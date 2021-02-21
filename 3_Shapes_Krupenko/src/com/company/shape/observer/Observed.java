package com.company.shape.observer;

public interface Observed {
    void attach(CustomerObserver observer);

    void detach(CustomerObserver observer);

    void notifyObservers();
}
