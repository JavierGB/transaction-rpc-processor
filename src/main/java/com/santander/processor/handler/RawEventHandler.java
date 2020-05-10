package com.santander.processor.handler;

import com.santander.processor.event.Document;
import com.santander.processor.event.RawEvent;
import com.santander.processor.exception.DuplicateElementException;
import com.santander.processor.exception.FunctionalException;
import com.santander.processor.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
public class RawEventHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private BusinessService businessService;


    @StreamListener(Sink.INPUT)
    public void process(RawEvent rawEvent) {
        logger.info("Received RawEvent={}", rawEvent);

        try {
            Document document = businessService.bussinessLogic(rawEvent);
            businessService.rpc(document);
        } catch (FunctionalException e1) {
            logger.error("FunctionalException --> Sent to DLT");
            throw new FunctionalException();
        } catch (DuplicateElementException e2) {
            //commit record
            logger.info("DuplicateElementException --> Commit offset");
        }
    }
}
