package com.yourcompany.infrastructure.database;

import com.yourcompany.api.factories.SalesmanFactory;
import com.yourcompany.domain.salesman.Salesman;
import com.yourcompany.domain.salesman.SalesmanRepository;
import com.yourcompany.exceptions.user.NoSuchSalesmanExists;
import com.yourcompany.infrastructure.dbsetup.Database;

import java.sql.*;
import java.util.NoSuchElementException;

public class DBSalesmanRepository implements SalesmanRepository {

    private final Database db;

    public DBSalesmanRepository(Database db) {
        this.db = db;
    }

    private Salesman loadSalesman(ResultSet rs) throws SQLException {
        return new Salesman(
                rs.getInt("salesmen.id"),
                rs.getInt("salesmen.userid")
        );
    }


    @Override
    public Salesman createSalesman(SalesmanFactory salesmanFactory) throws NoSuchSalesmanExists {
        int id;
        try (Connection conn = db.connect()) {
            PreparedStatement ps =
                    conn.prepareStatement(
                            "INSERT INTO salesmen (userid) " +
                                    "VALUE (?);",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, salesmanFactory.getUserId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                throw new NoSuchSalesmanExists();
            }
        } catch (SQLException e) {
            throw new NoSuchSalesmanExists();
        }
        return findById(id);
    }

    @Override
    public Salesman findByUserId(int userId) throws NoSuchSalesmanExists {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Salesman findById(int id) throws NoSuchSalesmanExists {
        try(Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                    "SELECT * FROM salesmen WHERE id = ?;");
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            if(rs.next()) {
                return loadSalesman(rs);
            } else {
                System.err.println("No version in properties.");
                throw new NoSuchElementException("No salesman with id: " + id);
            }
        } catch (SQLException e) {
            throw new NoSuchSalesmanExists();
        }
    }
}
