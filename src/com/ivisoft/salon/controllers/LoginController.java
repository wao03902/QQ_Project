/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivisoft.salon.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;

import com.ivisoft.salon.QQ_Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Андпрей
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

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        loginFiled.setText("AnDrUsHa");
        passwordField.setText("Andrusha");
    }

    public void setApp(QQ_Project application) {
        this.application = application;
    }

    @FXML
    private void loginAction(ActionEvent event) throws Exception {

            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/ivisoft/salon/gui/main.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Main window");
            stage.setScene(new Scene(root1));
            stage.show();
          
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
