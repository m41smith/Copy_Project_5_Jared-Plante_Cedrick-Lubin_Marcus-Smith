package com.project5.project_5_jaredplante_cedricklubin_marcussmith;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.net.URL;
import java.util.ResourceBundle;

public class SWWindowController implements Initializable {
    @FXML
    private TextField Name;
    @FXML
    private TextField Height;
    @FXML
    private TextField DoB;
    @FXML
    private ListView<SWDataHandler.StarWarsDataType> ListControl;
    private SWDataHandler Model;

    public void loadData(){
        var site = "https://swapi.dev/api/people/?search=";
        var params = getQueryParams();
        var query = site+params;
        Model = new SWDataHandler(query);
        var univList = Model.getData();
        ObservableList<SWDataHandler.StarWarsDataType> dataToShow =
                FXCollections.observableArrayList(univList);
        ListControl.setItems(dataToShow);
    }

    private String getQueryParams() {
        TextInputDialog inputGrabber = new TextInputDialog("Luke");
        inputGrabber.setHeaderText("Gathering Information for query");
        inputGrabber.setContentText("What Star Wars Character shall we search for:");
        var name = inputGrabber.showAndWait();
        if (name.isPresent()){
            return name.get();
        }
        else {
            return "";
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }
}
