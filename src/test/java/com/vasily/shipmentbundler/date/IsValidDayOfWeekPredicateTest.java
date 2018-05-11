package com.vasily.shipmentbundler.date;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class IsValidDayOfWeekPredicateTest {
    private final IsValidDayOfWeekPredicate subject = new IsValidDayOfWeekPredicate();

    @Test
    public void test_ReturnsTrue_WhenInputValidDayOfWeek() {
        assertThat(subject.test("M"), is(true));
    }

    @Test
    public void test_ReturnsFalse_WhenInputInvalidDayOfWeek() {
        assertThat(subject.test("X"), is(false));
    }
}
