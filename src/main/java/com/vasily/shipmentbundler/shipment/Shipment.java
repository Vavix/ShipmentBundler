package com.vasily.shipmentbundler.shipment;

import java.util.Objects;

import com.vasily.shipmentbundler.date.DayOfWeek;

public class Shipment {
    private static final int IMAGINARY_SOURCE_ID = -1;
    private static final int IMAGINARY_DESTINATION_ID = -2;
    public static final Shipment IMAGINARY_SOURCE = new Shipment(IMAGINARY_SOURCE_ID);
    public static final Shipment IMAGINARY_DESTINATION = new Shipment(IMAGINARY_DESTINATION_ID);

    private final int identifier;
    private final String originCity;
    private final String destinationCity;
    private final DayOfWeek dayOfWeek;

    private Shipment(final int identifier) {
        this(identifier, null, null, DayOfWeek.M.name());
    }

    public Shipment(final int identifier,
                    final String originCity,
                    final String destinationCity,
                    final String dayOfWeek) {
        this.identifier = identifier;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.dayOfWeek = DayOfWeek.parseFromString(dayOfWeek);
    }

    public String getOriginCity() {
        return originCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Shipment shipment = (Shipment) o;
        return getIdentifier() == shipment.getIdentifier();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier());
    }

    @Override
    public String toString() {
        if (IMAGINARY_SOURCE.equals(this)) {
            return "Imaginary Source";
        }

        if (IMAGINARY_DESTINATION.equals(this)) {
            return "Imaginary Destination";
        }

        return "Shipment{" + "identifier=" + identifier + ", dayOfWeek='" + dayOfWeek.name() + '\'' +
            ", originCity='" + originCity + '\'' + ", destinationCity='" + destinationCity + '\'' + '}';
    }

    int getIdentifier() {
        return identifier;
    }

    DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
