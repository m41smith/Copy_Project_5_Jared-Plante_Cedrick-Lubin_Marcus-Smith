package com.project5.project_5_jaredplante_cedricklubin_marcussmith;

import com.google.gson.JsonSyntaxException;
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
import java.util.*;


public class GOTAPIWindowController implements Initializable {
    @FXML
    private TextField ISBNField;
    @FXML
    private TextField PageNumField;
    @FXML
    private TextField BornField;
    @FXML
    private TextField DiedField;
    @FXML
    private TextField ActorField;
    @FXML
    private ListView<GOTDataHandler.GOTDataType> BookList;
    @FXML
    private ListView<GOTDataHandler.GOTCharDataType> CharacterList;
    private GOTDataHandler Model;
    private GOTDataHandler charModel;

    public void loadData() {
        var site = "https://www.anapioficeandfire.com/api/books";
        Model = new GOTDataHandler(site);
        var bookList = Model.getData();
        ObservableList<GOTDataHandler.GOTDataType> dataToShow =
                FXCollections.observableArrayList(bookList);
        BookList.setItems(dataToShow);
    }
    public void loadCharacterData(String site){
        charModel = new GOTDataHandler(site);
        try {
            var person = charModel.getSingleCharData();
                CharacterList.getItems().add(person);
        }catch(JsonSyntaxException e){
            var charList = charModel.getcharData();
            for(var person : charList)
            CharacterList.getItems().add(person);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        BookList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<GOTDataHandler.GOTDataType>() {
            @Override
            public void changed(ObservableValue<? extends GOTDataHandler.GOTDataType> observableValue,
                                GOTDataHandler.GOTDataType gotDataType, GOTDataHandler.GOTDataType t1) {
                BornField.clear();
                DiedField.clear();
                ActorField.clear();
                CharacterList.getItems().clear();
                ISBNField.setText(t1.isbn);
                PageNumField.setText(String.valueOf(t1.numberOfPages));
                var povCharList = t1.povCharacters;
                for(var character : povCharList)
                loadCharacterData(character);
            }
        });
                CharacterList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<GOTDataHandler.GOTCharDataType>() {
                    @Override
                    public void changed(ObservableValue<? extends GOTDataHandler.GOTCharDataType> observable,
                                        GOTDataHandler.GOTCharDataType oldValue, GOTDataHandler.GOTCharDataType newValue) {
                        if(newValue != null) {
                            BornField.setText(newValue.born);
                            DiedField.setText(newValue.died);
//                            var actorList = newValue.playedBy;
//                            for(var actors : actorList){
//                                var splitActors = actors.split("[,]");
//                                ActorField.getText().split("[,]")
//                            }
                            var actors = newValue.actorString();
                            ActorField.setText(actors);
                        }
                    }
                });
    }
}
