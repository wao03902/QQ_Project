package com.ivisoft.salon.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ivisoft.salon.Salon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MainController implements Initializable {
    
    @FXML
    private GridPane root;
    
    @FXML
    private ImageView logoPhoto;
    
    @FXML
    private ImageView userPhoto;
    
    @FXML
    private Button scheduleButton;
    
    @FXML
    private Button servicesButton;
    
    @FXML
    private Button clientsButton;
    
    @FXML
    private Button staffButton;
    
    @FXML
    private Button statusButton;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private Label timeLabel;
    
    @FXML
    private Label dataLabel;
    
    @FXML
    private TableView mainTable;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private void scheduleAction(ActionEvent event) {
        
    }
    
    @FXML
    private void servicesAction(ActionEvent event) {
        Salon.stage.setTitle("Услуги");
        Salon.stage.setScene(Salon.servicesScene);
    }
    
    @FXML
    private void clientsAction(ActionEvent event) throws Exception {
        Salon.stage.setTitle("Клиенты");
        Salon.stage.setScene(Salon.clientsScene);
    }
    
    @FXML
    private void staffAction(ActionEvent event) throws IOException {
        Salon.stage.setTitle("Персонал");
        Salon.stage.setScene(Salon.staffScene);
    }

    @FXML
    private void statusAction(ActionEvent event) {
        
    }
    
    @FXML
    private void logoutAction(ActionEvent event) {
        
    }
}
