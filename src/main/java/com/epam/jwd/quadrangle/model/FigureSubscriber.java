package com.epam.jwd.quadrangle.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * Contains figureContext to be observed.
 */
public class FigureSubscriber implements Subscriber<Figure> {
    private static final Logger LOG = LogManager.getLogger(FigureSubscriber.class);

    private static final String SUBSCRIPTION_COMPLETED_MSG = "Publisher or subscriber canceled the subscription!";

    private Subscription subscription;
    private FigureContext figureContext;

    public FigureContext getFigureContext() {
        try {
            return (FigureContext) figureContext.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    /**
     * Builds figureContext on provided figure
     *
     * @param figure figure
     */
    @Override
    public void onNext(Figure figure) {
        figureContext = new FigureContext(figure);
        LOG.info(figure);
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
               "figureContext=" + figureContext +
               '}';
    }
}
