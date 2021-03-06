package com.yourcompany.infrastructure.database;

import com.yourcompany.api.factories.ShedFactory;
import com.yourcompany.domain.shed.Shed;
import com.yourcompany.domain.shed.ShedRepository;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.shed.NoSuchShedExists;
import com.yourcompany.infrastructure.dbsetup.Database;

import java.sql.*;
import java.util.NoSuchElementException;

public class DBShedRepository implements ShedRepository {
    private final Database db;

    public DBShedRepository(Database db) {
        this.db = db;
    }

    private Shed loadShed(ResultSet rs) throws SQLException {
        return new Shed(
                rs.getInt("shed.id"),
                rs.getInt("shed.length"),
                rs.getInt("shed.width"),
                rs.getInt("shed.carportid"));
    }

    @Override
    public Shed findById(int id) throws NoSuchShedExists {
        try(Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                    "SELECT * FROM shed WHERE id = ?;");
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            if(rs.next()) {
                return loadShed(rs);
            } else {
                System.err.println("No version in properties.");
                throw new NoSuchElementException("No shed with id: " + id);
            }
        } catch (SQLException e) {
            throw new NoSuchShedExists();
        }
    }

    @Override
    public Shed createShed(ShedFactory shedFactory) throws NoSuchShedExists {
        int id;
        try (Connection conn = db.connect()) {
            PreparedStatement ps =
                    conn.prepareStatement(
                            "INSERT INTO shed (length, width, carportid)" +
                                    "VALUE (?,?,?);",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, shedFactory.getLength());
            ps.setInt(2, shedFactory.getWidth());
            ps.setInt(3, shedFactory.getCarportID());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                throw new NoSuchShedExists();
            }
        } catch (SQLException e) {
            throw new NoSuchShedExists();
        }
        return findById(id);
    }

    @Override
    public Shed findByCarportId(int id) throws NoSuchShedExists {
        try(Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                "SELECT * FROM shed WHERE carportid = ?;");
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            if(rs.next()) {
                return loadShed(rs);
            } else {
                return new Shed(-1, 0, 0, id);
            }
        } catch (SQLException e) {
            throw new NoSuchShedExists();
        }
    }

    @Override
    public void updateShed(ShedFactory shedFactory, int id) throws NoSuchShedExists {
        try (Connection conn = db.connect()) {
            PreparedStatement ps =
                conn.prepareStatement(
                    "UPDATE shed SET width = ?, length = ? WHERE id = ?;");
            ps.setInt(1, shedFactory.getWidth());
            ps.setInt(2, shedFactory.getLength());
            ps.setInt(3, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new NoSuchShedExists();
        }
    }

}
