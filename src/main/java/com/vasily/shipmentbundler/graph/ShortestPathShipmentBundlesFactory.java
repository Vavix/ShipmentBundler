package com.vasily.shipmentbundler.graph;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.vasily.shipmentbundler.shipment.Shipment;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import com.google.common.collect.Sets;

public class ShortestPathShipmentBundlesFactory {
    public Set<List<Shipment>> create(final Graph<Shipment, ShipmentConnectionEdge> scheduleGraph) {
        final Set<List<Shipment>> shipmentBundles = Sets.newHashSet();

        GraphPath<Shipment, ShipmentConnectionEdge> shortestPath;
        do {
            // run-time complexity O(|E| + |V|log|V|) for a single shortest path, O(|V|E| + |V^2|log|V|) overall
            final ShortestPathAlgorithm<Shipment, ShipmentConnectionEdge> shortestPathAlgorithm
                = new DijkstraShortestPath<>(scheduleGraph);

            shortestPath = shortestPathAlgorithm.getPath(Shipment.IMAGINARY_SOURCE,
                                                         Shipment.IMAGINARY_DESTINATION);
            if (shortestPath != null) {
                final List<Shipment> shipmentBundle = getCityVertices(shortestPath);
                // remove city vertices from graph to prepare for next shipment bundle
                scheduleGraph.removeAllVertices(shipmentBundle);
                shipmentBundles.add(shipmentBundle);
            }

        } while (shortestPath != null); // while there are still unfulfilled shipments

        return shipmentBundles;
    }

    private List<Shipment> getCityVertices(final GraphPath<Shipment, ShipmentConnectionEdge> path) {
        final List<Shipment> allVertices = path.getVertexList();
        if (allVertices.size() < 2) return Collections.emptyList();
        return allVertices.subList(1, allVertices.size() - 1);
    }
}
