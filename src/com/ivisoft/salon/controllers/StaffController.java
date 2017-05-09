package com.ivisoft.salon.controllers;

import static com.ivisoft.salon.utils.JavaFXUtil.createScene;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ivisoft.salon.Salon;
import com.ivisoft.salon.dao.MasterDao;
import com.ivisoft.salon.model.Master;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class StaffController implements Initializable {
    
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
    private TableView<Master> mainTable;
    
//    @FXML
//    private TableColumn<Master, File> photoColumn;
    
    @FXML
    private TableColumn<Master, String> idColumn;
    
    @FXML
    private Label dataLabel;
    
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
    
    private static ObservableList<Master> masterList = null;
    
    @FXML
    private void addAction(ActionEvent event) {
        AddOrEditStaffController.isEdition = false;
        AddOrEditStaffController.master = new Master();
        Stage stage = new Stage();
        stage.setScene(createScene(this, "addStaff"));
        stage.setResizable(false);
        stage.initOwner(Salon.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Новый сотрудник");
        stage.show();
    }
    
    @FXML
    private void editAction(ActionEvent event) {
        AddOrEditStaffController.isEdition = true;
        AddOrEditStaffController.master = mainTable.getSelectionModel().getSelectedItem();
        
        Alert al = new Alert(AlertType.CONFIRMATION);
        al.setTitle("Изменение сотрудника");
        al.setHeaderText("Хотите внести изменения?");
        al.setContentText("Вы уверены, что хотите внести изменения сотруднику " + AddOrEditStaffController.master.getName() + "?");
        Optional<ButtonType> result = al.showAndWait();
        if (result.get() == ButtonType.OK){
            al.close();
            Stage stage = new Stage();
            stage.setScene(createScene(this, "addStaff"));
            stage.setResizable(false);
            stage.initOwner(Salon.stage);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Изменяем сотрудника");
            stage.show();
        } else {
            al.close();
        }
    }
    
    @FXML
    private void deleteAction(ActionEvent event) {
        if (mainTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Извините, произошла ошибка");
            alert.setContentText("Вначале выберите мастера, которого хотите удалить!");
            alert.showAndWait();
            return;
        } 
        
        Master selectedMaster = mainTable.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Удаление сотрудника");
        alert.setHeaderText("Хотите удалить сотрудника?");
        alert.setContentText("Вы уверены, что хотите удалить сотрудника " + selectedMaster.getName() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            MasterDao.deleteMaster(selectedMaster);
            refreshTable();
        } else {
            alert.close();
        }
        
    }
    
    @FXML
    private void logoutAction(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        masterList = FXCollections.observableList((MasterDao.getAllMasters()));
        mainTable.setItems(masterList);
        
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
        
        mainTable.setRowFactory(new Callback<TableView<Master>, TableRow<Master>>() {
            @Override
            public TableRow<Master> call(TableView<Master> tableView) {
                TableRow<Master> row = new TableRow<>();
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                        .then((ContextMenu) null)
                        .otherwise(contextMenu)
                );
                return row;
            }
        });
        
//        photoColumn.setCellFactory(new Callback<TableColumn<Master, File>, TableCell<Master, File>>() {
//
//            @Override
//            public TableCell<Master, File> call(TableColumn<Master, File> param) {
//                TableCell<Master, File> cell = new TableCell<Master, File>() {
//                    @Override
//                    public void updateItem(File item, boolean empty) {
//                        if (item == null || empty) {
//                            setGraphic(null);
//                        } else {
//                            try {
//                                HBox box= new HBox();
//                                box.setSpacing(10) ;
//                                box.setAlignment(Pos.CENTER);
//                                ImageView photo = new ImageView();
//                                photo.setFitWidth(50);
//                                photo.setPreserveRatio(true);
//                                photo.setImage(new Image(item.toURI().toURL().toString(), false));
//                                box.getChildren().add(photo);
//                                setGraphic(box);
//                            } catch (MalformedURLException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                };
//                return cell;
//            }
//        });
    }

    public static void refreshTable() {
        masterList.removeAll(masterList);
        masterList.addAll(MasterDao.getAllMasters());
    }
}