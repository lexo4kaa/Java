package com.company.multithreading.state.impl;

import com.company.multithreading.entity.Pier;
import com.company.multithreading.entity.Port;
import com.company.multithreading.entity.Ship;
import com.company.multithreading.exception.CustomerException;
import com.company.multithreading.state.ShipState;

import java.util.Optional;

public class ShipLoadingState implements ShipState {
    @Override
    public void doAction(Ship ship) throws CustomerException {
        Port port = Port.getInstance();
        Optional<Pier> pier = ship.getPier();
        if (pier.isPresent()) {
            port.loadShip(ship);
        }
        ship.setShipState(new ShipDepartingState());
    }
}