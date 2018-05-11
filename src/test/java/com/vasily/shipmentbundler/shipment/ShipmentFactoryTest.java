package com.vasily.shipmentbundler.shipment;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

import com.vasily.shipmentbundler.date.DayOfWeek;
import com.vasily.shipmentbundler.TestHelper;
import org.junit.Test;

public class ShipmentFactoryTest {
    private final ShipmentFactory subject = new ShipmentFactory();

    @Test
    public void createFromInput_ReturnsExpectedShipment() {
        final Shipment output = subject.createFromInput(TestHelper.FIRST_CONNECTION_INPUT_LINE);
        assertThat(output.getOriginCity(), is(equalTo(("SEATTLE"))));
        assertThat(output.getDestinationCity(), is(equalTo(("PORTLAND"))));
        assertThat(output.getIdentifier(), is(equalTo((1))));
        assertThat(output.getDayOfWeek(), is(equalTo(DayOfWeek.T)));
    }

    @Test(expected = IllegalStateException.class)
    public void createFromInput_ThrowsIllegalStateException_WhenInvalidNumberOfArguments() {
        final String inputLine = "1 SEATTLE PORTLAND T T T";

        subject.createFromInput(inputLine);

        //should not get to this point
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void createFromInput_ThrowsIllegalStateException_WhenATokenIsEmpty() {
        final String inputLine = "1 SEATTLE PORTLAND  ";

        subject.createFromInput(inputLine);

        //should not get to this point
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void createFromInput_ThrowsIllegalStateException_WhenDayOfWeekIsNotValid() {
        final String inputLine = "1 SEATTLE PORTLAND X";

        subject.createFromInput(inputLine);

        //should not get to this point
        fail();
    }
}
