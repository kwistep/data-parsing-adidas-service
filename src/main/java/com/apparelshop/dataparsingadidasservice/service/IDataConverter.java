package com.apparelshop.dataparsingadidasservice.service;

import com.apparelshop.dataparsingadidasservice.dto.Offer;
import com.apparelshop.dataparsingadidasservice.exception.EmptyResponseException;

public interface IDataConverter {

    public Offer getOfferData(String response) throws EmptyResponseException;

}
