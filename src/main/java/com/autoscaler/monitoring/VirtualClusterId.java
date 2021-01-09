package com.autoscaler.monitoring;

import java.util.Objects;

public class VirtualClusterId {
    private String clusterId;

    public VirtualClusterId(final String clusterId) {
        this.clusterId = clusterId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final VirtualClusterId that = (VirtualClusterId) o;
        return Objects.equals(clusterId, that.clusterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clusterId);
    }

    public String getId() {
        return clusterId;
    }
}
