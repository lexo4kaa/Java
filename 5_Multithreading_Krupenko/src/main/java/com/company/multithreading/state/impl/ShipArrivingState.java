package com.company.multithreading.state.impl;

import com.company.multithreading.entity.Pier;
import com.company.multithreading.entity.Port;
import com.company.multithreading.entity.Ship;
import com.company.multithreading.exception.CustomerException;
import com.company.multithreading.state.ShipState;

import java.util.Optional;

public class ShipArrivingState implements ShipState {
    @Override
    public void doAction(Ship ship) throws CustomerException {
        Port port = Port.getInstance();
        Pier pier = port.getPier();
        ship.setPier(Optional.of(pier));
        switch (ship.getShipTarget()){
            case LOADING -> ship.setShipState(new ShipLoadingState());
            case UNLOADING, LOADING_UNLOADING -> ship.setShipState(new ShipUnloadingState());
        }
    }
}
