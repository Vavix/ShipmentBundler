package com.vasily.shipmentbundler.graph;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.vasily.shipmentbundler.shipment.Schedule;
import com.vasily.shipmentbundler.shipment.Shipment;
import com.vasily.shipmentbundler.TestHelper;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ShortestPathShipmentBundlesFactoryTest {
    private final ShortestPathShipmentBundlesFactory subject = new ShortestPathShipmentBundlesFactory();

    @Test
    public void create_WithSimpleInput_ReturnsExpectedShipmentBundles() {
        // this is a no-no as we are indirectly testing another component, but in the interest of time let's reuse it
        // ideally it would be mocked and a bunch of expect statements added to define its behavior...too much work
        final Schedule schedule = TestHelper.createSimpleSchedule();
        final Graph<Shipment, ShipmentConnectionEdge> scheduleGraph = new ScheduleGraphFactory().create(schedule);

        final Set<List<Shipment>> output = subject.create(scheduleGraph);
        final Set<List<Shipment>> expectedOutput = Sets.newHashSet();
        expectedOutput.add(Lists.newArrayList(TestHelper.FIRST_CONNECTION, TestHelper.SECOND_CONNECTION));
        expectedOutput.add(Lists.newArrayList(TestHelper.NON_CONNECTING_SHIPMENT));

        assertThat(output, is(equalTo(expectedOutput)));
    }

    @Test
    public void create_WithGraphWithNoShipments_ReturnsEmptyShipmentBundles() {
        // this is a no-no as we are indirectly testing another component, but in the interest of time let's reuse it
        // ideally it would be mocked and a bunch of expect statements added to define its behavior...too much work
        final Schedule schedule = TestHelper.createSimpleSchedule();
        final Graph<Shipment, ShipmentConnectionEdge> trivialGraph
            = new DefaultDirectedGraph<>(ShipmentConnectionEdge.class);
        trivialGraph.addVertex(Shipment.IMAGINARY_SOURCE);
        trivialGraph.addVertex(Shipment.IMAGINARY_DESTINATION);

        assertThat(subject.create(trivialGraph), is(equalTo(Collections.emptySet())));
    }

}
