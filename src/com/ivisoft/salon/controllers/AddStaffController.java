package com.ivisoft.salon.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.ivisoft.salon.dao.MasterDao;
import com.ivisoft.salon.model.Master;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddStaffController implements Initializable {
    
    @FXML
    private TextField nameFiled;
    
    @FXML
    private TextField tel1Field;
    
    @FXML
    private TextField tel2Field;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private Button saveButton;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private Button loadPhoto;
    
    @FXML
    private ImageView photo;
    
    private Master master = new Master();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        photo.setFitWidth(100);
        photo.setPreserveRatio(true);
    }
    
    @FXML
    private void loadAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        
        File file = fileChooser.showOpenDialog((Stage) loadPhoto.getScene().getWindow());
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
        master.setExtraPhone(tel2Field.getText());
        master.setStatus(1);
        
        MasterDao.createMaster(master);
        
        Node source = (Node) event.getSource(); 
        Stage stage = (Stage) source.getScene().getWindow(); 
        stage.close();
        StaffController.refreshTable();
    }
    
    @FXML
    private void cancelAction(ActionEvent event) {

    }
}
