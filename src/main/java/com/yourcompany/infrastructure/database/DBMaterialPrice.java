package com.yourcompany.infrastructure.database;

import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.material.MaterialPrice;
import com.yourcompany.domain.material.MaterialPriceRepository;
import com.yourcompany.exceptions.bom.NoSuchMaterialExist;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.infrastructure.dbsetup.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DBMaterialPrice implements MaterialPriceRepository {

    private final Database db;

    public DBMaterialPrice(Database db) {
        this.db = db;
    }

    private MaterialPrice loadMaterialPrice(ResultSet rs) throws SQLException {
        return new MaterialPrice(
            rs.getInt("materialprice.id"),
            rs.getString("materialprice.name"),
            rs.getDouble("materialprice.meterprice")
        );
    }

    @Override
    public MaterialPrice findById(int id) throws NoSuchMaterialExist {
        try(Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                "SELECT * FROM materialprice WHERE id = ?;");
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            if(rs.next()) {
                return loadMaterialPrice(rs);
            } else {
                System.err.println("No version in properties.");
                throw new NoSuchElementException("No materialprice with id: " + id);
            }
        } catch (SQLException e) {
            throw new NoSuchMaterialExist();
        }
    }

    @Override
    public MaterialPrice findByName(String name) throws NoSuchMaterialExist {
        try(Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                "SELECT * FROM materialprice WHERE name = ?;");
            s.setString(1, name);
            ResultSet rs = s.executeQuery();
            if(rs.next()) {
                return loadMaterialPrice(rs);
            } else {
                System.err.println("No version in properties.");
                throw new NoSuchElementException("No materialprice with name: " + name);
            }
        } catch (SQLException e) {
            throw new NoSuchMaterialExist();
        }
    }

    @Override
    public void updateMaterial(int id, double price) throws NoSuchMaterialExist {
        try (Connection conn = db.connect()) {
            PreparedStatement ps =
                conn.prepareStatement(
                    "UPDATE materialprice SET meterprice = ? WHERE id = ?;");
            ps.setDouble(1, price);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new NoSuchMaterialExist();
        }
    }

    @Override
    public List<MaterialPrice> findAll() throws NoSuchMaterialExist {
        List<MaterialPrice> materialPriceList = new ArrayList<>();
        try (Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement("SELECT * FROM materialprice");
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                materialPriceList.add(loadMaterialPrice(rs));
            }
        } catch (SQLException e) {
            throw new NoSuchMaterialExist();
        }
        return materialPriceList;
    }
}
