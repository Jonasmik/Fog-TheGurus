package com.yourcompany.infrastructure.database;

import com.yourcompany.api.factories.OfferFactory;
import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.offer.Offer;
import com.yourcompany.domain.offer.OfferRepository;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.exceptions.order.NoSuchOfferExists;
import com.yourcompany.infrastructure.dbsetup.Database;

import java.sql.*;
import java.util.NoSuchElementException;

public class DBOfferRepository implements OfferRepository {
    private final Database db;

    public DBOfferRepository(Database db) {
        this.db = db;
    }
    private Offer loadOffer(ResultSet rs) throws SQLException {
        return new Offer(
                rs.getInt("offers.id"),
                rs.getInt("offers.preorderid"),
                rs.getDouble("offers.price"),
                rs.getBoolean("offers.active"));

    }
    @Override
    public void createOffer(OfferFactory offerFactory) throws NoSuchOfferExists {
        try (Connection conn = db.connect()) {
            PreparedStatement ps =
                    conn.prepareStatement(
                            "INSERT INTO offers (preorderid, price, active) " +
                                    "VALUE (?,?,?);",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, offerFactory.getPreorderid());
            ps.setDouble(2, offerFactory.getPrice());
            ps.setBoolean(3, offerFactory.isActive());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new NoSuchOfferExists();
        }
    }

    @Override
    public Offer findByPreOrderId(int id) throws NoSuchOfferExists {
        try(Connection conn = db.connect()) {
            PreparedStatement s = conn.prepareStatement(
                    "SELECT * FROM offers WHERE id = ?;");
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            if(rs.next()) {
                return loadOffer(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new NoSuchOfferExists();
        }
    }

    @Override
    public void updateOffer(OfferFactory offerFactory) throws NoSuchOfferExists {
        try (Connection conn = db.connect()) {
            PreparedStatement ps =
                    conn.prepareStatement(
                            "UPDATE offers SET price = ? WHERE preorderid = ?;");
            ps.setDouble(1, offerFactory.getPrice());
            ps.setInt(2, offerFactory.getPreorderid());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new NoSuchOfferExists();
        }
    }
}
