package com.ivisoft.salon.controllers;

import static com.ivisoft.salon.utils.JavaFXUtil.createScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ivisoft.salon.Salon;
import com.ivisoft.salon.dao.ServiceDao;
import com.ivisoft.salon.model.Service;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ServicesController implements Initializable {
    
    @FXML
    private GridPane root;
    
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
    private TableView<Service> mainTable;
    
    @FXML
    private Button addButton;
    
    @FXML
    private Button editButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private Label timeLabel;
    
    @FXML
    private Label dataLabel;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        mainTable.setItems(FXCollections.observableList(ServiceDao.getAllServices()));
    }
    
    @FXML
    private void scheduleAction(ActionEvent event) {

    }
    
    @FXML
    private void servicesAction(ActionEvent event) {
        Salon.stage.setTitle("������");
        Salon.stage.setScene(Salon.servicesScene);
    }
    
    @FXML
    private void clientsAction(ActionEvent event) throws Exception {
        Salon.stage.setTitle("�������");
        Salon.stage.setScene(Salon.clientsScene);
    }
    
    @FXML
    private void staffAction(ActionEvent event) throws IOException {
        Salon.stage.setTitle("��������");
        Salon.stage.setScene(Salon.staffScene);
    }
    
    @FXML
    private void statusAction(ActionEvent event) {

    }
    
    @FXML
    private void addAction(ActionEvent event) {
        Stage stage = new Stage();
        stage.setScene(createScene(this, "addServices"));
        stage.setResizable(false);
        stage.initOwner(Salon.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("����� ������");
        stage.show();
    }
    
    @FXML
    private void editAction(ActionEvent event) {
        
    }
    
    @FXML
    private void deleteAction(ActionEvent event) {
        
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        
    }
    
    @FXML
    private void addSelectionAction(ActionEvent event) {
        
    }
    
    @FXML
    private void fullScreenAction(ActionEvent event) {
        
    }
}