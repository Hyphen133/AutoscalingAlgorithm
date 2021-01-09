package com.autoscaler.infastructureapi;

import lombok.Builder;


/*
*   This statistics are mutable like an infrastructure
 */
@Builder
public class PhysicalInstanceStatistics {
    private int activeSession;
    private int sessionNumberLimit;

    public int getActiveSession() {
        return activeSession;
    }

    public void setActiveSession(final int activeSession) {
        this.activeSession = activeSession;
    }

    public int getSessionNumberLimit() {
        return sessionNumberLimit;
    }

    public void setSessionNumberLimit(final int sessionNumberLimit) {
        this.sessionNumberLimit = sessionNumberLimit;
    }
}
