package com.apparelshop.dataparsingadidasservice.service;

import com.apparelshop.dataparsingadidasservice.dto.Offer;
import com.apparelshop.dataparsingadidasservice.repository.OfferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisService implements IRedisService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OfferRepository offerRepository;

    public boolean saveOffer(Offer offer) {
        offerRepository.save(offer);
        logger.info("Offer [" + offer.getArticleId() + "] has been saved.");
        return Boolean.TRUE;
    }


    public List<Offer> retrieveAll() {
        Iterable<Offer> all = offerRepository.findAll();
        List<Offer> offers = new ArrayList<>();
        logger.info("[" + offers.size() + "] offers have been retrieved from database.");
        all.forEach(offers::add);
        return offers;
    }

}



