package com.ivisoft.salon.controllers;

import static com.ivisoft.salon.utils.JavaFXUtil.createScene;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.ivisoft.salon.Salon;
import com.ivisoft.salon.dao.ClientDao;
import com.ivisoft.salon.dao.VisitDao;
import com.ivisoft.salon.model.Client;
import com.ivisoft.salon.utils.JavaFXUtil;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ClientsController implements Initializable {
    
    @FXML
    private TableView<Client> mainTable;
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
    private Button logoutButton;
    @FXML
    private Label timeLabel;
    @FXML
    private Label dataLabel;
    @FXML
    private Button addButton;
    @FXML
    private TableColumn<Client, Integer> idColumn;
    @FXML
    private TableColumn<Client, String> nameColumn;
    @FXML
    private TableColumn<Client, String> phoneColumn;
    @FXML
    private TableColumn<Client, Integer> visitCountColumn;
    
    @FXML
    private Button addClientBtn;
    
    private static ObservableList<Client> clientList = null;
    
    public void initialize(URL url, ResourceBundle rb) {
        List<Client> clients = ClientDao.getAllClients();
        clientList = FXCollections.observableArrayList(clients);
        mainTable.setItems(clientList);
//        visitCountColumn.setCellValueFactory(c-> new SimpleIntegerProperty());
        
        visitCountColumn.setCellFactory(new Callback<TableColumn<Client, Integer>, TableCell<Client, Integer>>() {

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
        
        Stage stage = new Stage();
        stage.setScene(createScene(this, "addClient"));
        stage.setResizable(false);
        stage.initOwner(Salon.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("New client");
        stage.show();
    }
    
    @FXML
    private void cancel(ActionEvent event) throws Exception {
//        JavaFXUtil.replaceSceneContent(event, "/com/ivisoft/salon/gui/main.fxml");
    }
    
    public static void refreshTable() {
        clientList.removeAll(clientList);
        clientList.addAll(FXCollections.observableList(ClientDao.getAllClients()));
    }
    
    @FXML
    private void scheduleAction(ActionEvent event) {
        if (Salon.stage.isFullScreen()) {
            Salon.stage.setScene(Salon.mainScene);
            Salon.stage.setFullScreen(true);
        } else {
            Salon.stage.setScene(Salon.mainScene);
        }
    }

    @FXML
    private void servicesAction(ActionEvent event) {
        if (Salon.stage.isFullScreen()) {
            Salon.stage.setScene(Salon.servicesScene);
            Salon.stage.setFullScreen(true);
        } else {
            Salon.stage.setScene(Salon.servicesScene);
        }
    }

    @FXML
    private void clientsAction(ActionEvent event) {
        if (Salon.stage.isFullScreen()) {
            Salon.stage.setScene(Salon.clientsScene);
            Salon.stage.setFullScreen(true);
        } else {
            Salon.stage.setScene(Salon.clientsScene);
        }
    }

    @FXML
    private void staffAction(ActionEvent event) {
        if (Salon.stage.isFullScreen()) {
            Salon.stage.setScene(Salon.staffScene);
            Salon.stage.setFullScreen(true);
        } else {
            Salon.stage.setScene(Salon.staffScene);
        }
    }

    @FXML
    private void statusAction(ActionEvent event) {
    }

    @FXML
    private void fullScreenAction(ActionEvent event) {
    }

    @FXML
    private void logoutAction(ActionEvent event) {
//        Salon.logOut();
    }

    @FXML
    private void addAction(ActionEvent event) {
    }
}