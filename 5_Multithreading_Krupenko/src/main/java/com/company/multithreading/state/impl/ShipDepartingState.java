package com.company.multithreading.state.impl;

import com.company.multithreading.entity.Pier;
import com.company.multithreading.entity.Port;
import com.company.multithreading.entity.Ship;
import com.company.multithreading.state.ShipState;

import java.util.Optional;

public class ShipDepartingState implements ShipState {
    @Override
    public void doAction(Ship ship) {
        Port port = Port.getInstance();
        Optional<Pier> pier = ship.getPier();
        if (pier.isPresent()) {
            port.releasePier(pier.get());
            ship.setPier(Optional.empty());
        }
        ship.setShipState(new ShipArrivingState());
    }
}
