package com.ivisoft.salon.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.ivisoft.salon.dao.DictionaryDao;
import com.ivisoft.salon.model.Dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewSectionController implements Initializable {
    
    @FXML
    private TextField newSectionField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private void okAction(ActionEvent event) {
        Dictionary dict = new Dictionary();
        dict.setCaption(newSectionField.getText());
        dict.setType("service_type");
        dict.setStatus(1);
        
        DictionaryDao.createDictionary(dict);
        
        cancelAction(event);
        
        ServicesController.refreshSectionBox(DictionaryDao.getDictsByType("service_type"));
    }
    
    @FXML
    private void cancelAction(ActionEvent event) {
        Node source = (Node) event.getSource(); 
        Stage parentStage = (Stage) source.getScene().getWindow(); 
        parentStage.close();
    }
}
