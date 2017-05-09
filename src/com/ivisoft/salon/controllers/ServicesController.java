package com.ivisoft.salon.controllers;

import static com.ivisoft.salon.utils.JavaFXUtil.createScene;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ivisoft.salon.Salon;
import com.ivisoft.salon.dao.DictionaryDao;
import com.ivisoft.salon.dao.MasterDao;
import com.ivisoft.salon.dao.ServiceDao;
import com.ivisoft.salon.model.Dictionary;
import com.ivisoft.salon.model.Master;
import com.ivisoft.salon.model.Service;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ServicesController implements Initializable {
    
    private static ObservableList<Service> serviceList = null;
    
    @FXML
    private ChoiceBox<Dictionary> selectionBox;
    
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
        serviceList = FXCollections.observableList(ServiceDao.getAllServices());
        mainTable.setItems(serviceList);
        
        selectionBox.setItems(FXCollections.observableList(DictionaryDao.getDictsByType("service_type")));
        selectionBox.getSelectionModel().selectedItemProperty().addListener((obj, oldSelect, newSelect) -> {
            refreshTable(ServiceDao.getServicesByTypeId(newSelect.getId()));
        });
        
        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Редактировать");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editAction(event);
                mainTable.getSelectionModel().clearSelection();
            }
        });
        MenuItem item2 = new MenuItem("Удалить");
        item2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteAction(event);
                mainTable.getSelectionModel().clearSelection();
            }
        });
        contextMenu.getItems().addAll(item1, item2);
        
        mainTable.setRowFactory(new Callback<TableView<Service>, TableRow<Service>>() {
            @Override
            public TableRow<Service> call(TableView<Service> tableView) {
                TableRow<Service> row = new TableRow<>();
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                        .then((ContextMenu) null)
                        .otherwise(contextMenu)
                );
                return row;
            }
        });
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
    private void addAction(ActionEvent event) {
        AddOrEditServicesController.isEdition = false;
        AddOrEditServicesController.service = new Service();
        Stage stage = new Stage();
        stage.setScene(createScene(this, "addServices"));
        stage.setResizable(false);
        stage.initOwner(Salon.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Новая услуга");
        stage.show();
    }
    
    @FXML
    private void editAction(ActionEvent event) {
        AddOrEditServicesController.isEdition = true;
        AddOrEditServicesController.service = mainTable.getSelectionModel().getSelectedItem();
        
        Alert al = new Alert(AlertType.CONFIRMATION);
        al.setTitle("Изменение услуги");
        al.setHeaderText("Хотите внести изменения?");
        al.setContentText("Вы уверены, что хотите изменить информацию об услуге " + AddOrEditServicesController.service.getName() + "?");
        Optional<ButtonType> result = al.showAndWait();
        if (result.get() == ButtonType.OK){
            al.close();
            Stage stage = new Stage();
            stage.setScene(createScene(this, "addServices"));
            stage.setResizable(false);
            stage.initOwner(Salon.stage);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Изменяем услугу");
            stage.show();
        } else {
            al.close();
        }
    }
    
    @FXML
    private void deleteAction(ActionEvent event) {
        Service selectedService = mainTable.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Удаление услуги");
        alert.setHeaderText("Хотите удалить услугу?");
        alert.setContentText("Вы уверены, что хотите удалить услугу " + selectedService.getName() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            ServiceDao.deleteService(selectedService);
            refreshTable(ServiceDao.getAllServices());
        } else {
            alert.close();
        }
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
    
    public static void refreshTable(List<Service> services) {
        serviceList.removeAll(serviceList);
        serviceList.addAll(FXCollections.observableList(services));
    }
}
