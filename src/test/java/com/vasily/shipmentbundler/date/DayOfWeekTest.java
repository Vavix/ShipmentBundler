package com.vasily.shipmentbundler.date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

import org.junit.Test;

public class DayOfWeekTest {
    @Test
    public void parseFromString_ReturnsExpectedEnum_ForValidValue() {
        assertThat(DayOfWeek.parseFromString("W"), is(equalTo(DayOfWeek.W)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseFromString_ThrowsException_ForInvalidValue() {
        DayOfWeek.parseFromString("A");

        // should not reach this point
        fail("Did not throw expected exception.");
    }

    @Test
    public void getIndex_ReturnsExpectedInt() {
        final DayOfWeek friday = DayOfWeek.R;
        assertThat(friday.getIndex(), is(equalTo(3)));
    }
}
