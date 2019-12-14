package com.pjtest.app;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

class SpotsData {
    int totalArea;
    int numberOfSpots;
    double spotsAverageArea;
    int biggestSpotArea;

    public static String genJson (SpotsData data){
        JsonObject personObject = Json.createObjectBuilder()
                .add("total_area", data.totalArea)
                .add("number_of_spots", data.numberOfSpots)
                .add("spots_average_area", data.spotsAverageArea)
                .add("biggest_spot_area", data.biggestSpotArea)
                .build();
         
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = Json.createWriter(stringWriter);
        writer.writeObject(personObject);
        writer.close();

        return stringWriter.getBuffer().toString();
    }
}