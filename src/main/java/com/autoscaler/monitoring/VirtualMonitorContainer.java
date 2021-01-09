package com.autoscaler.monitoring;

import java.util.List;

public class VirtualMonitorContainer {

    private List<IterationStatistics> statistics;

    public synchronized void addEntry(IterationStatistics statistics){
        this.statistics.add(statistics);
    }

    public List<IterationStatistics> getLastStatistics(int numberOfStatistics){
        //TODO -> this shoudl be time dependent
        return statistics.subList(statistics.size()-numberOfStatistics, statistics.size());
    }

}
