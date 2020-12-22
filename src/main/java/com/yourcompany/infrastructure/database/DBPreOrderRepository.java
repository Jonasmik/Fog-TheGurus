package com.yourcompany.infrastructure.database;

import com.yourcompany.api.factories.PreOrderFactory;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.preorder.PreOrderRepository;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.infrastructure.dbsetup.Database;

import java.sql.*;
import java.util.ArrayList;
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
            rs.getInt("preorders.carportid"),
            rs.getBoolean("preorders.active"),
            rs.getBoolean("preorders.sold"));
    }

    @Override
    public List<PreOrder> findAllUnused() throws NoSuchPreOrderExists {
        List<PreOrder> preOrders = new ArrayList<>();
        try (Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement("SELECT * FROM preorders WHERE salesmanid IS NULL");
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                preOrders.add(loadPreOrder(rs));
            }
        } catch (SQLException e) {
            throw new NoSuchPreOrderExists(e.getMessage());
        }
        return preOrders;
    }

    @Override
    public PreOrder findBySalesmanId(int salesmanId) throws NoSuchPreOrderExists {
        try (Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                "SELECT * FROM preorders WHERE salesmanid = ? AND active = true;");
            s.setInt(1, salesmanId);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                return loadPreOrder(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new NoSuchPreOrderExists(e.getMessage());
        }
    }

    @Override
    public PreOrder findByCustomerId(int customerid, boolean sold) throws NoSuchPreOrderExists {
        try (Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                "SELECT * FROM preorders WHERE customerid = ? AND sold = ?;");
            s.setInt(1, customerid);
            s.setBoolean(2, sold);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                return loadPreOrder(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new NoSuchPreOrderExists(e.getMessage());
        }
    }

    @Override
    public PreOrder findPreOrderById(int id) throws NoSuchPreOrderExists {
        try (Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                "SELECT * FROM preorders WHERE id = ?;");
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
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
                        "VALUE (?,?);",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, preOrderFactory.getCustomerId());
            ps.setInt(2, preOrderFactory.getCarportId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                throw new NoSuchPreOrderExists("Failed to generate id of preorder");
            }
        } catch (SQLException e) {
            throw new NoSuchPreOrderExists(e.getMessage());
        }
        return findPreOrderById(id);
    }

    @Override
    public void takePreOrder(int salesmanId, int preOrderId) throws NoSuchPreOrderExists {
        try (Connection conn = db.connect()) {
            PreparedStatement ps =
                conn.prepareStatement(
                    "UPDATE preorders SET salesmanid = ? WHERE id = ?;");
            ps.setInt(1, salesmanId);
            ps.setInt(2, preOrderId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new NoSuchPreOrderExists(e.getMessage());
        }
    }

    @Override
    public void updatePreOrderStatus(String columnName, int id, boolean status) throws NoSuchPreOrderExists {
        try (Connection conn = db.connect()) {
            PreparedStatement ps = null;
            if (columnName.equals("active")){
                ps = conn.prepareStatement(
                                "UPDATE preorders SET active = ? WHERE id = ?;");
            } else {
                ps = conn.prepareStatement(
                                "UPDATE preorders SET sold = ? WHERE id = ?;");
            }
            ps.setBoolean(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new NoSuchPreOrderExists(e.getMessage());
        }
    }
}
