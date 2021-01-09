package com.autoscaling.monitoring;

import java.util.Map;

public class IterationStatistics {
    private Map<VirtualClusterId, VirtualCluster> virtualClusterMap;

    public IterationStatistics(final Map<VirtualClusterId, VirtualCluster> virtualClusterMap) {
        this.virtualClusterMap = virtualClusterMap;
    }

    public VirtualCluster getCluster(VirtualClusterId clusterId){
        return virtualClusterMap.get(clusterId);
    }

    public VirtualMachine getInstance(VirtualMachineId virtualMachineId){
        // TODO -> not very optimal
        for (VirtualCluster cluster : virtualClusterMap.values()) {
            for (VirtualMachine machine : cluster.getMachines()) {
                if(machine.getInstanceId().equals(virtualMachineId)){
                    return machine;
                }
            }
        }
        throw new RuntimeException("Instance with id " + virtualMachineId.getMachineId() + " has not beed found! ");
    }
}
