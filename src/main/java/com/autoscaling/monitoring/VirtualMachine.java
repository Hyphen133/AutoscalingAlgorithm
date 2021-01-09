package com.autoscaling.monitoring;

import lombok.Builder;

/*
*   This class represents all statistics gather for single VM
*
 */
@Builder
public class VirtualMachine {
    private VirtualMachineId instanceId;
    private int numberOfActiveSession;
    private int sessionNumberLimit;
}
