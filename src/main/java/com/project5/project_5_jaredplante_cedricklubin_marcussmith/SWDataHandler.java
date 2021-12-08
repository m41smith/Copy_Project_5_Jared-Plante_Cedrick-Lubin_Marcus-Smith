package com.project5.project_5_jaredplante_cedricklubin_marcussmith;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class SWDataHandler {
    private HttpClient dataGrabber;
    private String webLocation;

    public SWDataHandler(String webLocation){
        dataGrabber = HttpClient.newHttpClient();
        this.webLocation = webLocation;
    }

    public StarWarsDataType getData() {
        var httpbuilder = HttpRequest.newBuilder();
        var dataRequest = httpbuilder.uri(URI.create(webLocation)).build();
        HttpResponse<String> response = null;
        try {
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException exception) {
            System.out.println("Error with the network");
        }
        catch (InterruptedException e) {
            System.out.println("Error completing data transfer");
        }
        if (response == null) {
            System.out.println("Something went very wrong, quitting program");
            System.exit(-1);
        }
        var responseBody = response.body();
        var jsonInterpreter = new Gson();
        var StarWarsData = jsonInterpreter.fromJson(responseBody, StarWarsDataType.class);
        return StarWarsData;
    }

    class StarWarsDataType{
        String name;
        String height;
        String birth_year;
        String homeworld;
        ArrayList<String> films;

        @Override
        public String toString(){
            return name;
        }
    }
}
