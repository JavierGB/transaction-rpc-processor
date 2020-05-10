package com.santander.processor.fake;

import com.santander.processor.event.Document;
import com.santander.processor.event.RawEvent;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Random;

@Aspect
@Component
public class NetworkErrorSimulate {

    @AfterReturning("execution(* *..*RawEventHandler+.process(com.santander.processor.event.RawEvent)) && args(msg)")
    public void kafkaNetworkmaybeFail(RawEvent msg) {
        randomNetworkError();
    }

    @AfterReturning("execution(* *..*RPCFake+.create(com.santander.processor.event.Document)) && args(document)")
    public void rpcNetworkmaybeFail(Document document) {
        randomNetworkError();
    }

    private void randomNetworkError() {
        Random rand = new Random();
        int rand_int1 = rand.nextInt(10);
        if (rand_int1 == 3) {
            throw new RuntimeException("Simulated network error");
        }
    }

}
