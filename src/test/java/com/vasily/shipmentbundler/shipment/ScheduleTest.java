package com.vasily.shipmentbundler.shipment;

import static com.vasily.shipmentbundler.TestHelper.FIRST_CONNECTION;
import static com.vasily.shipmentbundler.TestHelper.NON_CONNECTING_SHIPMENT;
import static com.vasily.shipmentbundler.TestHelper.SECOND_CONNECTION;
import static com.vasily.shipmentbundler.TestHelper.createSimpleSchedule;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Collections;
import org.junit.Test;
import com.google.common.collect.Sets;
import com.vasily.shipmentbundler.date.DayOfWeek;

public class ScheduleTest {
    @Test
    public void addToSchedule_AndThen_GetShipments_ReturnsExpectedShipments() {
        final Schedule schedule = createSimpleSchedule();

        assertThat(schedule.getShipments(DayOfWeek.T.getIndex()),
                   is(equalTo(Collections.singleton(FIRST_CONNECTION))));
        assertThat(schedule.getShipments(DayOfWeek.W.getIndex()),
                   is(equalTo(Sets.newHashSet(SECOND_CONNECTION, NON_CONNECTING_SHIPMENT))));
    }

    @Test
    public void addToSchedule_AndThen_GetAllShipments_ReturnsExpectedShipments() {
        final Schedule schedule = createSimpleSchedule();

        assertThat(schedule.getAllShipments(),
                   is(equalTo(Sets.newHashSet(FIRST_CONNECTION, SECOND_CONNECTION, NON_CONNECTING_SHIPMENT))));
    }
}
