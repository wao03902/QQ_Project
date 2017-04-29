package com.ivisoft.salon.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ivisoft.salon.model.Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClientsController implements Initializable {
    
    @FXML
    private TableView<Client> clientsTable;
    
    @FXML
    private TableColumn<Client, String> idColumn;
    
    @FXML
    private TableColumn<Client, String> nameColumn;
    
    @FXML
    private TableColumn<Client, String> surnameColumn;
    
    @FXML
    private TableColumn<Client, String> fathernameColumn;

    @FXML
    private TableColumn<Client, String> phoneColumn;
    
    @FXML
    private TableColumn<Client, LocalDate> createDateColumn;
    

    @FXML
    private TableColumn<Client, String> statusColumn;
    
    public void initialize(URL url, ResourceBundle rb) {
        
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
//        fathernameColumn.setCellValueFactory(new PropertyValueFactory<>("fathername"));
//        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
//        createDateColumn.setCellValueFactory(new PropertyValueFactory<>("createDate"));
//        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        List<Client> clients = new ArrayList<>();
        
        ObservableList<Client> clientList = FXCollections.observableArrayList(clients);
        clientsTable.setItems(clientList);
            
    }

    @FXML
    private void newClient(ActionEvent event) {
        
    }
    
    @FXML
    private void cancel(ActionEvent event) {
        
    }
}