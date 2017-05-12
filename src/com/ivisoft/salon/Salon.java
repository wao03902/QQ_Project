package com.ivisoft.salon;

import static com.ivisoft.salon.utils.JavaFXUtil.createScene;

import java.sql.Connection;
import java.sql.SQLException;

import com.ivisoft.salon.dao.ClientDao;
import com.ivisoft.salon.utils.DBUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Salon extends Application {
    
    public static Stage stage;
    public static Scene mainScene;
    public static Scene staffScene;
    public static Scene servicesScene;
    public static Scene clientsScene;
    
    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {
        System.out.println(ClientDao.getClientById(1));
        
        Connection test = DBUtil.getConnection();
        if (test == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не удалось подключится к базе данных");
            alert.setContentText("Проверьте ваши настройки базы данных и повторите попытку!");
            alert.showAndWait();
            System.exit(0);
        }
        test.close();
        
        mainScene = createScene(this, "main");
        staffScene = createScene(this, "staff");
        clientsScene = createScene(this, "clients");
        servicesScene = createScene(this, "services");
        
        Scene scene = createScene(this, "login");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login window");
        primaryStage.show();
        
        Salon.stage = primaryStage;
    }

    public static void main(String[] args) {
        Application.launch(Salon.class, (java.lang.String[]) null);
    }
}