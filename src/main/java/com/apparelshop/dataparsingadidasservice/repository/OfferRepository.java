package com.apparelshop.dataparsingadidasservice.repository;

import com.apparelshop.dataparsingadidasservice.dto.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Integer> {

}
