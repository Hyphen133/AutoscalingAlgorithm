package com.autoscaler.autoscaler;

import com.autoscaler.monitoring.StatisticsLoader;

public class AutoscalerAlgotihm1 implements Autoscaler {

    private StatisticsLoader monitor;

    public AutoscalerAlgotihm1(StatisticsLoader monitor) {
        this.monitor = monitor;
    }


    @Override
    public AutoscalerDecision adjustInfrastructureState() {
        return null;
    }
}
