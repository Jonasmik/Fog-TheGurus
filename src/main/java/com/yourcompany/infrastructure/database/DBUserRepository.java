package com.yourcompany.infrastructure.database;

import com.yourcompany.api.factories.UserFactory;
import com.yourcompany.domain.user.User;
import com.yourcompany.domain.user.UserRepository;
import com.yourcompany.exceptions.order.NoSuchOfferExists;
import com.yourcompany.exceptions.user.UserValidationError;
import com.yourcompany.infrastructure.dbsetup.Database;

import java.sql.*;
import java.util.NoSuchElementException;

public class DBUserRepository implements UserRepository {

    private final Database db;

    public DBUserRepository(Database db) {
        this.db = db;
    }

    private User loadUser(ResultSet rs) throws SQLException {
        return new User(
            rs.getInt("users.id"),
            rs.getString("users.name"),
            rs.getString("users.email"),
            rs.getString("users.address"),
            rs.getString("users.zip"),
            rs.getString("users.city"),
            rs.getTimestamp("users.createdAt").toLocalDateTime(),
            rs.getBytes("users.salt"),
            rs.getBytes("users.secret"),
            rs.getString("users.role"));

    }

    @Override
    public User authorizeUser(String email) throws UserValidationError {
        try (Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                "SELECT * FROM users WHERE email = ?;");
            s.setString(1, email);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                return loadUser(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new UserValidationError(e.getMessage());
        }

    }


    @Override
    public User createUser(UserFactory userFactory, byte[] salt, byte[] secret) throws UserValidationError {
        int id;
        try (Connection conn = db.connect()) {
            PreparedStatement ps =
                conn.prepareStatement(
                    "INSERT INTO users (name, email, address, zip, city, salt, secret, role) " +
                        "VALUE (?,?,?,?,?,?,?,?);",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userFactory.getName());
            ps.setString(2, userFactory.getEmail());
            ps.setString(3, userFactory.getAddress());
            ps.setString(4, userFactory.getZip());
            ps.setString(5, userFactory.getCity());
            ps.setBytes(6, salt);
            ps.setBytes(7, secret);
            ps.setString(8, "customer");

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                throw new UserValidationError(userFactory.getEmail());
            }
        } catch (SQLException e) {
            throw new UserValidationError(e.getMessage());
        }
        return findUserById(id);
    }

    @Override
    public User findUserById(int id) throws UserValidationError {
        try (Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                "SELECT * FROM users WHERE id = ?;");
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                return loadUser(rs);
            } else {
                System.err.println("No version in properties.");
                throw new NoSuchElementException("No user with id: " + id);
            }
        } catch (SQLException e) {
            throw new UserValidationError(e.getMessage());
        }
    }

    @Override
    public User updateUserSettings(int id, String email, String name) throws UserValidationError {
        try (Connection conn = db.connect()) {
            PreparedStatement ps =
                conn.prepareStatement(
                    "UPDATE users SET email = ?, name = ? WHERE id = ?;");
            ps.setString(1, email);
            ps.setString(2, name);
            ps.setInt(3, id);
            ps.executeUpdate();
            ps.close();

            PreparedStatement ps2 =
                conn.prepareStatement(
                    "SELECT * from users WHERE id = ?;");
            ps2.setInt(1, id);
            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {
                return loadUser(rs);
            } else {
                throw new NoSuchElementException("No user with id: " + id);
            }
        } catch (SQLException e) {
            throw new UserValidationError(e.getMessage());
        }
    }
}
