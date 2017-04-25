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
public class LoginController extends Dialog implements Initializable {

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
    @FXML

    private QQ_Project application;
    private static final String url = "jdbc:mysql://localhost:3306/qqdb";
    private static final String user = "qq";
    private static final String password = "qqqq";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginFiled.setText("AnDrUsHa");
        passwordField.setText("Andrusha");
    }

    public void setApp(QQ_Project application) {
        this.application = application;
    }

    @FXML
    private void loginAction(ActionEvent event) throws Exception {

        if(loginFiled.getText().equals("1")){
            if(passwordField.getText().equals("2")){
                Parent root = FXMLLoader.load(getClass().getResource("../models/main.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Main");
                primaryStage.setResizable(false);
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
                return;
            }
        }
        System.out.println("Fail");

    }

    @FXML
    private void closeAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void changePasswordAction(ActionEvent event) {
    }

    void hash() {
        String Password1 = "1234";
        String Password1Hash = BCrypt.hashpw(Password1, BCrypt.gensalt(8));
        System.out.println(Password1Hash);
        String Password2 = "1234";
        String Password2Hash = BCrypt.hashpw(Password2, BCrypt.gensalt(8));
        System.out.println(Password2Hash);
        boolean matched = BCrypt.checkpw(Password2, Password1Hash);
        System.out.println(matched);
    }
}
