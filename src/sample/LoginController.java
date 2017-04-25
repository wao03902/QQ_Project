/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;
import qq_project.QQ_Project;

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
        hash();
        
        if (loginFiled.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Введите логин!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (passwordField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Введите пароль!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * "
                    + "from qq_login "
                    + "where BINARY lower(login) = BINARY '" + loginFiled.getText().trim().toLowerCase() + "'");
            boolean succeessEntry = true;
            boolean loginExist = false;
            while (rs.next()) {
                loginExist = true;
                if (!rs.getString("login_status").equals("1")) {
                    succeessEntry = false;
                    JOptionPane.showMessageDialog(null, "Пользователь заблокирован!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (!BCrypt.checkpw(passwordField.getText(),rs.getString("hashed_password"))) {
                        succeessEntry = false;
                        JOptionPane.showMessageDialog(null, "Пароль неверный!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            rs.close();
            stmt.close();
            con.close();
            if (!loginExist) {
                JOptionPane.showMessageDialog(null, "Пользователь с таким логином не зарегистрирован!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.println("Successed login!");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
