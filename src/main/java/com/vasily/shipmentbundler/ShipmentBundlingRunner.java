package com.vasily.shipmentbundler;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vasily.shipmentbundler.shipment.Schedule;
import com.vasily.shipmentbundler.shipment.Shipment;
import org.apache.commons.lang3.StringUtils;
import org.jgrapht.Graph;

import com.vasily.shipmentbundler.graph.ShipmentConnectionEdge;
import com.vasily.shipmentbundler.graph.ScheduleGraphFactory;
import com.vasily.shipmentbundler.graph.ShortestPathShipmentBundlesFactory;
import com.vasily.shipmentbundler.shipment.BundlePrinter;
import com.vasily.shipmentbundler.shipment.ScheduleFactory;

public class ShipmentBundlingRunner {
    private final static Logger logger = Logger.getLogger(ShipmentBundlingRunner.class.getCanonicalName());

    public static void main(final String[] args) {
        if (!isValidInput(args)) {
            printUsage();
            return;
        }

        final String inputFileName = args[0];
        final Schedule schedule;
        try {
            schedule = new ScheduleFactory().create(inputFileName);
        } catch (final IOException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "IOException while parsing input file, aborting.", e);
            return;
        }

        final Graph<Shipment, ShipmentConnectionEdge> shipmentScheduleGraph
            = new ScheduleGraphFactory().create(schedule);

        final Set<List<Shipment>> shipmentBundles
            = new ShortestPathShipmentBundlesFactory().create(shipmentScheduleGraph);

        new BundlePrinter().printToStdOut(shipmentBundles);
    }

    private static boolean isValidInput(final String[] arguments) {
        if (arguments.length != 1) return false;
        return !StringUtils.isEmpty(arguments[0]);
    }

    private static void printUsage() {
        System.out.println("Usage (from .class file root): java <classpath java args> "
                               + "ShipmentBundlingRunner <test_file_path>");
    }
}
