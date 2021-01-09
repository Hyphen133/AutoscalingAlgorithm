import com.autoscaler.infastructureapi.PhysicalClusterId;
import com.autoscaler.infastructureapi.InfrastructureAPI;
import com.autoscaler.infastructureapi.PhysicalInstanceId;
import com.autoscaler.infastructureapi.MockInfrastructureAPI;
import com.autoscaler.infastructureapi.PhysicalInstanceStatistics;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestInfrastructureAPIFactory {
    public static String TEST_CLUSTER_NAME = "cluster1";
    public static String FIRST_INSTANCE_NAME = "instance1";
    public static String SECOND_INSTANCE_NAME = "instance1";
    public static String THIRD_INSTANCE_NAME = "instance1";


    public InfrastructureAPI createBasicTestInfrastructureAPI(){
        final Map<PhysicalClusterId, Set<PhysicalInstanceId>> clusterInstances = new HashMap<>();
        PhysicalClusterId clusterId = new PhysicalClusterId(TEST_CLUSTER_NAME);
        PhysicalInstanceId firstInstanceId = new PhysicalInstanceId(FIRST_INSTANCE_NAME);
        PhysicalInstanceId secondInstanceId = new PhysicalInstanceId(SECOND_INSTANCE_NAME);
        PhysicalInstanceId thirdInstanceId = new PhysicalInstanceId(THIRD_INSTANCE_NAME);

        clusterInstances.put(clusterId,Stream.of(firstInstanceId, secondInstanceId, thirdInstanceId).collect(Collectors.toSet()));

        final Map<PhysicalInstanceId, PhysicalInstanceStatistics> statisticsForInstances = new HashMap<>();

        PhysicalInstanceStatistics firstInstanceStatistics = PhysicalInstanceStatistics.builder()
                .activeSession(10).sessionNumberLimit(20).build();
        PhysicalInstanceStatistics secondInstanceStatistics = PhysicalInstanceStatistics.builder()
                .activeSession(3).sessionNumberLimit(3).build();
        PhysicalInstanceStatistics thirdInstanceStatistics = PhysicalInstanceStatistics.builder()
                .activeSession(5).sessionNumberLimit(5).build();

        statisticsForInstances.put(firstInstanceId, firstInstanceStatistics);
        statisticsForInstances.put(secondInstanceId, secondInstanceStatistics);
        statisticsForInstances.put(thirdInstanceId, thirdInstanceStatistics);

        return new MockInfrastructureAPI(clusterInstances, statisticsForInstances);

    }
}
