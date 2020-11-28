package com.yourcompany.infrastructure.database;

import com.yourcompany.api.factories.PreOrderFactory;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.preorder.PreOrderRepository;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.infrastructure.dbsetup.Database;

import java.sql.*;
import java.util.List;
import java.util.NoSuchElementException;

public class DBPreOrderRepository implements PreOrderRepository {

    private final Database db;

    public DBPreOrderRepository(Database db) {
        this.db = db;
    }

    private PreOrder loadPreOrder(ResultSet rs) throws SQLException {
        return new PreOrder(
                rs.getInt("preorders.id"),
                rs.getInt("preorders.customerid"),
                rs.getInt("preorders.salesmanid"),
                rs.getInt("preorders.carportid")
        );
    }

    @Override
    public List<PreOrder> findAll() throws NoSuchPreOrderExists {
        return null;
    }

    @Override
    public PreOrder findPreOrderById(int id) throws NoSuchPreOrderExists {
        try(Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                    "SELECT * FROM preorders WHERE id = ?;");
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            if(rs.next()) {
                return loadPreOrder(rs);
            } else {
                System.err.println("No version in properties.");
                throw new NoSuchElementException("No preorder with id: " + id);
            }
        } catch (SQLException e) {
            throw new NoSuchPreOrderExists(e.getMessage());
        }
    }

    @Override
    public PreOrder createPreOrder(PreOrderFactory preOrderFactory) throws NoSuchPreOrderExists {
        int id;
        try (Connection conn = db.connect()) {
            PreparedStatement ps =
                    conn.prepareStatement(
                            "INSERT INTO preorders (customerid, carportid)" +
                                    "VALUE (?,?,?,?,?,?,?);",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, preOrderFactory.getCustomerId());
            ps.setInt(2, preOrderFactory.getCarportId());
            try {
                ps.executeUpdate();
            } catch (SQLIntegrityConstraintViolationException e) {
                throw new NoSuchPreOrderExists(e.getMessage());
            }

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                throw new NoSuchPreOrderExists("Failed to generate id of preorder");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findPreOrderById(id);
    }
}
