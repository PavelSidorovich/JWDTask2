package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigureContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class FigurePublisher implements Publisher<List<FigureContext>> {
    private static final Logger LOG = LogManager.getLogger(FigurePublisher.class);

    private final List<FigureContext> contextList;
    private final List<FigureSubscription> subscriptions = new ArrayList<>();

    public FigurePublisher(List<Figure> figures) {
        contextList = new ArrayList<>();
        for (Figure figure : figures) {
            contextList.add(new FigureContext(figure));
        }
        LOG.info(contextList);
    }

    public void setContextList(List<Figure> figures) {
        contextList.clear();
        for (Figure figure : figures) {
            contextList.add(new FigureContext(figure));
        }
        publish();
    }

    @Override
    public void subscribe(Subscriber<? super List<FigureContext>> subscriber) {
        FigureSubscription subscription = new FigureSubscription((Subscriber<List<FigureContext>>) subscriber);

        subscriptions.add(subscription);

        subscriber.onSubscribe(subscription);
    }

    private void publish() {
        for (FigureSubscription subscription : subscriptions) {
            subscription.request(1);
        }
    }

    private class FigureSubscription implements Subscription {
        private final Subscriber<List<FigureContext>> subscriber;
        private boolean isCanceled;

        public FigureSubscription(Subscriber<List<FigureContext>> subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void request(long n) {
            if (n != 0 && !isCanceled) {
                if (n < 0) {
                    subscriber.onError(new IllegalArgumentException());
                } else {
                    subscriber.onNext(contextList);
                }
            }
        }

        @Override
        public void cancel() {
            isCanceled = true;
            subscriptions.remove(this);
        }
    }
}
