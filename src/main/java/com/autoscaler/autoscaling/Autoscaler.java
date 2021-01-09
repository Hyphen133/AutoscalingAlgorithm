package com.autoscaler.autoscaling;

public interface Autoscaler {
    AutoscalerDecision adjustInfrastructureState();
}
