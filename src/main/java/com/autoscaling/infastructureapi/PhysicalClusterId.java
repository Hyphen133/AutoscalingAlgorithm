package com.autoscaling.infastructureapi;

import java.util.Objects;

public class PhysicalClusterId {
    private final String id;

    public PhysicalClusterId(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PhysicalClusterId that = (PhysicalClusterId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
