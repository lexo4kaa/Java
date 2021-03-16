package com.company.multithreading.state.impl;

import com.company.multithreading.entity.Pier;
import com.company.multithreading.entity.Port;
import com.company.multithreading.entity.Ship;
import com.company.multithreading.entity.ShipTarget;
import com.company.multithreading.exception.CustomerException;
import com.company.multithreading.state.ShipState;

import java.util.Optional;

public class ShipUnloadingState implements ShipState {
    @Override
    public void doAction(Ship ship) throws CustomerException {
        Port port = Port.getInstance();
        Optional<Pier> pier = ship.getPier();
        if (pier.isPresent()) {
            port.unloadShip(ship);
        }
        if (ship.getShipTarget() == ShipTarget.LOADING_UNLOADING) {
            ship.setShipState(new ShipLoadingState());
        } else {
            ship.setShipState(new ShipDepartingState());
        }
    }
}
