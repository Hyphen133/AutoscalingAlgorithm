package com.autoscaler.infastructureapi;

import lombok.Builder;
import lombok.Data;


/*
*   This statistics are mutable like an infrastructure
 */
@Builder
@Data
public class PhysicalInstanceStatistics {
    private int activeSession;
    private int sessionNumberLimit;
}
