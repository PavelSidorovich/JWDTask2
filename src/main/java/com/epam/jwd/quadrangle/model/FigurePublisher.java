package com.epam.jwd.quadrangle.model;

import com.epam.jwd.quadrangle.exception.ArgumentNullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * Contains figure to be subscribed by {@code FigureSubscriber}
 */
public class FigurePublisher implements Publisher<Figure> {
    private static final Logger LOG = LogManager.getLogger(FigurePublisher.class);

    private Figure figure;
    private final List<FigureSubscription> subscriptions = new ArrayList<>();

    public FigurePublisher(Figure figure) {
        if (figure == null) {
            throw new ArgumentNullException();
        }
        this.figure = figure;
        LOG.info(figure);
    }

    public void setFigure(Figure figure) {
        if (figure == null) {
            throw new ArgumentNullException();
        }
        this.figure = figure;
        publish();
    }

    public Figure getFigure() {
        return figure;
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
    public void subscribe(Subscriber<? super Figure> subscriber) {
        FigureSubscription subscription = new FigureSubscription((Subscriber<Figure>) subscriber);

        subscriptions.add(subscription);

        subscriber.onSubscribe(subscription);
    }

    private class FigureSubscription implements Subscription {
        private final Subscriber<Figure> subscriber;
        private boolean isCanceled;

        public FigureSubscription(Subscriber<Figure> subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void request(long n) {
            if (n != 0 && !isCanceled) {
                if (n < 0) {
                    subscriber.onError(new IllegalArgumentException());
                } else {
                    subscriber.onNext(figure);
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
