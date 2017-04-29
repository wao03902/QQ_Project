/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import qq_project.QQ_Project;

/**
 * models Controller class
 *
 * @author �������
 */
public class LoginController extends Dialog {

    @FXML
    private Button okButton;
    @FXML
    private Button closeButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginFiled;
    @FXML
    private Hyperlink changePasswordLabel;


    private QQ_Project application;


    public void setApp(QQ_Project application) {
        this.application = application;
    }



    //LOOK THIS
    @FXML
    private void loginAction(ActionEvent event) throws Exception {

        if(loginFiled.getText().equals("1")){
            if(passwordField.getText().equals("2")){
                Parent root = FXMLLoader.load(getClass().getResource("../models/app.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Main");
                primaryStage.setResizable(true);
                primaryStage.setMinWidth(800);
                primaryStage.setMinHeight(600);
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
                return;
            }
        }

    }


    public void changePasswordAction(ActionEvent actionEvent) {
    }

    public void closeAction(ActionEvent actionEvent) {
        System.exit(1);
    }
}
