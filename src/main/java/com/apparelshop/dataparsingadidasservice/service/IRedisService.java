package com.apparelshop.dataparsingadidasservice.service;

import com.apparelshop.dataparsingadidasservice.dto.Offer;

import java.util.List;

public interface IRedisService {

    public boolean saveOffer(Offer offer);

    public List<Offer> retrieveAll();

}
