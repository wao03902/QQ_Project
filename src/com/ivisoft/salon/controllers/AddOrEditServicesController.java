package com.ivisoft.salon.controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ivisoft.salon.dao.DictionaryDao;
import com.ivisoft.salon.dao.MasterDao;
import com.ivisoft.salon.dao.ServiceDao;
import com.ivisoft.salon.model.Dictionary;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddOrEditServicesController implements Initializable {
    
    public static boolean isEdition = false;
    public static Service service = new Service();
    
    @FXML
    private ChoiceBox<Dictionary> selectionField;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField priceField;
    
    @FXML
    private TextField durationField;
    
    @FXML
    private TextArea descField;
    
    @FXML
    private Button saveButton;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private Label newServiceLabel;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectionField.setItems(FXCollections.observableList(DictionaryDao.getDictsByType("service_type")));
        
        priceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    priceField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        durationField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    priceField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        if (isEdition) {
            nameField.setText(service.getName());
            priceField.setText(service.getPrice().toString());
            descField.setText(service.getDescription());
            durationField.setText(service.getDuration().toString());
            selectionField.getSelectionModel().select(service.getType());
            
            newServiceLabel.setText("Измеяем услугу");
        }
    }
    
    @FXML
    private void saveAction(ActionEvent event) {
        service.setName(nameField.getText());
        service.setPrice(Integer.valueOf(priceField.getText()));
        service.setDescription(descField.getText());
        service.setDuration(Integer.valueOf(durationField.getText()));
        service.setStatus(1);
        service.setType(selectionField.getSelectionModel().getSelectedItem());
        
        if (!isEdition) {
            ServiceDao.createService(service);
            Node source = (Node) event.getSource(); 
            Stage stage = (Stage) source.getScene().getWindow(); 
            stage.close();
            ServicesController.refreshTable(ServiceDao.getAllServices());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Успех");
            alert.setHeaderText(null);
            alert.setContentText("Новая услуга была успешно добавлена!");
            alert.showAndWait();
        } else {
            ServiceDao.updateService(service);
            Node source = (Node) event.getSource(); 
            Stage stage = (Stage) source.getScene().getWindow(); 
            stage.close();
            ServicesController.refreshTable(ServiceDao.getAllServices());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Успех");
            alert.setHeaderText(null);
            alert.setContentText("Информация об услуге была успешно изменена!");
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
