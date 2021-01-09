package com.autoscaling.infastructureapi;

import java.util.Set;

public interface InfrastructureAPI {
    PhysicalInstanceStatistics getStatisticsForInstance(PhysicalInstanceId instanceId);
    Set<PhysicalInstanceId> getInstancesForCluster(PhysicalClusterId clusterId);
}
