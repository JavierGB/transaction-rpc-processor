package com.santander.processor.service;

import com.santander.processor.event.Document;
import com.santander.processor.event.RawEvent;
import com.santander.processor.exception.DuplicateElementException;
import com.santander.processor.exception.FunctionalException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class BusinessService {

    public Document bussinessLogic(RawEvent rawEvent) throws FunctionalException {
        //simulate FunctionalException (invalid format, unexpected data, bug, etc)
        if (rawEvent.getType().equals("finalized")) {
            throw new FunctionalException();
        }
        return new Document(rawEvent.getId(), rawEvent.getType());
    }

    public void rpc(Document document) throws DuplicateElementException {
        RestTemplate rpc = new RestTemplate();
        try {
            ResponseEntity responseEntity = rpc.postForEntity("http://localhost:9091/create", document, Object.class);
        } catch (HttpClientErrorException.Conflict e) {
            throw new DuplicateElementException();
        }
    }
}
