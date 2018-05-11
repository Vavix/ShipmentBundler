package com.vasily.shipmentbundler.shipment;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class BundlePrinter {
    public void printToStdOut(@NotNull final Set<List<Shipment>> shipmentBundles) {
        shipmentBundles.forEach(this::printShipmentIdentifiers);
    }

    private void printShipmentIdentifiers(final List<Shipment> shipments) {
        final List<Integer> shipmentIdentifiers = shipments.stream()
                                                           .map(Shipment::getIdentifier)
                                                           .collect(Collectors.toList());
        System.out.println(StringUtils.join(shipmentIdentifiers, ' '));
    }
}
