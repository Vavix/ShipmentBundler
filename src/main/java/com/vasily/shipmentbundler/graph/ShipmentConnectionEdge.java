package com.vasily.shipmentbundler.graph;

import com.vasily.shipmentbundler.shipment.Shipment;
import org.jgrapht.graph.DefaultEdge;

public class ShipmentConnectionEdge extends DefaultEdge {
    private Shipment source;
    private Shipment target;

    public ShipmentConnectionEdge(final Shipment source, final Shipment target)
    {
        super();
        this.source = source;
        this.target = target;
    }

    @Override
    public Shipment getSource() {
        return this.source;
    }

    @Override
    public Shipment getTarget() {
        return this.target;
    }

    @Override
    public String toString() {
        return "ShipmentConnectionEdge{" + "source=" + source + ", target=" + target + '}';
    }
}
