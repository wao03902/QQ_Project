/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivisoft.salon;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ivisoft.salon.controllers.LoginController;
import com.ivisoft.salon.dao.MasterDao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Андпрей
 */

public class QQ_Project extends Application {
    
    private Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 200;
    private final double MINIMUM_WINDOW_HEIGHT = 200;
    
   @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            System.out.println("Hello QQ!");
            stage.setTitle("FXML Login Sample");
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            goToLogin();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(QQ_Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(MasterDao.getMasterById(1));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Application.launch(QQ_Project.class, (java.lang.String[])null);
    }
    
    private void goToLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("/com/ivisoft/salon/gui/login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QQ_Project.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
//        System.getProperty(key)
        InputStream in = QQ_Project.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(QQ_Project.class.getResource(fxml));
        Pane page;
        try {
            page = (Pane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
}
