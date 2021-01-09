package com.autoscaling.infastructureapi;

import java.util.Objects;

public class PhysicalInstanceId {
    private String id;

    public PhysicalInstanceId(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PhysicalInstanceId id1 = (PhysicalInstanceId) o;
        return Objects.equals(id, id1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
