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
//        for (VirtualCluster value : virtualClusterMap.values()) {
//            for (VirtualClusterId virtualClusterId : value.g) {
//
//            }
//        }
        return null;
    }

}
