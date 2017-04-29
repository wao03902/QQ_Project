package com.ivisoft.salon.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.ivisoft.salon.dao.ClientDao;
import com.ivisoft.salon.model.Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    
    @FXML
    private Button addClientBtn;
    
    public void initialize(URL url, ResourceBundle rb) {
        
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
//        fathernameColumn.setCellValueFactory(new PropertyValueFactory<>("fathername"));
//        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
//        createDateColumn.setCellValueFactory(new PropertyValueFactory<>("createDate"));
//        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        List<Client> clients = ClientDao.getAllClients();
        
        ObservableList<Client> clientList = FXCollections.observableArrayList(clients);
        clientsTable.setItems(clientList);
            
    }

    @FXML
    private void newClient(ActionEvent event) throws IOException {
//        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/ivisoft/salon/gui/addClient.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Main window");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    @FXML
    private void cancel(ActionEvent event) {
        
    }
}