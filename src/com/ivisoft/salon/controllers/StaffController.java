package com.ivisoft.salon.controllers;

import static com.ivisoft.salon.utils.JavaFXUtil.createScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ivisoft.salon.Salon;
import com.ivisoft.salon.dao.MasterDao;
import com.ivisoft.salon.model.Master;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        Stage stage = new Stage();
        stage.setScene(createScene(this, "addStaff"));
        stage.setResizable(false);
        stage.initOwner(Salon.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Изменяем сотрудника");
        stage.show();
    }
    
    @FXML
    private void deleteAction(ActionEvent event) {
        Master selectedMaster = mainTable.getSelectionModel().getSelectedItem();
        MasterDao.deleteMaster(selectedMaster);
        refreshTable();
    }
    
    @FXML
    private void logoutAction(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        masterList = FXCollections.observableList((MasterDao.getAllMasters()));
        mainTable.setItems(masterList);
        
//        idColumn.setCellFactory(new Callback<TableColumn<Master, String>, TableCell<Master, String>>() {
//            @Override
//            public TableCell<Master, String> call(TableColumn<Master, String> param) {
//                TableCell<Master, String> cell = new TableCell<Master, String>() {
//                    @Override
//                    public void updateItem(String item, boolean empty) {
//                        System.out.println("GO " + counter);
//                        setText(String.valueOf(++counter));
//                    }
//                };
//                return cell;
//            }
//        });
        
        
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