package com.yourcompany.infrastructure.database;

import com.yourcompany.api.factories.OrderFactory;
import com.yourcompany.domain.order.Order;
import com.yourcompany.domain.order.OrderRepository;
import com.yourcompany.exceptions.order.NoSuchOfferExists;
import com.yourcompany.exceptions.order.NoSuchOrderExists;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.infrastructure.dbsetup.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBOrderRepository implements OrderRepository {

    private final Database db;

    public DBOrderRepository(Database db) {
        this.db = db;
    }

    private Order loadOrder(ResultSet rs) throws SQLException {
        return new Order(
            rs.getInt("orders.id"),
            rs.getInt("orders.customerid"),
            rs.getInt("orders.offerid")
        );
    }

    //Brugt fra AcceptOffer
    @Override
    public Order createOrder(OrderFactory orderFactory) throws NoSuchOrderExists {
        int id;
        try (Connection conn = db.connect()) {
            PreparedStatement ps =
                conn.prepareStatement(
                    "INSERT INTO orders (customerid, offerid)" +
                        "VALUE (?,?);",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, orderFactory.getCustomerId());
            ps.setInt(2, orderFactory.getOffer());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                throw new NoSuchOrderExists();
            }
        } catch (SQLException e) {
            throw new NoSuchOrderExists();
        }
        return findOrderById(id);
    }

    @Override
    public Order findOrderById(int id) throws NoSuchOrderExists {
        try(Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                "SELECT * FROM orders WHERE id = ?;");
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            if(rs.next()) {
                return loadOrder(rs);
            } else {
                throw new NoSuchOrderExists();
            }
        } catch (SQLException e) {
            throw new NoSuchOrderExists();
        }
    }

}
