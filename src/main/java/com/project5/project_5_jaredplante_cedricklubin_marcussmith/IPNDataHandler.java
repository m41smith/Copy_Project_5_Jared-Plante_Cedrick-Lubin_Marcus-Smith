package com.project5.project_5_jaredplante_cedricklubin_marcussmith;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class IPNDataHandler {


    /*public static void main(String[] args)
     {
         var comp152Inc = new IPNDataHandler("http://country.io/names.json");
         var comp152Inc2 = new IPNDataHandler("http://country.io/phone.json");
         System.out.println(comp152Inc.getData().toString());
         System.out.println(comp152Inc2.getData().toString());

         System.out.println(comp152Inc2.getData().keySet().toString());
         System.out.println(comp152Inc2.getData().values().toArray());
     }*/



    private HttpClient dataGrabber;
    private String webLocation;

    public IPNDataHandler(String webLocation){
        dataGrabber = HttpClient.newHttpClient();
        this.webLocation = webLocation;
    }



    public Map getData(){
        var httpBuilder = HttpRequest.newBuilder();
        var dataRequest = httpBuilder.uri(URI.create(webLocation)).build();
        HttpResponse<String> response = null;
        try{
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException exception){
            System.out.println("Error with the network");
        }
        catch(InterruptedException e){
            System.out.println("Error completing data transfer");
        }
        if(response == null){
            System.out.println("Something went wrong, quitting program");
            System.exit(-1);
        }
        var responseBody = response.body();
        var gson = new Gson();
        var Data = gson.fromJson(responseBody,Map.class);
        return Data;
    }
}
