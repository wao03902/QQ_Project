package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Администратор on 26.04.2017.
 */
public class MainController {

    public void scheduleAction(ActionEvent actionEvent) {

    }

    public void servicesAction(ActionEvent actionEvent) {
    }

    public void customersAction(ActionEvent actionEvent) {
    }

    public void staffAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../models/staff.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Main");
        primaryStage.setResizable(false);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void statusAction(ActionEvent actionEvent) {
    }
}
