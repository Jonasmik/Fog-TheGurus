package com.yourcompany.infrastructure.database;

import com.yourcompany.api.factories.CarportFactory;
import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.carport.CarportRepository;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.exceptions.shed.NoSuchShedExists;
import com.yourcompany.infrastructure.dbsetup.Database;

import java.sql.*;
import java.util.List;
import java.util.NoSuchElementException;

public class DBCarportRepository implements CarportRepository {
    private final Database db;

    public DBCarportRepository(Database db) {
        this.db = db;
    }

    private Carport loadCarport(ResultSet rs) throws SQLException {
        return new Carport(
                rs.getInt("carport.id"),
                rs.getInt("carport.length"),
                rs.getInt("carport.width"),
                rs.getString("carport.roof"),
                rs.getInt("carport.roofangle"));

    }
    @Override
    public List<Carport> findAll() throws NoSuchCarportExists {
        return null;
    }

    @Override
    public Carport findById(int id) throws NoSuchCarportExists {
        try(Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                "SELECT * FROM carport WHERE id = ?;");
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            if(rs.next()) {
                return loadCarport(rs);
            } else {
                System.err.println("No version in properties.");
                throw new NoSuchElementException("No carport with id: " + id);
            }
        } catch (SQLException e) {
            throw new NoSuchCarportExists();
        }
    }

    @Override
    public Carport createCarport(CarportFactory carportFactory) throws NoSuchCarportExists {
        int id;
        try (Connection conn = db.connect()) {
            PreparedStatement ps =
                    conn.prepareStatement(
                            "INSERT INTO carport (length, width, roof, roofAngle) " +
                                    "VALUE (?,?,?,?);",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, carportFactory.getLength());
            ps.setInt(2, carportFactory.getWidth());
            ps.setString(3, carportFactory.getRoof());
            ps.setInt(4, carportFactory.getRoofAngle());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                throw new NoSuchCarportExists();
            }
        } catch (SQLException e) {
            throw new NoSuchCarportExists();
        }
        return findById(id);
    }

    @Override
    public void updateCarport(CarportFactory carportFactory, int id) throws NoSuchCarportExists {
        try (Connection conn = db.connect()) {
            PreparedStatement ps =
                conn.prepareStatement(
                    "UPDATE carport SET length = ?, width = ?, roof = ?, roofangle = ? WHERE id = ?;");
            ps.setInt(1, carportFactory.getLength());
            ps.setInt(2, carportFactory.getWidth());
            ps.setString(3, carportFactory.getRoof());
            ps.setInt(4, carportFactory.getRoofAngle());
            ps.setInt(5, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new NoSuchCarportExists();
        }
    }

}
