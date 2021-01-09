package com.autoscaler.autoscaling;

import com.autoscaler.monitoring.VirtualClusterId;

public class ClusterLimits {
    private int maximalNumberOfSessions;
    private int minimalNumberOfSessions;

    public ClusterLimits(final int maximalNumberOfSessions, final int minimalNumberOfSessions) {
        this.maximalNumberOfSessions = maximalNumberOfSessions;
        this.minimalNumberOfSessions = minimalNumberOfSessions;
    }

    public int getMaximalNumberOfSessions() {
        return maximalNumberOfSessions;
    }

    public int getMinimalNumberOfSessions() {
        return minimalNumberOfSessions;
    }
}
