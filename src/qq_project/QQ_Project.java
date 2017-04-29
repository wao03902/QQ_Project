/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qq_project;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.LoginController;

public class QQ_Project extends Application {
    
   @Override
    public void start(Stage primaryStage) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("../models/login.fxml"));
       primaryStage.setTitle("Login");
       primaryStage.setResizable(false);
       Scene scene = new Scene(root);
       primaryStage.setScene(scene);
       primaryStage.show();
    }

    public static void main(String[] args) {
       launch(args);
    }

}
