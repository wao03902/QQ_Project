package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Администратор on 26.04.2017.
 */
public class MainController  {

    @FXML
    Label dataLabel;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();

    public void logoutAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../models/login.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void addClientAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../models/addClient.fxml"));
        Stage stage = new Stage();
        stage.setTitle("AddClient");
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Node source = (Node) actionEvent.getSource();
        Stage parentStage = (Stage) source.getScene().getWindow();
        stage.initOwner(parentStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }

    public void addServiceAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../models/addServices.fxml"));
        Stage stage = new Stage();
        stage.setTitle("AddService");
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Node source = (Node) actionEvent.getSource();
        Stage parentStage = (Stage) source.getScene().getWindow();
        stage.initOwner(parentStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }

    public void sectionAction(ActionEvent actionEvent) {
    }

    public void addStaffAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../models/addStaff.fxml"));
        Stage stage = new Stage();
        stage.setTitle("AddStaff");
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Node source = (Node) actionEvent.getSource();
        Stage parentStage = (Stage) source.getScene().getWindow();
        stage.initOwner(parentStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
}
