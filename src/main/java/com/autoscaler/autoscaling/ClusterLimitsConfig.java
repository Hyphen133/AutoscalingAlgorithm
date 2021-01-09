package com.autoscaler.autoscaling;

import com.autoscaler.monitoring.VirtualClusterId;
import java.util.Map;

public class ClusterLimitsConfig {

    private Map<VirtualClusterId, ClusterLimits> limitsForCluster;

    public ClusterLimitsConfig(final Map<VirtualClusterId, ClusterLimits> limitsForCluster) {
        this.limitsForCluster = limitsForCluster;
    }

    public ClusterLimits getLimitsForCluster(VirtualClusterId id){
        return limitsForCluster.get(id);
    }
}
