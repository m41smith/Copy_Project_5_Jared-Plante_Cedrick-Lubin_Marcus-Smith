//Marcus Smith

package com.project5.project_5_jaredplante_cedricklubin_marcussmith;

//imports needed packages
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
    private TextField HomeWorld;
    @FXML
    private ListView Films;
    @FXML
    private ListView<SWDataHandler.StarWarsDataType> ListControl;
    private SWDataHandler Model;

    //loads api Data
    public void loadData(){
        var site = "https://swapi.dev/api/people/?search=";
        var params = getQueryParams();
        var query = site+params;
        Model = new SWDataHandler(query);
        var SWList = Model.getData();
        ObservableList<SWDataHandler.StarWarsDataType> dataToShow =
                FXCollections.observableArrayList(SWList);
        ListControl.setItems(dataToShow);
    }

    //wait's for user input
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
        ListControl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SWDataHandler.StarWarsDataType>() {
            @Override
            public void changed(ObservableValue<? extends SWDataHandler.StarWarsDataType> observable, SWDataHandler.StarWarsDataType oldValue, SWDataHandler.StarWarsDataType newValue) {
                Name.setText(newValue.name);
                Height.setText(newValue.height + "cm");
                DoB.setText(newValue.birth_year);
                var planet = Model.getPlanetData().toString();
                HomeWorld.setText(planet);
                var test = FXCollections.observableArrayList(Model.getFilmData());
                Films.setItems(test);
            }
        });
    }
}
