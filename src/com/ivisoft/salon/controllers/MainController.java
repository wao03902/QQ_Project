package com.ivisoft.salon.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ivisoft.salon.QQ_Project;
import com.ivisoft.salon.utils.JavaFXUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MainController implements Initializable {
    
    @FXML
    GridPane root;
    
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
        JavaFXUtil.replaceSceneContent(event, "/com/ivisoft/salon/gui/clients.fxml", "/com/ivisoft/salon/gui/style.css");
    }
    
    @FXML
    private void staffAction(ActionEvent event) throws IOException {
//        root.getChildren().setAll(FXMLLoader.load(QQ_Project.class.getResource("/com/ivisoft/salon/gui/staff.fxml")));
//        JavaFXUtil.replaceSceneContent(event, "/com/ivisoft/salon/gui/staff.fxml", "/com/ivisoft/salon/gui/style.css");
        QQ_Project.primaryStage.setScene(QQ_Project.staffScene);
    }

    @FXML
    private void statusAction(ActionEvent event) {
        
    }
    
    @FXML
    private void logoutAction(ActionEvent event) {
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
