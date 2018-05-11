package com.vasily.shipmentbundler;

import com.vasily.shipmentbundler.shipment.Shipment;
import com.vasily.shipmentbundler.date.DayOfWeek;
import com.vasily.shipmentbundler.shipment.Schedule;

public class TestHelper {
    public static final Shipment FIRST_CONNECTION = new Shipment(1, "Seattle", "Portland", DayOfWeek.T.name());
    public static final String FIRST_CONNECTION_INPUT_LINE = "1 SEATTLE PORTLAND T";
    public static final Shipment SECOND_CONNECTION = new Shipment(2, "Portland", "SF", DayOfWeek.W.name());
    public static final Shipment NON_CONNECTING_SHIPMENT = new Shipment(3, "SF", "LA", DayOfWeek.W.name());

    public static Schedule createSimpleSchedule() {
        final Schedule schedule = new Schedule();

        schedule.addToSchedule(FIRST_CONNECTION);
        schedule.addToSchedule(SECOND_CONNECTION);
        schedule.addToSchedule(NON_CONNECTING_SHIPMENT);
        return schedule;
    }
}
