package com.autoscaler.autoscaling;

import java.util.Set;

public interface Autoscaler {
    Set<AutoscalerDecision> makeAdjustmentDecisions();
}
