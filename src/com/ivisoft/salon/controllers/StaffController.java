package com.ivisoft.salon.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ivisoft.salon.dao.MasterDao;
import com.ivisoft.salon.model.Client;
import com.ivisoft.salon.model.Master;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class StaffController implements Initializable {
    
    @FXML
    private Button scheduleButton;
    
    @FXML
    private Button servicesButton;
    
    @FXML
    private Button customersButton;
    
    @FXML
    private Button staffButton;
    
    @FXML
    private Button statusButton;
    
    @FXML
    private Button addButton;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private TableView<Master> staffTable;
    
    @FXML
    private TableColumn<Master, File> photoColumn;
    
    @FXML
    private Label dataLabel;
    
    @FXML
    private void scheduleAction(ActionEvent event) {

    }
    
    @FXML
    private void servicesAction(ActionEvent event) {

    }
    
    @FXML
    private void customersAction(ActionEvent event) {

    }
    
    @FXML
    private void staffAction(ActionEvent event) {

    }
    
    @FXML
    private void statusAction(ActionEvent event) {

    }
    
    private static ObservableList<Master> masterList = null;
    
    @FXML
    private void addAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/ivisoft/salon/gui/addStaff.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add master");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    @FXML
    private void logoutAction(ActionEvent event) {
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        masterList = FXCollections.observableList((MasterDao.getAllMasters()));
        staffTable.setItems(masterList);
        photoColumn.setCellFactory(new Callback<TableColumn<Master, File>, TableCell<Master, File>>() {

            @Override
            public TableCell<Master, File> call(TableColumn<Master, File> param) {
                TableCell<Master, File> cell = new TableCell<Master, File>() {
                    @Override
                    public void updateItem(File item, boolean empty) {
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            try {
                                HBox box= new HBox();
                                box.setSpacing(10) ;
                                box.setAlignment(Pos.CENTER);
                                ImageView photo = new ImageView();
                                photo.setFitWidth(50);
                                photo.setPreserveRatio(true);
                                photo.setImage(new Image(item.toURI().toURL().toString(), false));
                                box.getChildren().add(photo);
                                setGraphic(box);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                return cell;
            }
        });
    }

    public static void refreshTable() {
        masterList.removeAll(masterList);
        masterList.addAll(MasterDao.getAllMasters());
    }
}
