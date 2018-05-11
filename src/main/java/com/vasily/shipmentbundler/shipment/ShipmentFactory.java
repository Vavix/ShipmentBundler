package com.vasily.shipmentbundler.shipment;

import java.util.Arrays;
import java.util.function.Predicate;

import com.vasily.shipmentbundler.date.IsValidDayOfWeekPredicate;
import org.jetbrains.annotations.NotNull;

class ShipmentFactory {
    private static final String INPUT_SEPARATOR = " ";
    private static final int IDENTIFIER_INDEX = 0;
    private static final int ORIGIN_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;
    private static final int DAY_OF_WEEK_INDEX = 3;
    private static final Predicate<String> isValidDayOfWeekPredicate = new IsValidDayOfWeekPredicate();

    Shipment createFromInput(@NotNull final String inputLine) {
        final String[] tokens = inputLine.split(INPUT_SEPARATOR);

        validateInput(tokens);

        final int identifier = Integer.parseInt(tokens[IDENTIFIER_INDEX]);
        final String origin = tokens[ORIGIN_INDEX];
        final String destination = tokens[DESTINATION_INDEX];
        final String dayOfWeek = tokens[DAY_OF_WEEK_INDEX];

        return new Shipment(identifier, origin, destination, dayOfWeek);
    }

    private void validateInput(final String[] tokens) {
        if (tokens.length != 4) {
            throw new IllegalStateException("invalid number of arguments on line");
        }

        if (Arrays.stream(tokens).anyMatch(String::isEmpty)) {
            throw new IllegalStateException("invalid input format");
        }

        if (!isValidDayOfWeekPredicate.test(tokens[DAY_OF_WEEK_INDEX])) {
            throw new IllegalStateException("days of week not in expected format. Aborting.");
        }
    }
}
