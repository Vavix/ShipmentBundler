package com.vasily.shipmentbundler.graph;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.vasily.shipmentbundler.date.DayOfWeek;
import com.vasily.shipmentbundler.shipment.Shipment;
import org.junit.Test;

public class IsValidShipmentConnectionPredicateTest {
    private IsValidShipmentConnectionPredicate subject;

    @Test
    public void test_ReturnsTrue_WhenInputCitySourceEqualsToPreviousCityDestination() {
        final Shipment nextDayShipment = new Shipment(1, "Denver", "SF", DayOfWeek.T.name());
        subject = new IsValidShipmentConnectionPredicate(nextDayShipment);

        final Shipment inputPreviousDayShipment = new Shipment(0, "Seattle", "Denver", DayOfWeek.M.name());
        assertThat(subject.test(inputPreviousDayShipment), is(true));
    }

    @Test
    public void test_ReturnsFalse_WhenInputCitySourceNotEqualToPreviousCityDestination() {
        final Shipment nextDayShipment = new Shipment(1, "LA", "Seattle", DayOfWeek.T.name());
        subject = new IsValidShipmentConnectionPredicate(nextDayShipment);

        final Shipment inputPreviousDayShipment = new Shipment(0, "Seattle", "Denver", DayOfWeek.M.name());
        assertThat(subject.test(inputPreviousDayShipment), is(false));
    }
}
