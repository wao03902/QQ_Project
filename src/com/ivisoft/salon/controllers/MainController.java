package com.ivisoft.salon.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import com.ivisoft.salon.Salon;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.Tile.SkinType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainController implements Initializable {
    
    @FXML
    private GridPane root;
    
    @FXML
    private ImageView logoPhoto;
    
    @FXML
    private ImageView userPhoto;
    
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
    private TableView mainTable;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tile tile = TileBuilder.create()
                .skinType(SkinType.CLOCK)
                .prefSize(183, 48)
                .backgroundColor(Color.WHITE)
                .foregroundBaseColor(Color.BLACK)
                .dateVisible(false)
                .locale(Locale.US)
                .running(true)
                .build();
        timeLabel.setAlignment(Pos.CENTER);
        timeLabel.setGraphic(tile);
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
    private void logoutAction(ActionEvent event) {
        
    }
    
    @FXML
    private void fullScreenAction(ActionEvent event) {
        
    }
    
    @FXML
    private void addVisit(ActionEvent event) {
        
    }
}
