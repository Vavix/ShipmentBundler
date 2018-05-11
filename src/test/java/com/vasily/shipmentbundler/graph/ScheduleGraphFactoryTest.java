package com.vasily.shipmentbundler.graph;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.vasily.shipmentbundler.shipment.Schedule;
import com.vasily.shipmentbundler.shipment.Shipment;
import com.vasily.shipmentbundler.TestHelper;
import org.jgrapht.Graph;
import org.junit.Test;

public class ScheduleGraphFactoryTest {
    private final ScheduleGraphFactory subject = new ScheduleGraphFactory();

    @Test
    public void create_CreatesExpectedScheduleGraph() {
        final Schedule schedule = TestHelper.createSimpleSchedule();
        final Graph<Shipment, ShipmentConnectionEdge> output = subject.create(schedule);

        assertThat(output.vertexSet().size(), is(5));
        assertThat(output.edgeSet().size(), is(7));
        // TODO make more specific assertions about what the vertices/edges actually are
    }
}
