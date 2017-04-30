package com.ivisoft.salon;

import com.ivisoft.salon.utils.JavaFXUtil;

import javafx.application.Application;
import javafx.stage.Stage;

public class QQ_Project extends Application {
    
   @Override
    public void start(Stage primaryStage) {
        JavaFXUtil.replaceSceneContent(primaryStage, "/com/ivisoft/salon/gui/login.fxml");
        primaryStage.show();
    }

    public static void main(String[] args) {
       Application.launch(QQ_Project.class, (java.lang.String[])null);
    }
}
