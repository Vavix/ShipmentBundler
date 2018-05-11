package com.vasily.shipmentbundler.graph;

import java.util.Set;
import java.util.function.Predicate;

import com.vasily.shipmentbundler.date.DayOfWeek;
import com.vasily.shipmentbundler.shipment.Schedule;
import com.vasily.shipmentbundler.shipment.Shipment;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

public class ScheduleGraphFactory {
    private final int CITY_TO_CITY_COST = 0;

    public Graph<Shipment, ShipmentConnectionEdge> create(final Schedule schedule) {
        final Graph<Shipment, ShipmentConnectionEdge> graph
            = new DefaultDirectedWeightedGraph<>(ShipmentConnectionEdge.class);
        addVertices(graph, schedule);
        addPenaltyEdges(graph, schedule);
        addEdgesForPotentialCityConnections(graph, schedule);

        return graph;
    }

    private void addVertices(final Graph<Shipment, ShipmentConnectionEdge> graph,
                             final Schedule schedule) {
        // These vertices serve two functions: 1. Act as entry and exit point for shortest paths later calculated.
        // and 2. Connect to all city intermediate vertices with penalty edges to "discourage" use for shortest path.
        graph.addVertex(Shipment.IMAGINARY_SOURCE);
        graph.addVertex(Shipment.IMAGINARY_DESTINATION);

        schedule.getAllShipments().forEach(graph::addVertex);
    }

    private void addEdgesForPotentialCityConnections(final Graph<Shipment, ShipmentConnectionEdge> graph,
                                                     final Schedule schedule) {
        for (int dayIndex = DayOfWeek.T.getIndex(); dayIndex <= DayOfWeek.F.getIndex(); ++dayIndex) {
            final Set<Shipment> shipmentsForCurrentDay = schedule.getShipments(dayIndex);
            final Set<Shipment> shipmentsForPreviousDay = schedule.getShipments(dayIndex - 1);
            addEdgesForPotentialCityConnectionsForDay(graph, shipmentsForCurrentDay, shipmentsForPreviousDay);
        }
    }

    private void addEdgesForPotentialCityConnectionsForDay(final Graph<Shipment, ShipmentConnectionEdge> graph,
                                                           final Set<Shipment> shipmentsForCurrentDay,
                                                           final Set<Shipment> shipmentsForPreviousDay) {
        for (final Shipment shipment : shipmentsForCurrentDay) {
            final Predicate<Shipment> isValidShipmentConnectionPredicate
                = new IsValidShipmentConnectionPredicate(shipment);
            shipmentsForPreviousDay.stream()
                                   .filter(isValidShipmentConnectionPredicate)
                                   .forEach(previousDayShipment -> this.addWeightedEdge(graph,
                                                                                        previousDayShipment,
                                                                                        shipment,
                                                                                        CITY_TO_CITY_COST));
        }
    }

    private void addPenaltyEdges(final Graph<Shipment, ShipmentConnectionEdge> graph,
                                 final Schedule schedule) {
        int penaltyForNoPreviousChainedCity = 1;
        int penaltyForNoNextChainedCity = 5;

        for (int dayIndex = DayOfWeek.M.getIndex(); dayIndex <= DayOfWeek.F.getIndex(); ++dayIndex) {
            final Set<Shipment> shipmentsForDay = schedule.getShipments(dayIndex);

            // Penalty weight for skipping 4 days of next-day connections is the highest at 5 and decreases by 1 each
            // subsequent day. Conversely we start at lowest penalty of 1 for previous-day connections and increment
            for (final Shipment shipment : shipmentsForDay) {
                addWeightedEdge(graph, shipment, Shipment.IMAGINARY_DESTINATION, penaltyForNoNextChainedCity);
                addWeightedEdge(graph, Shipment.IMAGINARY_SOURCE, shipment, penaltyForNoPreviousChainedCity);
            }

            --penaltyForNoNextChainedCity;
            ++penaltyForNoPreviousChainedCity;
        }
    }

    private void addWeightedEdge(final Graph<Shipment, ShipmentConnectionEdge> graph,
                                 final Shipment source,
                                 final Shipment destination,
                                 final int weightValue) {
        final ShipmentConnectionEdge edge = new ShipmentConnectionEdge(source, destination);
        graph.addEdge(source, destination, edge);
        graph.setEdgeWeight(edge, weightValue);
    }
}
