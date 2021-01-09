package com.autoscaling.monitoring;

import lombok.Builder;
import lombok.Getter;

/*
*   This class represents all statistics gather for single VM
*
 */
@Builder
@Getter
public class VirtualMachine {
    private VirtualMachineId instanceId;
    private int numberOfActiveSession;
    private int sessionNumberLimit;
}
