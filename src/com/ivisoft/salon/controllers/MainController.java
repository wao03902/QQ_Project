package com.ivisoft.salon.controllers;

import java.io.IOException;

import com.ivisoft.salon.utils.JavaFXUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

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
    private void clientsAction(ActionEvent event) throws Exception {
        JavaFXUtil.replaceSceneContent(event, "/com/ivisoft/salon/gui/clients.fxml");
    }
    
    @FXML
    private void staffAction(ActionEvent event) throws IOException {
        JavaFXUtil.replaceSceneContent(event, "/com/ivisoft/salon/gui/staff.fxml");
    }

    @FXML
    private void statusAction(ActionEvent event) {
        
    }
    
    @FXML
    private void logoutAction(ActionEvent event) {
        
    }
}
