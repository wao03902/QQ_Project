/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivisoft.salon.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.mindrot.jbcrypt.BCrypt;

import com.ivisoft.salon.QQ_Project;
import com.ivisoft.salon.utils.JavaFXUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

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

    public void initialize(URL url, ResourceBundle rb) {
        loginFiled.setText("AnDrUsHa");
        passwordField.setText("Andrusha");
    }

    public void setApp(QQ_Project application) {
        this.application = application;
    }

    @FXML
    private void loginAction(ActionEvent event) {
        JavaFXUtil.replaceSceneContent(event, "/com/ivisoft/salon/gui/main.fxml");
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
