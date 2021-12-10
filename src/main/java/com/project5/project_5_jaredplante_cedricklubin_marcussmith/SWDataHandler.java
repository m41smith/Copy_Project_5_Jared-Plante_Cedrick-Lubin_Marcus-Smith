//Marcus Smith

package com.project5.project_5_jaredplante_cedricklubin_marcussmith;

//imports needed packages
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
    private ArrayList<SWDataHandler.StarWarsFilmDataType> filmArrayList;

    public SWDataHandler(String webLocation){
        dataGrabber = HttpClient.newHttpClient();
        this.webLocation = webLocation;
    }

    //Gets character data from the API
    public StarWarsDataType getData() {
        var httpbuilder = HttpRequest.newBuilder();
        var dataRequest = httpbuilder.uri(URI.create(webLocation)).build();
        HttpResponse<String> response = null;
        try {
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        }
        //catches Input/output errors
        catch (IOException exception) {
            System.out.println("Error with the network");
        }
        //catches connection interruption errors
        catch (InterruptedException e) {
            System.out.println("Error completing data transfer");
        }
        if (response == null) {
            System.out.println("Something went very wrong, quitting program");
            System.exit(-1);
        }
        var responseBody = response.body();
        var jsonInterpreter = new Gson();
        var StarWarsData = jsonInterpreter.fromJson(responseBody, StarWarsResponse.class);
        return StarWarsData.results[0];
    }

    //Gets planet data from the API
    public StarWarsPlanetDataType getPlanetData(){
        var httpbuilder = HttpRequest.newBuilder();
        var dataRequest = httpbuilder.uri(URI.create(getData().homeworld)).build();
        HttpResponse<String> response = null;
        try {
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        }
        //catches Input/output errors
        catch (IOException exception) {
            System.out.println("Error with the network");
        }
        //catches connection interruption errors
        catch (InterruptedException e) {
            System.out.println("Error completing data transfer");
        }
        if (response == null) {
            System.out.println("Something went very wrong, quitting program");
            System.exit(-1);
        }
        var responseBody = response.body();
        var jsonInterpreter = new Gson();
        var planetData = jsonInterpreter.fromJson(responseBody, StarWarsPlanetDataType.class);
        return planetData;
    }

    //Gets film data from the API
    public ArrayList<StarWarsFilmDataType> getFilmData(){
        filmArrayList = new ArrayList<SWDataHandler.StarWarsFilmDataType>();
        var httpbuilder = HttpRequest.newBuilder();
        var filmLinkList = getData().films;
        var listTotal = filmLinkList.stream().count();
        var i = 0;
        //checks each available film api for their movie title
        while (i < listTotal){
            var link = filmLinkList.get(i);
            System.out.println(link);
            var dataRequest = httpbuilder.uri(URI.create(link)).build();
            HttpResponse<String> response = null;
            try {
                response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
            }
            //catches Input/output errors
            catch (IOException exception) {
                System.out.println("Error with the network");
            }
            //catches connection interruption errors
            catch (InterruptedException e) {
                System.out.println("Error completing data transfer");
            }
            if (response == null) {
                System.out.println("Something went very wrong, quitting program");
                System.exit(-1);
            }
            var responseBody = response.body();
            var jsonInterpreter = new Gson();
            var filmData = jsonInterpreter.fromJson(responseBody, StarWarsFilmDataType.class);
            filmArrayList.add(filmData);
            i++;
        }
        var filmDataList = filmArrayList;
        return filmDataList;
    }

    class StarWarsResponse{
        int count;
        String next;
        String previous;
        StarWarsDataType[] results;
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

    class StarWarsPlanetDataType{
        String name;

        @Override
        public String toString(){
            return name;
        }

    }

    class StarWarsFilmDataType{
        String title;

        @Override
        public String toString(){
            return title;
        }
    }

}
