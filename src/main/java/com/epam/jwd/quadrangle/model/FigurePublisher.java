package com.epam.jwd.quadrangle.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class FigurePublisher implements Publisher<FigureContext> {
    private static final Logger LOG = LogManager.getLogger(FigurePublisher.class);

    private FigureContext figureContext;
    private final List<FigureSubscription> subscriptions = new ArrayList<>();

    public FigurePublisher(Figure figure) {
        figureContext = new FigureContext(figure);
        LOG.info(figureContext);
    }

    public void setFigure(Figure figure) {
        figureContext = new FigureContext(figure);
        publish();
    }

    public FigureContext getFigureContext() {
        return figureContext;
    }

    public void cancel() {
        subscriptions.forEach(FigureSubscription::cancel);
    }

    private void publish() {
        for (FigureSubscription subscription : subscriptions) {
            subscription.request(1);
        }
    }

    @Override
    public void subscribe(Subscriber<? super FigureContext> subscriber) {
        FigureSubscription subscription = new FigureSubscription((Subscriber<FigureContext>) subscriber);

        subscriptions.add(subscription);

        subscriber.onSubscribe(subscription);
    }

    private class FigureSubscription implements Subscription {
        private final Subscriber<FigureContext> subscriber;
        private boolean isCanceled;

        public FigureSubscription(Subscriber<FigureContext> subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void request(long n) {
            if (n != 0 && !isCanceled) {
                if (n < 0) {
                    subscriber.onError(new IllegalArgumentException());
                } else {
                    subscriber.onNext(figureContext);
                }
            }
        }

        @Override
        public void cancel() {
            isCanceled = true;
            subscriptions.remove(this);
            subscriber.onComplete();
        }
    }
}
