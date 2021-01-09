package com.autoscaling.monitoring;

import java.util.Objects;

public class VirtualMachineId {
    private String machineId;

    public VirtualMachineId(final String machineId) {
        this.machineId = machineId;
    }

    public String getMachineId() {
        return machineId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final VirtualMachineId that = (VirtualMachineId) o;
        return Objects.equals(machineId, that.machineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(machineId);
    }
}
