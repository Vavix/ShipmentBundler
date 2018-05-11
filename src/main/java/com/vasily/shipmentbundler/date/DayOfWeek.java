package com.vasily.shipmentbundler.date;

import org.jetbrains.annotations.NotNull;

public enum DayOfWeek {
    M(0), T(1), W(2), R(3), F(4);

    private final int index;

    DayOfWeek(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static DayOfWeek parseFromString(@NotNull final String dayOfWeek) {
        return DayOfWeek.valueOf(dayOfWeek);
    }
}
