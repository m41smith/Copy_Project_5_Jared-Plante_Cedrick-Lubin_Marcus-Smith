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
    private ListView<IPNDataHandler.PhoneDataType> ListControl;
    private IPNDataHandler Model;
    //private IPNDataHandler Model2;


    public void loadCountryData(){
        var site = "http://country.io/names.json";
        Model = new IPNDataHandler(site);
        var univList = Model.getData();
        ObservableList<IPNDataHandler.PhoneDataType> dataToShow =
                FXCollections.observableArrayList(univList);
        ListControl.setItems(dataToShow);
        System.out.println(univList);
    }
    public void loadPhoneData(){
        var site2 = "http://country.io/phone.json";
        Model = new IPNDataHandler(site2);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
        loadCountryData();
        loadPhoneData();
        ListControl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IPNDataHandler.PhoneDataType>() {
            @Override
            public void changed(ObservableValue<? extends IPNDataHandler.PhoneDataType> observableValue, IPNDataHandler.PhoneDataType phoneDataType, IPNDataHandler.PhoneDataType t1) {
                //CountryNameField.setText(t1.country);
                CountryCodeField.setText(t1.countryCode);
                PhonePrefixField.setText(t1.phonePrefix);
            }
        });
    }


}
