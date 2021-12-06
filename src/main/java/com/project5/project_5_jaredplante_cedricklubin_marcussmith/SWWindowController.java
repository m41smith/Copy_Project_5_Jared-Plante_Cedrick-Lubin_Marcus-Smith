package com.project5.project_5_jaredplante_cedricklubin_marcussmith;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    private ListView<SWDataHandler> ListControl;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
