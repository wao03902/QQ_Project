package com.ivisoft.salon.utils;

import java.io.IOException;

import com.ivisoft.salon.QQ_Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXUtil {
    
    public static Parent replaceSceneContent(ActionEvent event, String fxml, String css) {
        
        Node source = (Node) event.getSource(); 
        Stage stage = (Stage) source.getScene().getWindow();
        
        return replaceSceneContent(stage, fxml);
    }

    public static Parent replaceSceneContent(Stage stage, String fxml) {
        Parent page = null;
        try {
            page = (Parent) FXMLLoader.load(QQ_Project.class.getResource(fxml), null, new JavaFXBuilderFactory());
            Scene scene = stage.getScene();
            if (scene == null) {
                scene = new Scene(page);
//                scene.getStylesheets().add(css);
                stage.setScene(scene);
            } else {
                stage.getScene().setRoot(page);
            }
            stage.sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }
    
    public static Stage createStage(String fxml) {
        Stage stage = new Stage();
        Parent page = null;
        try {
            page = (Parent) FXMLLoader.load(QQ_Project.class.getResource(fxml), null, new JavaFXBuilderFactory());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(page);
        scene.setRoot(page);
//        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.sizeToScene();
        return stage;
    }
}
