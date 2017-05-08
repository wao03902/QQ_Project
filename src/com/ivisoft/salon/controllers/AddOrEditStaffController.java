package com.ivisoft.salon.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.ListSelectionView;

import com.ivisoft.salon.dao.MasterDao;
import com.ivisoft.salon.dao.ServiceDao;
import com.ivisoft.salon.model.Master;
import com.ivisoft.salon.model.Service;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddOrEditStaffController implements Initializable {
    
    public static boolean isEdition = false;
    public static Master master = new Master();
    
    @FXML
    private TextField nameFiled;
    
    @FXML
    private TextField tel1Field;
    
//    @FXML
//    private TextField tel2Field;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private Button saveButton;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private Hyperlink loadPhotoLink;
    
    @FXML
    private ListSelectionView<Service> selectServices;
    
    @FXML
    private ImageView photo;
    
    @FXML
    private Label headerLabel;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        if (isEdition) {
            headerLabel.setText("Изменяем сотрудника");
            nameFiled.setText(master.getName());
            emailField.setText(master.getEmail());
            tel1Field.setText(master.getPhone());
            
            try {
                photo.setImage(new Image(master.getPhoto().toURI().toURL().toString(), false));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        
        Text available = new Text("Доступно");
        available.setFill(Color.WHITE);
        available.setStyle("-fx-font-weight: bold;");
        selectServices.sourceHeaderProperty().set(available);
        
        Text target = new Text("Выбрано");
        target.setFill(Color.WHITE);
        target.setStyle("-fx-font-weight: bold;");
        selectServices.targetHeaderProperty().set(target);
        
        if (!isEdition) {
            ObservableList<Service> services = FXCollections.observableList(ServiceDao.getAllServices());
            selectServices.setSourceItems(services);
            
            tel1Field.setText("+38");
            tel1Field.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                        tel1Field.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
        } else {
            ObservableList<Service> services = FXCollections.observableList(ServiceDao.getAllServices());
            ObservableList<Service> targetServices = FXCollections.observableList(ServiceDao.getServicesByMasterId(master.getId()));
            services.removeIf(p -> targetServices.contains(p));
            selectServices.setSourceItems(services);
            selectServices.setTargetItems(targetServices);
        }
    }
    
    @FXML
    private void loadAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        
        File file = fileChooser.showOpenDialog((Stage) loadPhotoLink.getScene().getWindow());
        if (file != null) {
            master.setPhoto(file);
            Image image = new Image(file.toURI().toURL().toString(), false);
            photo.setImage(image);
        }
    }
    
    @FXML
    private void saveAction(ActionEvent event) {
        master.setName(nameFiled.getText());
        master.setEmail(emailField.getText());
        master.setPhone(tel1Field.getText());
        master.setStatus(1);
        
        if (!isEdition) {
            MasterDao.createMaster(master, selectServices.getTargetItems());
            Node source = (Node) event.getSource(); 
            Stage stage = (Stage) source.getScene().getWindow(); 
            stage.close();
            StaffController.refreshTable();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Успех");
            alert.setHeaderText(null);
            alert.setContentText("Новый сотрудник был успешно добавлен!");
            alert.showAndWait();
        } else {
            MasterDao.updateMaster(master, selectServices.getTargetItems());
            Node source = (Node) event.getSource(); 
            Stage stage = (Stage) source.getScene().getWindow(); 
            stage.close();
            StaffController.refreshTable();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Успех");
            alert.setHeaderText(null);
            alert.setContentText("Информация о сотруднике была успешно изменена!");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void cancelAction(ActionEvent event) {
        Node source = (Node) event.getSource(); 
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
