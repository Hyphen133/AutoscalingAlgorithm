import static org.junit.jupiter.api.Assertions.*;

import com.autoscaling.infastructureapi.InfrastructureAPI;
import com.autoscaling.monitoring.IterationStatistics;
import com.autoscaling.monitoring.StandardVirtualMonitor;
import com.autoscaling.monitoring.VirtualClusterId;
import com.autoscaling.monitoring.VirtualMachineId;
import com.autoscaling.monitoring.VirtualMonitor;
import org.junit.jupiter.api.Test;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StandardVirtualMonitorTests {
    @Test
    public void shouldGatherStatisticsFromInfrastructure(){
        //Given
        InfrastructureAPI infrastructureAPI  = new TestInfrastructureAPIFactory().createBasicTestInfrastructureAPI();
        VirtualClusterId virtualClusterId = new VirtualClusterId(TestInfrastructureAPIFactory.TEST_CLUSTER_NAME);
        VirtualMonitor virtualMonitor = new StandardVirtualMonitor(infrastructureAPI, Stream.of(virtualClusterId).collect(Collectors.toSet()));

        //When
        final IterationStatistics iterationStatistics = virtualMonitor.gatherStatistics();

        //Then
        assertNotEquals(iterationStatistics.getCluster(virtualClusterId), null );
        assertNotEquals(iterationStatistics.getInstance(new VirtualMachineId(TestInfrastructureAPIFactory.FIRST_INSTANCE_NAME)), null );
    }
}
