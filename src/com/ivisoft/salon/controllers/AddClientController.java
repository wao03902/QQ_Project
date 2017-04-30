package com.ivisoft.salon.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ivisoft.salon.dao.ClientDao;
import com.ivisoft.salon.dao.DictionaryDao;
import com.ivisoft.salon.model.Client;
import com.ivisoft.salon.model.Dictionary;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddClientController implements Initializable {
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField telField;
    
    @FXML
    private ComboBox<Dictionary> sexComboBox;
    
    @FXML
    private TextArea noteField;
    
    @FXML
    private Button saveButton;
    
    @FXML
    private Button cancelButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sexComboBox.setItems(FXCollections.observableList(DictionaryDao.getDictsByType("client_sex")));
    }
    
    @FXML
    private void saveAction(ActionEvent event) throws IOException {
        Client client = new Client();
        client.setName(nameField.getText());
        client.setPhone(telField.getText());
        client.setStatus(1);
        client.setDescription(noteField.getText());
        client.setSex(sexComboBox.getValue());
        
        ClientDao.createClient(client);
        
        Node source = (Node) event.getSource(); 
        Stage stage = (Stage) source.getScene().getWindow(); 
        stage.close();
        
        ClientsController.refreshTable();
    }
    
    @FXML
    private void cancelAction(ActionEvent event) {
        Node source = (Node) event.getSource(); 
        Stage parentStage = (Stage) source.getScene().getWindow(); 
        parentStage.close(); 
    }
}
