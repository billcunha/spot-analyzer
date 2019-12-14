package com.pjtest.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SpotsDataTest {
    @Test
    public void readInput() {
        SpotsData data = new SpotsData();
        data.biggestSpotArea = 6;
        data.numberOfSpots = 2;
        data.totalArea = 7;
        data.spotsAverageArea = 3.5;
        assertEquals("{\"total_area\":7,\"number_of_spots\":2,\"spots_average_area\":3.5,\"biggest_spot_area\":6}", SpotsData.genJson(data));
    }
}