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

public class IPNWindowController implements Initializable {
    @FXML
    private TextField CountryNameField;
    @FXML
    private TextField CountryCodeField;
    @FXML
    private TextField PhonePrefixField;
    @FXML
    private ListView ListControl;
    private IPNDataHandler Model;


    public void loadCountryData(){
        var site = "http://country.io/names.json";
        Model = new IPNDataHandler(site);
        var countryList = Model.getData().values();
        ObservableList<ListView> dataToShow = FXCollections.observableArrayList(countryList);
        ListControl.setItems(dataToShow);


    }

    public void loadPhoneData(){
        var site2 = "http://country.io/phone.json";
        Model = new IPNDataHandler(site2);
        var codeList = Model.getData().keySet();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources){
        loadCountryData();
        loadPhoneData();
        ListControl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                //CountryNameField.setText(newValue.);
            }
        });

    }


}
