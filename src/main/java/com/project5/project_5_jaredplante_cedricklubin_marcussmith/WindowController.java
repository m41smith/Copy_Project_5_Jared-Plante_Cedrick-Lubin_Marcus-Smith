package com.project5.project_5_jaredplante_cedricklubin_marcussmith;

import com.project5.project_5_jaredplante_cedricklubin_marcussmith.APIApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowController {
    @FXML
    private Label welcomeText;


    @FXML
    public void handleOpenWindow(ActionEvent event){
        var secondLoc = new FXMLLoader(APIApplication.class.getResource("GameOfThronesAPI_Jared-Plante.fxml"));
        Scene secondScene = null;
        try{
            secondScene = new Scene(secondLoc.load(), 900, 600);
        }catch (IOException e){
            System.out.println("Couldn't load second window");
            e.printStackTrace();
        }
        Stage secondWindow = new Stage();
        secondWindow.setScene(secondScene);
        secondWindow.setTitle("See  - here is a second window");
        secondWindow.show();
    }

    @FXML
    public void handleOpenStarWarsWindow(ActionEvent event){
        var test = new FXMLLoader(APIApplication.class.getResource("StarWarsAPI_MarcusSmith.fxml"));
        Scene nextScene = null;
        try{
            nextScene = new Scene(test.load(), 900, 600);
        }catch (IOException e){
            System.out.println("Couldn't load Star Wars window");
            e.printStackTrace();
        }
        Stage secondWindow = new Stage();
        secondWindow.setScene(nextScene);
        secondWindow.setTitle("See  - here is the Star Wars window");
        secondWindow.show();
    }

    @FXML
    public void handleClose(ActionEvent event){
        System.exit(0);
    }

}