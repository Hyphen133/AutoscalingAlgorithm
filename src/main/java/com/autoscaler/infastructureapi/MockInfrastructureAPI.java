package com.autoscaler.infastructureapi;

import java.util.Map;
import java.util.Set;

public class MockInfrastructureAPI implements InfrastructureAPI {

    private Map<PhysicalClusterId, Set<PhysicalInstanceId>> clusterInstances;
    private Map<PhysicalInstanceId, PhysicalInstanceStatistics>  statisticsForInstance;

    public MockInfrastructureAPI(final Map<PhysicalClusterId, Set<PhysicalInstanceId>> clusterInstances, final Map<PhysicalInstanceId, PhysicalInstanceStatistics> statisticsForInstances) {
        this.clusterInstances = clusterInstances;
        this.statisticsForInstance = statisticsForInstances;
    }

    @Override
    public PhysicalInstanceStatistics getStatisticsForInstance(final PhysicalInstanceId instanceId) {
        return statisticsForInstance.get(instanceId);
    }

    @Override
    public Set<PhysicalInstanceId> getInstancesForCluster(final PhysicalClusterId clusterId) {
        return clusterInstances.get(clusterId);
    }

}
