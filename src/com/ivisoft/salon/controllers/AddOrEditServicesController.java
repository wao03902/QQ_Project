package com.ivisoft.salon.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.ivisoft.salon.dao.DictionaryDao;
import com.ivisoft.salon.model.Dictionary;
import com.ivisoft.salon.model.Master;
import com.ivisoft.salon.model.Service;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddOrEditServicesController implements Initializable {
    
    public static boolean isEdition = false;
    public static Service master = new Service();
    
    @FXML
    private ChoiceBox<Dictionary> selectionField;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField priceField;
    
    @FXML
    private TextField durationField;
    
    @FXML
    private TextArea descField;
    
    @FXML
    private Button saveButton;
    
    @FXML
    private Button cancelButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectionField.setItems(FXCollections.observableList(DictionaryDao.getDictsByType("service_type")));
    }
    
    @FXML
    private void saveAction(ActionEvent event) {
        if (isEdition == false) {
            
        }
    }
    
    @FXML
    private void cancelAction(ActionEvent event) {
        Node source = (Node) event.getSource(); 
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
