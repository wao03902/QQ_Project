package com.ivisoft.salon.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.ivisoft.salon.dao.ClientDao;
import com.ivisoft.salon.dao.VisitDao;
import com.ivisoft.salon.model.Client;
import com.ivisoft.salon.utils.JavaFXUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ClientsController implements Initializable {
    
    @FXML
    private TableView<Client> clientsTable;
    
    @FXML
    private TableColumn<Client, String> idColumn;
    
    @FXML
    private TableColumn<Client, String> nameColumn;
    
    @FXML
    private TableColumn<Client, String> phoneColumn;
    
    @FXML
    private TableColumn<Client, Integer> visitAmountColumn;
    
    @FXML
    private Button addClientBtn;
    
    private static ObservableList<Client> clientList = null;
    
    public void initialize(URL url, ResourceBundle rb) {
        List<Client> clients = ClientDao.getAllClients();
        clientList = FXCollections.observableArrayList(clients);
        clientsTable.setItems(clientList);
        
        visitAmountColumn.setCellFactory(new Callback<TableColumn<Client, Integer>, TableCell<Client, Integer>>() {

            @Override
            public TableCell<Client, Integer> call(TableColumn<Client, Integer> param) {
                TableCell<Client, Integer> cell = new TableCell<Client, Integer>() {
                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        if (item != null) {
                            HBox box= new HBox();
                            box.setAlignment(Pos.CENTER);
                            Text amount = new Text();
                            amount.setText(String.valueOf(VisitDao.countVisitsByClientId(item.intValue())));
                            box.getChildren().add(amount);
                            setGraphic(box);
                        }
                    }
                };
                return cell;
            }
        });
    }

    @FXML
    private void newClient(ActionEvent event) throws IOException {
        
//        Stage stage = JavaFXUtil.createStage("/com/ivisoft/salon/gui/addClient.fxml");
//        stage.setTitle("New client");
//        stage.show();
    }
    
    @FXML
    private void cancel(ActionEvent event) throws Exception {
//        JavaFXUtil.replaceSceneContent(event, "/com/ivisoft/salon/gui/main.fxml");
    }
    
    public static void refreshTable() {
        clientList.removeAll(clientList);
        clientList.addAll(FXCollections.observableList(ClientDao.getAllClients()));
    }
}