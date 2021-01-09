package com.autoscaler.autoscaling;

import com.autoscaler.monitoring.IterationStatistics;
import com.autoscaler.monitoring.VirtualCluster;
import com.autoscaler.monitoring.VirtualMachine;
import com.autoscaler.monitoring.VirtualMachineId;
import com.autoscaler.monitoring.VirtualMonitorContainer;
import java.util.HashSet;
import java.util.Set;

public class AutoscalerAlgorithm1 implements Autoscaler {

    private VirtualMonitorContainer monitor;
    private ClusterLimitsConfig clusterLimits;
    private double sessionUpperBound;
    private double sessionLowerBound;

    public AutoscalerAlgorithm1(final VirtualMonitorContainer monitor, final ClusterLimitsConfig clusterLimits, final double sessionUpperBound, final double sessionLowerBound) {
        this.monitor = monitor;
        this.clusterLimits = clusterLimits;
        this.sessionUpperBound = sessionUpperBound;
        this.sessionLowerBound = sessionLowerBound;
    }

    @Override
    public Set<AutoscalerDecision> makeAdjustmentDecisions() {
        final IterationStatistics lastIterationStatistics = this.monitor.getLastStatistics(1).get(0);

        Set<AutoscalerDecision> decisions = new HashSet<>();

        for (VirtualCluster cluster : lastIterationStatistics.getAllClusters()) {

            int numberOfMachinesAboveUpperThreshold = 0;
            int numberOfMachinesBelowLowerThreshold = 0;

            int numberOfMachinesToAdd = 0;
            Set<VirtualMachine> machinesToRemove = new HashSet<>();
            ClusterLimits limits = clusterLimits.getLimitsForCluster(cluster.getId());


            for (VirtualMachine machine : cluster.getMachines()) {
                if(machine.getNumberOfActiveSession() / limits.getMaximalNumberOfSessions() >= sessionUpperBound){
                    numberOfMachinesAboveUpperThreshold += 1;
                }

                if(machine.getNumberOfActiveSession() / limits.getMinimalNumberOfSessions() <= sessionLowerBound){
                    numberOfMachinesBelowLowerThreshold += 1;
                    machinesToRemove.add(machine);
                }
            }

            if( numberOfMachinesAboveUpperThreshold == cluster.size()){
                numberOfMachinesToAdd += 1;
            }

            Set<VirtualMachineId> machineIdsToRemove = new HashSet<>();
            if(numberOfMachinesBelowLowerThreshold >= 2){
                for (VirtualMachine machine : machinesToRemove) {
                    if(machine.getNumberOfActiveSession() == 0){
                        machineIdsToRemove.add(machine.getInstanceId());
                    }
                }
            }

            if(!(machineIdsToRemove.size() ==0 && numberOfMachinesToAdd == 0 ))
            decisions.add(new AutoscalerDecision(cluster.getId(), machineIdsToRemove, numberOfMachinesToAdd));

        }
        return decisions;
    }
}
