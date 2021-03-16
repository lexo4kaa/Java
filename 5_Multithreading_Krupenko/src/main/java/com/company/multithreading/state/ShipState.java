package com.company.multithreading.state;

import com.company.multithreading.entity.Ship;
import com.company.multithreading.exception.CustomerException;

public interface ShipState {
    void doAction(Ship ship) throws CustomerException;
}
