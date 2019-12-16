package com.pjtest.app;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class AnalyzerTest {
    @Test
    public void readInput() {
        Analyzer analyzer = new Analyzer();
        try {
            analyzer.readInput(new ByteArrayInputStream("[[1,1,0,1], [0,0,0,0], [0,0,0,1], [1,0,0,0]]".getBytes()));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        ArrayList<ArrayList<Integer>> expected = new ArrayList<ArrayList<Integer>>();
        expected.add(new ArrayList<Integer>(Arrays.asList(1,1,0,1)));
        expected.add(new ArrayList<Integer>(Arrays.asList(0,0,0,0)));
        expected.add(new ArrayList<Integer>(Arrays.asList(0,0,0,1)));
        expected.add(new ArrayList<Integer>(Arrays.asList(1,0,0,0)));
        assertArrayEquals("Valid array", expected.toArray(), analyzer.getList().toArray());

        analyzer = new Analyzer();
        try {
            analyzer.readInput(new ByteArrayInputStream("[[1,1,0,1], [0,0,0,0], [0,0,0,1], [1,0,0,0,5]]".getBytes()));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        assertEquals("Not square array", new ArrayList<Integer>(), analyzer.getList());
        
        analyzer = new Analyzer();
        try {
            analyzer.readInput(new ByteArrayInputStream("[a,b,c]".getBytes()));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        assertEquals("Invalid array", new ArrayList<Integer>(), analyzer.getList());

        analyzer = new Analyzer();
        try {
            analyzer.readInput(new ByteArrayInputStream("some string".getBytes()));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        assertEquals("Invalid input", new ArrayList<Integer>(), analyzer.getList());
    }

    @Test
    public void analyzeData() {
        Analyzer analyzer = new Analyzer();
    try {
        analyzer.readInput(new ByteArrayInputStream("[[0,0,0,0], [0,0,0,0], [0,0,0,0], [0,0,0,0]]".getBytes()));
    } catch(Exception e){
        System.out.println(e.getMessage());
    }
        SpotsData data = analyzer.analyzeData();
        assertEquals(0, data.totalArea);
        assertEquals(0, data.numberOfSpots);
        assertEquals(0, data.spotsAverageArea, 0.001);
        assertEquals(0, data.biggestSpotArea);

        analyzer = new Analyzer();
        try {
            analyzer.readInput(new ByteArrayInputStream("[[1,1,0,0], [1,1,0,0], [0,0,1,1], [0,0,1,1]]".getBytes()));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        data = analyzer.analyzeData();
        assertEquals(8, data.totalArea);
        assertEquals(2, data.numberOfSpots);
        assertEquals(4, data.spotsAverageArea, 0.001);
        assertEquals(4, data.biggestSpotArea);

        analyzer = new Analyzer();
        try {
            analyzer.readInput(new ByteArrayInputStream("[[1,1,0,1], [0,0,0,0], [0,0,0,1], [1,0,0,0]]".getBytes()));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        data = analyzer.analyzeData();
        assertEquals(5, data.totalArea);
        assertEquals(4, data.numberOfSpots);
        assertEquals(1.25, data.spotsAverageArea, 0.001);
        assertEquals(2, data.biggestSpotArea);

        analyzer = new Analyzer();
        try {
            analyzer.readInput(new ByteArrayInputStream("[[1,0,0,0], [0,0,1,0], [0,0,0,1], [0,0,1,1]]".getBytes()));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        data = analyzer.analyzeData();
        assertEquals(5, data.totalArea);
        assertEquals(3, data.numberOfSpots);
        assertEquals(1.666, data.spotsAverageArea, 0.001);
        assertEquals(3, data.biggestSpotArea);

        analyzer = new Analyzer();
        try {
            analyzer.readInput(new ByteArrayInputStream("[[1,0,0,0], [0,0,0,0], [0,1,0,1], [1,1,1,1]]".getBytes()));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        data = analyzer.analyzeData();
        assertEquals(7, data.totalArea);
        assertEquals(2, data.numberOfSpots);
        assertEquals(3.5, data.spotsAverageArea, 0.001);
        assertEquals(6, data.biggestSpotArea);
    }
}
