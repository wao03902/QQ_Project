package com.ivisoft.salon.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
    
    @FXML
    ImageView logoPhoto;
    
    @FXML
    ImageView userPhoto;
    
    @FXML
    Button scheduleButton;
    
    @FXML
    Button servicesButton;
    
    @FXML
    Button clientsButton;
    
    @FXML
    Button staffButton;
    
    @FXML
    Button statusButton;
    
    @FXML
    private void scheduleAction(ActionEvent event) {
        
    }
    
    @FXML
    private void servicesAction(ActionEvent event) {

    }
    
    @FXML
    private void clientsAction(ActionEvent event) {
        
        try {
            Stage stage = (Stage) clientsButton.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/clients.fxml"));
            Parent root1;
            root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Clients");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void staffAction(ActionEvent event) {
        
    }

    @FXML
    private void statusAction(ActionEvent event) {
        
    }
    
    @FXML
    private void logoutAction(ActionEvent event) {
        
    }
}
