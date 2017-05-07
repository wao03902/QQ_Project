package com.ivisoft.salon;

import static com.ivisoft.salon.utils.JavaFXUtil.createScene;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Salon extends Application {
    
    public static Stage stage;
    public static Scene mainScene;
    public static Scene staffScene;
    public static Scene servicesScene;
    public static Scene clientsScene;
    
    @Override
    public void start(Stage primaryStage) {
        
        mainScene = createScene(this, "main");
        staffScene = createScene(this, "staff");
        clientsScene = createScene(this, "clients");
        servicesScene = createScene(this, "services");
        
        Scene scene = createScene(this, "login");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login window");
        primaryStage.show();
        
        Salon.stage = primaryStage;
    }

    public static void main(String[] args) {
        Application.launch(Salon.class, (java.lang.String[]) null);
    }
}