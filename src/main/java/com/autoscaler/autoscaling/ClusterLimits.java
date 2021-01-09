package com.autoscaler.autoscaling;

import com.autoscaler.monitoring.VirtualClusterId;

public class ClusterLimits {
    private int maximalNumberOfSessions;
    private int minimalNumberOfSessions;

    public int getMaximalNumberOfSessions() {
        return maximalNumberOfSessions;
    }

    public int getMinimalNumberOfSessions() {
        return minimalNumberOfSessions;
    }
}
