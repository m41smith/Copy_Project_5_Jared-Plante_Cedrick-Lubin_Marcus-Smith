package com.project5.project_5_jaredplante_cedricklubin_marcussmith;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import com.google.gson.Gson;

public class GOTDataHandler {
    private HttpClient dataGrabber;
    private String webLocation;

    public GOTDataHandler(String webLocation) {
        dataGrabber = HttpClient.newHttpClient();
        this.webLocation = webLocation;
    }
    public GOTDataType[] getData(){
        var httpbuilder = HttpRequest.newBuilder();
        var dataRequest = httpbuilder.uri(URI.create(webLocation)).build();
        HttpResponse<String> response= null;
        try{
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        }catch (IOException exception){
            System.out.println("Error with the network");
        }catch (InterruptedException e){
            System.out.println("Error completing data transfer");
        }
        if(response == null){
            System.out.println("Something went very wrong, quitting program");
            System.exit(-1);
        }
        var responseBody = response.body();
        var jsonInterpreter = new Gson();
        var GOTData = jsonInterpreter.fromJson(responseBody, GOTDataType[].class);
        return GOTData;
    }
    public GOTCharDataType[] getcharData(){
        var httpbuilder = HttpRequest.newBuilder();
        var dataRequest = httpbuilder.uri(URI.create(webLocation)).build();
        HttpResponse<String> response= null;
        try{
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        }catch (IOException exception){
            System.out.println("Error with the network");
        }catch (InterruptedException e){
            System.out.println("Error completing data transfer");
        }
        if(response == null){
            System.out.println("Something went very wrong, quitting program");
            System.exit(-1);
        }
        var responseBody = response.body();
        var jsonInterpreter = new Gson();
        var GOTCharData = jsonInterpreter.fromJson(responseBody, GOTCharDataType[].class);
        return GOTCharData;
    }
    public GOTCharDataType getSingleCharData(){
        var httpbuilder = HttpRequest.newBuilder();
        var dataRequest = httpbuilder.uri(URI.create(webLocation)).build();
        HttpResponse<String> response= null;
        try{
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        }catch (IOException exception){
            System.out.println("Error with the network");
        }catch (InterruptedException e){
            System.out.println("Error completing data transfer");
        }
        if(response == null){
            System.out.println("Something went very wrong, quitting program");
            System.exit(-1);
        }
        var responseBody = response.body();
        var jsonInterpreter = new Gson();
        var GOTCharData = jsonInterpreter.fromJson(responseBody, GOTCharDataType.class);
        return GOTCharData;
    }

    class GOTDataType{
        String name;
        String isbn;
        Integer numberOfPages;
        ArrayList<String> povCharacters;
        @Override
        public String toString(){
            return name;

        }
    }
    class GOTCharDataType{
        String name;
        String born;
        String died;
        ArrayList<String> playedBy;
        @Override
        public String toString(){
            return name;
        }
    }
}
