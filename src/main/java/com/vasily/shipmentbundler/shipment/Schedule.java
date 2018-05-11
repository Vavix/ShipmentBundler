package com.vasily.shipmentbundler.shipment;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.vasily.shipmentbundler.date.DayOfWeek;
import org.jetbrains.annotations.NotNull;

import com.google.common.collect.Sets;

public class Schedule {
    private final Set<Shipment>[] shipmentsForDaysOfWeek = initializeShipmentSets();

    public void addToSchedule(@NotNull final Shipment shipment) {
        shipmentsForDaysOfWeek[shipment.getDayOfWeek().getIndex()].add(shipment);
    }

    public Set<Shipment> getShipments(final int dayOfWeekIndex) {
        return shipmentsForDaysOfWeek[dayOfWeekIndex];
    }

    public Set<Shipment> getAllShipments() {
        return Arrays.stream(shipmentsForDaysOfWeek)
                     .flatMap(Set::stream)
                     .collect(Collectors.toSet());
    }

    private Set<Shipment>[] initializeShipmentSets() {
        final int numberOfDays = DayOfWeek.values().length;
        final Set<Shipment>[] sets = new Set[numberOfDays];
        IntStream.range(0, numberOfDays).forEach(index -> sets[index] = Sets.newHashSet());
        return sets;
    }
}
