package com.vasily.shipmentbundler.date;

import java.util.Arrays;
import java.util.function.Predicate;

import org.jetbrains.annotations.NotNull;

public class IsValidDayOfWeekPredicate implements Predicate<String> {
    private static final String[] VALID_DAYS_OF_WEEK = new String[] {
        DayOfWeek.M.name(), DayOfWeek.T.name(), DayOfWeek.W.name(), DayOfWeek.R.name(), DayOfWeek.F.name()
    };

    @Override
    public boolean test(@NotNull final String input) {
        return Arrays.stream(VALID_DAYS_OF_WEEK).anyMatch(input::equals);
    }
}
