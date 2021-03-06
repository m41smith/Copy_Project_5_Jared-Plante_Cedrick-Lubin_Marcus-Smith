package com.project5.project_5_jaredplante_cedricklubin_marcussmith;

import com.project5.project_5_jaredplante_cedricklubin_marcussmith.APIApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    //Marcus Smith
    @FXML
    public void handleOpenStarWarsWindow(ActionEvent event){
        var SWfxml = new FXMLLoader(APIApplication.class.getResource("StarWarsAPI_MarcusSmith.fxml"));
        Scene nextScene = null;
        var error = "";
        try{
            nextScene = new Scene(SWfxml.load(), 900, 600);
        }
        //catches Input/output errors
        catch (IOException e){
            //prints out complete error
            e.printStackTrace();
            error = e.getClass().toString();
        }
        //displays error window
        if (nextScene == null){
            Alert warn = new Alert(Alert.AlertType.ERROR);
            warn.setTitle(error+ " Error");
            warn.setContentText("Couldn't load Star Wars window. Hit OK and try again.\n(Make sure to check correct spelling)");
            warn.show();
        }
        //displays FXML scene
        else {
            Stage secondWindow = new Stage();
            secondWindow.setScene(nextScene);
            secondWindow.setTitle("Star Wars API Window");
            secondWindow.show();
        }
    }

    @FXML
    public void handleClose(ActionEvent event){
        System.exit(0);
    }

}