package com.project5.project_5_jaredplante_cedricklubin_marcussmith;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class GOTAPIWindowController implements Initializable {
    @FXML
    private TextField ISBNField;
    @FXML
    private TextField PageNumField;
    @FXML
    private ListView<GOTDataHandler.GOTDataType> BookList;
    @FXML
    private ListView<String> CharacterList;
    private GOTDataHandler Model;

    public void loadData() {
        var site = "https://www.anapioficeandfire.com/api/books";
        Model = new GOTDataHandler(site);
        var bookList = Model.getData();
        ObservableList<GOTDataHandler.GOTDataType> dataToShow =
                FXCollections.observableArrayList(bookList);
        BookList.setItems(dataToShow);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        BookList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<GOTDataHandler.GOTDataType>() {
            @Override
            public void changed(ObservableValue<? extends GOTDataHandler.GOTDataType> observableValue, GOTDataHandler.GOTDataType gotDataType, GOTDataHandler.GOTDataType t1) {
                ISBNField.setText(t1.isbn);
                PageNumField.setText(String.valueOf(t1.numberOfPages));
                CharacterList.setItems((ObservableList<String>) t1.povCharacters);
            }
        });
    }
}
