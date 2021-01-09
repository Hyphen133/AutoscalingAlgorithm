package com.autoscaler;

import com.autoscaler.infastructureapi.InfrastructureAPI;
import com.autoscaler.infastructureapi.PhysicalInstanceId;
import com.autoscaler.monitoring.IterationStatistics;
import com.autoscaler.monitoring.StandardStatisticsLoader;
import com.autoscaler.monitoring.StatisticsLoader;
import com.autoscaler.monitoring.VirtualClusterId;
import com.autoscaler.monitoring.VirtualMachineId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MutableMonitoringTests {
    @Test
    public void shouldGatherRecordStatisticChangeInInfrastructure(){
        //Given
        InfrastructureAPI infrastructureAPI = new TestInfrastructureAPIFactory().createChangingInfastructureAPI();
        VirtualClusterId virtualClusterId = new VirtualClusterId(TestInfrastructureAPIFactory.CHANGEABLE_TEST_CLUSTER);
        StatisticsLoader statisticsLoader = new StandardStatisticsLoader(infrastructureAPI, Stream.of(virtualClusterId).collect(Collectors.toSet()));
        PhysicalInstanceId instanceToBeChange = new PhysicalInstanceId(TestInfrastructureAPIFactory.CHANGEABLE_TEST_INSTANCE);

        //When
        final IterationStatistics firstStatistics = statisticsLoader.gatherStatistics();
        final IterationStatistics secondStatistics = statisticsLoader.gatherStatistics();

        //Then
        Assertions.assertNotEquals(
                firstStatistics.getInstance(new VirtualMachineId(TestInfrastructureAPIFactory.CHANGEABLE_TEST_INSTANCE)).getNumberOfActiveSession(),
                secondStatistics.getInstance(new VirtualMachineId(TestInfrastructureAPIFactory.CHANGEABLE_TEST_INSTANCE)).getNumberOfActiveSession());

    }
}
