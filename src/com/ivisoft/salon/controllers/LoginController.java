/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivisoft.salon.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.mindrot.jbcrypt.BCrypt;

import com.ivisoft.salon.QQ_Project;
import com.ivisoft.salon.utils.JavaFXUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private Button okButton;
    @FXML
    private Button closeButton;
    @FXML
    private PasswordField passTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Hyperlink changePassHiperLink;

    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void loginAction(ActionEvent event) {
        
        QQ_Project.primaryStage.close();
        
        //            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/ivisoft/salon/gui/main.fxml"), null, new JavaFXBuilderFactory()));
                    QQ_Project.primaryStage.setScene(QQ_Project.mainScene);
                    QQ_Project.primaryStage.show();
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
