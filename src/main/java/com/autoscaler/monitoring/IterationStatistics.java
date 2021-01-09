package com.autoscaler.monitoring;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class IterationStatistics {
    private Date date;
    private Map<VirtualClusterId, VirtualCluster> virtualClusterMap;

    public IterationStatistics(final Date dateTime, final Map<VirtualClusterId, VirtualCluster> virtualClusterMap) {
        this.date = date;
        this.virtualClusterMap = virtualClusterMap;
    }

    public VirtualCluster getCluster(VirtualClusterId clusterId){
        return virtualClusterMap.get(clusterId);
    }

    public VirtualMachine getInstance(VirtualMachineId virtualMachineId){
        // TODO -> not very optimal
        for (VirtualCluster cluster : virtualClusterMap.values()) {
            try{
                return cluster.getMachine(virtualMachineId);
            }catch (RuntimeException e){
                //Try another
            }
        }
        throw new RuntimeException("Instance with id " + virtualMachineId.getMachineId() + " has not beed found! ");
    }

    public Set<VirtualCluster> getAllClusters(){
        return virtualClusterMap.values().stream().collect(Collectors.toSet());
    }
}
