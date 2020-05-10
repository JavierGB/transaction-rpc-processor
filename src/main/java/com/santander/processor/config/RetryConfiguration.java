package com.santander.processor.config;

import com.santander.processor.exception.FunctionalException;
import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.cloud.stream.annotation.StreamRetryTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.AlwaysRetryPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collections;

@Configuration
public class RetryConfiguration {

    @StreamRetryTemplate
    public RetryTemplate customRetryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(retryPolicy());
        retryTemplate.setBackOffPolicy(backOffPolicy());

        return retryTemplate;
    }

    private ExceptionClassifierRetryPolicy retryPolicy() {
        BinaryExceptionClassifier keepRetryingClassifier = new BinaryExceptionClassifier(
                Collections.singletonList(FunctionalException.class
                ));
        keepRetryingClassifier.setTraverseCauses(true);

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy(1);
        AlwaysRetryPolicy alwaysRetryPolicy = new AlwaysRetryPolicy();

        ExceptionClassifierRetryPolicy retryPolicy = new ExceptionClassifierRetryPolicy();
        retryPolicy.setExceptionClassifier(
                classifiable -> keepRetryingClassifier.classify(classifiable) ?
                        simpleRetryPolicy : alwaysRetryPolicy);

        return retryPolicy;
    }

    private FixedBackOffPolicy backOffPolicy() {
        final FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(10000);
        return backOffPolicy;
    }
}