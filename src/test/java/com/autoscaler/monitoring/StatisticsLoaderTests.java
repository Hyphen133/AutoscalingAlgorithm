package com.autoscaler.monitoring;

import static org.junit.jupiter.api.Assertions.*;

import com.autoscaler.TestInfrastructureAPIFactory;
import com.autoscaler.infastructureapi.InfrastructureAPI;
import com.autoscaler.monitoring.IterationStatistics;
import com.autoscaler.monitoring.StandardStatisticsLoader;
import com.autoscaler.monitoring.VirtualClusterId;
import com.autoscaler.monitoring.VirtualMachineId;
import com.autoscaler.monitoring.StatisticsLoader;
import org.junit.jupiter.api.Test;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatisticsLoaderTests {
    @Test
    public void shouldGatherStatisticsFromInfrastructure(){
        //Given
        InfrastructureAPI infrastructureAPI  = new TestInfrastructureAPIFactory().createBasicTestInfrastructureAPI();
        VirtualClusterId virtualClusterId = new VirtualClusterId(TestInfrastructureAPIFactory.TEST_CLUSTER_NAME);
        StatisticsLoader virtualMonitor = new StandardStatisticsLoader(infrastructureAPI, Stream.of(virtualClusterId).collect(Collectors.toSet()));

        //When
        final IterationStatistics iterationStatistics = virtualMonitor.gatherStatistics();

        //Then
        assertNotEquals(iterationStatistics.getCluster(virtualClusterId), null );
        assertNotEquals(iterationStatistics.getInstance(new VirtualMachineId(TestInfrastructureAPIFactory.FIRST_INSTANCE_NAME)), null );
    }
}
