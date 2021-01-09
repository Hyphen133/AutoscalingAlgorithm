package com.autoscaler.autoscaling;

import com.autoscaler.monitoring.VirtualClusterId;
import com.autoscaler.monitoring.VirtualMachineId;
import java.util.Set;

public class AutoscalerDecision {
    private VirtualClusterId clusterId;
    private Set<VirtualMachineId> machinesToRemove;
    private int numberOfMachinesToAdd;

    public AutoscalerDecision(final VirtualClusterId clusterId, final Set<VirtualMachineId> machinesToRemove, final int numberOfMachinesToAdd) {
        this.clusterId = clusterId;
        this.machinesToRemove = machinesToRemove;
        this.numberOfMachinesToAdd = numberOfMachinesToAdd;
    }

    public VirtualClusterId getClusterId() {
        return clusterId;
    }

    public Set<VirtualMachineId> getMachinesToRemove() {
        return machinesToRemove;
    }

    public int getNumberOfMachinesToAdd() {
        return numberOfMachinesToAdd;
    }
}
