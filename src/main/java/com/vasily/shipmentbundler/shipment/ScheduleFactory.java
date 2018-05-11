package com.vasily.shipmentbundler.shipment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ScheduleFactory {
    public Schedule create(final String fileName) throws IOException {
        final Schedule schedule = new Schedule();
        final ShipmentFactory shipmentFactory = new ShipmentFactory();

        final Stream<String> shipmentLineStream = Files.lines(Paths.get(fileName));
        shipmentLineStream.map(shipmentFactory::createFromInput)
                          .forEach(schedule::addToSchedule);

        return schedule;
    }
}
