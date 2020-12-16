package com.yourcompany.domain.offer;

import com.yourcompany.api.factories.OfferFactory;
import com.yourcompany.exceptions.order.NoSuchOfferExists;

public interface OfferRepository {
    void createOffer(OfferFactory offerFactory) throws NoSuchOfferExists;
    Offer findByPreOrderId(int id) throws NoSuchOfferExists;
    void updateOffer(OfferFactory offerFactory) throws NoSuchOfferExists;

}
