package com.yourcompany.infrastructure.database;

import com.sun.jdi.connect.Connector;
import com.yourcompany.api.factories.UserFactory;
import com.yourcompany.domain.user.User;
import com.yourcompany.domain.user.UserRepository;
import com.yourcompany.exceptions.UserValidationError;
import com.yourcompany.infrastructure.dbsetup.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                rs.getTimestamp("users.createdAt").toLocalDateTime(),
                rs.getBytes("users.salt"),
                rs.getBytes("users.secret"),
                rs.getString("users.role"));

    }

    @Override
    public User authorizeUser(String email) throws UserValidationError {
        try(Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                    "SELECT * FROM users WHERE email = ?;");
            s.setString(1, email);
            ResultSet rs = s.executeQuery();
            if(rs.next()) {
                return loadUser(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new UserValidationError(e.getMessage());
        }

    }



    @Override
    public User createUser(UserFactory userFactory) {
        return null;
    }
}
