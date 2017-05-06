package com.ivisoft.salon;

import java.io.IOException;
import java.sql.SQLException;

import com.ivisoft.salon.utils.DBUtil;
import com.ivisoft.salon.utils.JavaFXUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class QQ_Project extends Application {
    
    public static Stage primaryStage;
    public static Scene mainScene;
    public static Scene staffScene;
    
    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {
        try {
            
            mainScene = new Scene(FXMLLoader.load(getClass().getResource("/com/ivisoft/salon/gui/main.fxml"), null, new JavaFXBuilderFactory()));
            staffScene = new Scene(FXMLLoader.load(getClass().getResource("/com/ivisoft/salon/gui/staff.fxml"), null, new JavaFXBuilderFactory()));
            
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/ivisoft/salon/gui/login.fxml"), null, new JavaFXBuilderFactory()));
            
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login window");
            primaryStage.show();
            
            QQ_Project.primaryStage = primaryStage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       Application.launch(QQ_Project.class, (java.lang.String[])null);
    }
}