package com.yourcompany.infrastructure.database;

import com.yourcompany.api.factories.CustomerFactory;
import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.customer.CustomerRepository;
import com.yourcompany.exceptions.shed.NoSuchShedExists;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.infrastructure.dbsetup.Database;

import java.sql.*;
import java.util.NoSuchElementException;

public class DBCustomerRepository implements CustomerRepository {
    private final Database db;

    public DBCustomerRepository(Database db) {
        this.db = db;
    }

    private Customer loadCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("customers.id"),
                rs.getInt("customers.userid"),
                rs.getString("customers.name"),
                rs.getString("customers.adress"),
                rs.getInt("customers.zipcode"),
                rs.getString("customers.city"),
                rs.getString("customers.email"),
                rs.getString("customers.additional")

        );
    }

    @Override
    public Customer findById(int id) throws NoSuchCustomerExists {
        try(Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                    "SELECT * FROM customers WHERE id = ?;");
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            if(rs.next()) {
                return loadCustomer(rs);
            } else {
                System.err.println("No version in properties.");
                throw new NoSuchElementException("No customer with id: " + id);
            }
        } catch (SQLException e) {
            throw new NoSuchCustomerExists();
        }
    }

    @Override
    public Customer createCustomer(CustomerFactory customerFactory) throws NoSuchCustomerExists {
        int id;
        try (Connection conn = db.connect()) {
            PreparedStatement ps =
                    conn.prepareStatement(
                            "INSERT INTO customers (userid, name, adress, zipcode, city, email, additional)" +
                                    "VALUE (?,?,?,?,?,?,?);",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, customerFactory.getUserid());
            ps.setString(2, customerFactory.getName());
            ps.setString(3, customerFactory.getAdress());
            ps.setInt(4, customerFactory.getZipcode());
            ps.setString(5, customerFactory.getCity());
            ps.setString(6, customerFactory.getEmail());
            ps.setString(7, customerFactory.getAdditional());
            try {
                ps.executeUpdate();
            } catch (SQLIntegrityConstraintViolationException e) {
                throw new NoSuchCustomerExists();
            }

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                throw new NoSuchCustomerExists();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findById(id);
    }
}
