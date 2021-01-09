package com.autoscaling.monitoring;

import java.util.Set;

public class VirtualCluster {
    private VirtualClusterId id;
    private Set<VirtualMachine> machines;

    public VirtualCluster(final VirtualClusterId id, final Set<VirtualMachine> machines) {
        this.id = id;
        this.machines = machines;
    }

    public Set<VirtualMachine> getMachines() {
        return machines;
    }

    public VirtualClusterId getId() {
        return id;
    }
}
