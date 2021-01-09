package com.autoscaler.infastructureapi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InfrastructureAPITests {

    @Test
    public void idsShouldBeComparableByValue(){
        //Given
        PhysicalInstanceId id1 = new PhysicalInstanceId("id");
        PhysicalInstanceId id2 = new PhysicalInstanceId("id");

        //When
        final boolean areIdsEqual = id1.equals(id2);

        //Then
        assertTrue(areIdsEqual);
    }

}
