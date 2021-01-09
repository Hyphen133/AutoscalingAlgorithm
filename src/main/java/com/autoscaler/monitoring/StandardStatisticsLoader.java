package com.autoscaler.monitoring;

import com.autoscaler.infastructureapi.InfrastructureAPI;
import com.autoscaler.infastructureapi.PhysicalClusterId;
import com.autoscaler.infastructureapi.PhysicalInstanceId;
import com.autoscaler.infastructureapi.PhysicalInstanceStatistics;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
*   This class gathers statistics for all Virtual Clusters registered
*
 */
public class StandardStatisticsLoader implements StatisticsLoader {
    private InfrastructureAPI infrastructureAPI;
    private final Set<VirtualClusterId> registeredClusterIds;

    public StandardStatisticsLoader(final InfrastructureAPI infrastructureAPI, final Set<VirtualClusterId> clusterIds) {
        this.infrastructureAPI = infrastructureAPI;
        this.registeredClusterIds = clusterIds;
    }

    public IterationStatistics gatherStatistics(){
        Set<VirtualCluster> clusters = new HashSet<>();
        for (VirtualClusterId registeredClusterId : registeredClusterIds) {
            clusters.add(gatherStatisticsForCluster(registeredClusterId));
        }
        return new IterationStatistics(new Date(), clusterSetToMap(clusters));
    }

    private VirtualCluster gatherStatisticsForCluster(final VirtualClusterId registeredClusterId) {
        Set<VirtualMachine> clusterMachines = new HashSet<>();
        PhysicalClusterId physicalClusterId = new PhysicalClusterId(registeredClusterId.getId());
        final Set<PhysicalInstanceId> clusterInstances = infrastructureAPI.getInstancesForCluster(physicalClusterId);

        for (PhysicalInstanceId instanceId : clusterInstances) {
            final PhysicalInstanceStatistics statisticsForInstance = infrastructureAPI.getStatisticsForInstance(instanceId);
            clusterMachines.add(convertPhysicalStatisticsToVirtualMachine(instanceId, statisticsForInstance));
        }
        return new VirtualCluster(new VirtualClusterId(physicalClusterId.getId()), clusterMachines);
    }

    private VirtualMachine convertPhysicalStatisticsToVirtualMachine(PhysicalInstanceId instanceId, PhysicalInstanceStatistics statistics){
        return VirtualMachine.builder()
                .instanceId(new VirtualMachineId(instanceId.getId()))
                .numberOfActiveSession(statistics.getActiveSession())
                .sessionNumberLimit(statistics.getSessionNumberLimit())
                .build();
    }

    private Map<VirtualClusterId, VirtualCluster> clusterSetToMap(Set<VirtualCluster> clusters){
        return clusters.stream().collect(Collectors.toMap(VirtualCluster::getId, cluster -> cluster, (a, b) -> b));
    }
}
