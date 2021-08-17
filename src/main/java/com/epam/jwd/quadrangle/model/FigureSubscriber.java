package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.model.FigureContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class FigureSubscriber implements Subscriber<FigureContext> {
    private static final Logger LOG = LogManager.getLogger(FigureSubscriber.class);

    private static final String SUBSCRIPTION_COMPLETED_MSG = "Publisher or subscriber canceled the subscription!";

    private Subscription subscription;
    private FigureContext context;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(FigureContext contexts) {
        this.context = contexts;
        LOG.info(contexts);
    }

    @Override
    public void onError(Throwable throwable) {
        LOG.error(throwable);
    }

    @Override
    public void onComplete() {
        LOG.info(SUBSCRIPTION_COMPLETED_MSG);
    }

    @Override
    public String toString() {
        return "FigureSubscriber{" +
               "contexts=" + context +
               '}';
    }
}
