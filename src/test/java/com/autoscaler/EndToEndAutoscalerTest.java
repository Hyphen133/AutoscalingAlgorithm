package com.autoscaler;

import com.autoscaler.autoscaling.Autoscaler;
import com.autoscaler.autoscaling.SessionBasedAutoscalingAlgorithm;
import com.autoscaler.autoscaling.AutoscalerDecision;
import com.autoscaler.autoscaling.ClusterLimits;
import com.autoscaler.autoscaling.ClusterLimitsConfig;
import com.autoscaler.infastructureapi.InfrastructureAPI;
import com.autoscaler.monitoring.StandardStatisticsLoader;
import com.autoscaler.monitoring.StatisticsLoader;
import com.autoscaler.monitoring.VirtualClusterId;
import com.autoscaler.monitoring.VirtualMonitorContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EndToEndAutoscalerTest {
    @Test
    public void sessionBasedAlgorithmShouldDecreaseLoad(){
        ClusterLimitsConfig limitsConfig = new ClusterLimitsConfig(Map.of(new VirtualClusterId(TestInfrastructureAPIFactory.TEST_CLUSTER_NAME), new ClusterLimits(15, 3)));
        //Given
        VirtualMonitorContainer monitorContainer = createVirtualMonitorContainer();
        Autoscaler autoscaler = new SessionBasedAutoscalingAlgorithm(monitorContainer, limitsConfig, 0.8, 0.2);

        //When
        final Set<AutoscalerDecision> autoscalerDecisions = autoscaler.makeAdjustmentDecisions();

        //Then
        algorithmDecidesToIncreaseLoad(autoscalerDecisions);
    }

    private void algorithmDecidesToIncreaseLoad(final Set<AutoscalerDecision> autoscalerDecisions) {
        Assertions.assertEquals(1, autoscalerDecisions.stream().findFirst().get().getNumberOfMachinesToAdd());
    }

    private VirtualMonitorContainer createVirtualMonitorContainer() {
        final InfrastructureAPI infrastructureAPI = new TestInfrastructureAPIFactory().createOverloadedTestInfrastructureAPI();
        final Set<VirtualClusterId> registeredIds = Stream.of(new VirtualClusterId(TestInfrastructureAPIFactory.TEST_CLUSTER_NAME)).collect(Collectors.toSet());
        StatisticsLoader statisticsLoader = new StandardStatisticsLoader(infrastructureAPI, registeredIds);
        VirtualMonitorContainer monitorContainer = new VirtualMonitorContainer();
        monitorContainer.addEntry(statisticsLoader.gatherStatistics());
        return monitorContainer;
    }
}
