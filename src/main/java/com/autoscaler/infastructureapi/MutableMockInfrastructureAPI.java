package com.autoscaler.infastructureapi;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
*   This api changes with every invocation and has only one instance
 */
public class MutableMockInfrastructureAPI implements InfrastructureAPI {
    private int invocationCount = 0;
    private PhysicalClusterId onlyClusterId = new PhysicalClusterId("testCluster");
    private PhysicalInstanceId onlyInstanceId = new PhysicalInstanceId("testInstance");

    @Override
    public PhysicalInstanceStatistics getStatisticsForInstance(final PhysicalInstanceId instanceId) {
        checkIfIdIsValid(instanceId);
        invocationCount+=1;
        return PhysicalInstanceStatistics.builder()
                .activeSession(invocationCount)
                .sessionNumberLimit(invocationCount+4)
                .build();
    }

    private void checkIfIdIsValid(final PhysicalInstanceId instanceId) {
        if(!instanceId.equals(onlyInstanceId)){
            throw new RuntimeException("Instance id should be equal to " + onlyInstanceId.getId());
        }
    }

    @Override
    public Set<PhysicalInstanceId> getInstancesForCluster(final PhysicalClusterId clusterId) {
        checkIfClusterIdIsValid(clusterId);
        return Stream.of(onlyInstanceId).collect(Collectors.toSet());
    }

    private void checkIfClusterIdIsValid(final PhysicalClusterId instanceId) {
        if(!instanceId.equals(onlyClusterId)){
            throw new RuntimeException("Instance id should be equal to " + onlyClusterId.getId());
        }
    }
}
