package com.project5.project_5_jaredplante_cedricklubin_marcussmith;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GOTAPIWindowController {
    @FXML
    private Label GOTLabel;

    @FXML
    public void handleButton(){
        GOTLabel.setText("hello");
    }

}
