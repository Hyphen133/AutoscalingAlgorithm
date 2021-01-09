package com.autoscaler.monitoring;

import java.util.Optional;
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

    public VirtualMachine getMachine(VirtualMachineId id){
        final Optional<VirtualMachine> virtualMachineOptional = machines.stream().filter(x -> x.getInstanceId().equals(id)).findFirst();
        if(virtualMachineOptional.isPresent()){
            return virtualMachineOptional.get();
        }
        throw new RuntimeException("Machine not found for id " + id.getMachineId());
    }

    public VirtualClusterId getId() {
        return id;
    }

    public int size() {
        return machines.size();
    }
}
