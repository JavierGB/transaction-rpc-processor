package com.santander.processor;

import com.santander.processor.event.Document;
import com.santander.processor.event.RawEvent;
import com.santander.processor.exception.DuplicateElementException;
import com.santander.processor.exception.FunctionalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableBinding(Sink.class)
@EnableAspectJAutoProxy
public class RPCProcessorSample {

    public static void main(String[] args) {
        SpringApplication.run(RPCProcessorSample.class, args);
    }
}