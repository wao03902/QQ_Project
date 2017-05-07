package com.ivisoft.salon.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;

public class JavaFXUtil {
    
    public static Scene createScene(Object obj, String name) {
        try {
            return new Scene(FXMLLoader.load(obj.getClass().getResource("/com/ivisoft/salon/gui/" + name + ".fxml"), null, new JavaFXBuilderFactory()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
