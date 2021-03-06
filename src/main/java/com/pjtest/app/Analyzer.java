package com.pjtest.app;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;

class Analyzer {
    // Original input array 
    private ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
    // Map of the points (points make spots)
    private Map<String, int[]> points = new HashMap<String, int[]>();
    // Map of the spots (group of points)
    private Map<String, Integer> spots = new HashMap<String, Integer>();

    public ArrayList<ArrayList<Integer>> getList(){
        return this.list;
    }

    public void readInput(InputStream input) throws InvalidMatrixException, Exception {
        JsonReader reader = Json.createReader(input);
        JsonArray bodyArray = reader.readArray();
        reader.close();

        // Check if square
        if(bodyArray.size() < 2){
            throw new InvalidMatrixException();
        }

        // Mount array based on input
        for (int x = 0; x < bodyArray.size(); x++) {
            JsonArray arr = bodyArray.getJsonArray(x);

            // check if square
            if(bodyArray.size() != arr.size()){
                this.list = new ArrayList<ArrayList<Integer>>();
                throw new InvalidMatrixException();
            }

            ArrayList<Integer> local = new ArrayList<Integer>();
            for (int y = 0; y < arr.size(); y++) {
                try{
                    local.add(arr.getInt(y));
                } catch(Exception e) {
                    throw new InvalidMatrixException();
                }
            }
            this.list.add(local);
        }
    }

    public SpotsData analyzeData(){
        SpotsData data = new SpotsData();

        // Mount map of points
        for(int x = 0; x < this.list.size(); x++){
            for(int y = 0; y < this.list.get(x).size(); y++){
                int item = this.list.get(x).get(y);
                if(item == 1){
                    this.points.put(x + "-" + y, new int[]{x, y});
                }
            }
        }
        data.totalArea = this.points.size();

        // Mount map of spots
        for(Map.Entry<String, int[]> point : this.points.entrySet()){
            if(spots.containsKey(point.getValue()[0] + "-" + point.getValue()[1])){
                continue;
            }
            trackSpots(point.getValue(), true);
        }
        data.numberOfSpots = actualSpot();


        if(data.totalArea == 0){
            data.spotsAverageArea = 0;
        }else{
            data.spotsAverageArea = (double) data.totalArea / data.numberOfSpots;
        }

        int max = 0;
        // Get the biggest spot
        for(Integer spot : this.spots.values()){
            int temp = Collections.frequency(this.spots.values(), spot);
            if(max < temp){
                max = temp;
            }
        }

        data.biggestSpotArea = max;

        return data;
    }

    private void trackSpots(int[] point, boolean first) {
        String atual = point[0] + "-" + point[1];

        String[] neighbors = new String[]{
            point[0] + "-" + (point[1] + 1),
            (point[0] + 1) + "-" + point[1]
        };

        // Check if neighbor y + 1
        // Check if neighbor x + 1
        for(String nb : neighbors){
            if(!this.points.containsKey(nb)){
                continue;
            }

            if(spots.containsKey(nb)){
                spots.put(atual, spots.get(nb));
                return;
            }else{
                int spot = actualSpot();
                if(first){
                    spot = actualSpot() + 1;
                }
                spots.put(atual, spot);
                spots.put(nb, spot);
                trackSpots(this.points.get(nb), false);
                return;
            }
        }

        if(first){
            spots.put(atual, actualSpot() + 1);
        }
    }

    private int actualSpot(){
        if(this.spots.size() == 0){
            return 0;
        }

        return Collections.max(this.spots.values());
    }
}