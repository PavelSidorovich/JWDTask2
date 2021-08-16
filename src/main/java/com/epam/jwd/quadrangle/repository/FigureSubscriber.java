package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.model.FigureContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class FigureSubscriber implements Subscriber<List<FigureContext>> {
    private static final Logger LOG = LogManager.getLogger(FigureSubscriber.class);

    private static final String SUBSCRIPTION_COMPLETED_MSG = "Subscription completed!";

    private Subscription subscription;
    private List<FigureContext> contexts;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(List<FigureContext> contexts) {
        this.contexts = contexts;
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
               "contexts=" + contexts +
               '}';
    }
}
