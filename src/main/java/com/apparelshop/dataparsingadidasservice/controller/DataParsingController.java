package com.apparelshop.dataparsingadidasservice.controller;

import com.apparelshop.dataparsingadidasservice.controller.feign.DatabaseServiceProxy;
import com.apparelshop.dataparsingadidasservice.dto.Offer;
import com.apparelshop.dataparsingadidasservice.dto.RequestData;
import com.apparelshop.dataparsingadidasservice.exception.EmptyResponseException;
import com.apparelshop.dataparsingadidasservice.service.IDataConverter;
import com.apparelshop.dataparsingadidasservice.service.IRedisService;
import com.apparelshop.dataparsingadidasservice.service.IRequestPerformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DataParsingController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IRequestPerformer requestPerformer;

    @Autowired
    private IDataConverter dataConverter;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private DatabaseServiceProxy proxy;

    @PostMapping("/process")
    public boolean processData(@RequestBody RequestData requestData) {
        String response = requestPerformer.getResponse(requestData.getLink());

        Offer offerData;
        try {
            offerData = dataConverter.getOfferData(response);
        } catch (EmptyResponseException e) {
            return Boolean.FALSE;
        }
        //offerData is never NULL
        logger.info(offerData.toString());
        redisService.saveOffer(offerData);
        return Boolean.TRUE;
    }

    @PutMapping("/save")
    public boolean sendToDatabase() {
        List<Offer> offers = redisService.retrieveAll();
        proxy.saveData(offers);
        return Boolean.TRUE;
    }

    @GetMapping("/retrieve")
    public ResponseEntity<List<Offer>> retrieveExtractedData() {
        return new ResponseEntity<>(redisService.retrieveAll(), HttpStatus.ACCEPTED);
    }


}
