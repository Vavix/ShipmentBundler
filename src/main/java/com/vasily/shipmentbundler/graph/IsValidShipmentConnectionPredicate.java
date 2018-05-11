package com.vasily.shipmentbundler.graph;

import java.util.function.Predicate;

import com.vasily.shipmentbundler.shipment.Shipment;
import org.jetbrains.annotations.NotNull;

public class IsValidShipmentConnectionPredicate implements Predicate<Shipment> {
    private final Shipment shipment;

    IsValidShipmentConnectionPredicate(@NotNull final Shipment shipment) {
        this.shipment = shipment;
    }

    @Override
    public boolean test(@NotNull final Shipment previousDayShipment) {
        return previousDayShipment.getDestinationCity().equals(shipment.getOriginCity());
    }
}
