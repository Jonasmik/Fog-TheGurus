package com.yourcompany.api.facades;

import com.yourcompany.api.factories.OfferFactory;
import com.yourcompany.domain.offer.Offer;
import com.yourcompany.domain.offer.OfferRepository;
import com.yourcompany.exceptions.order.NoSuchOfferExists;
import com.yourcompany.infrastructure.database.DBOfferRepository;
import com.yourcompany.infrastructure.dbsetup.Database;

public class OfferFacade {

    private static OfferFacade instance;
    private final OfferRepository repo;

    public OfferFacade(OfferRepository repo) {
        this.repo = repo;
    }


    public static OfferFacade getInstance() {
        if (instance == null) {
            Database db = new Database();
            OfferRepository offerRepository = new DBOfferRepository(db);
            instance = new OfferFacade(offerRepository);
        }
        return instance;
    }

    public void createOffer(OfferFactory offerFactory) throws NoSuchOfferExists{
        repo.createOffer(offerFactory);
    }
    public Offer findActiveOfferByPreOrderId(int id) throws NoSuchOfferExists{
        return repo.findActiveOfferByPreOrderId(id);
    }
    public Offer findByPreOrderId(int id) throws NoSuchOfferExists{
        return repo.findByPreOrderId(id);
    }
    public void updateOffer(OfferFactory offerFactory) throws NoSuchOfferExists{
        repo.updateOffer(offerFactory);
    }
    public void updateOfferStatus(int id, boolean status) throws NoSuchOfferExists{
        repo.updateOfferStatus(id, status);
    }



}
