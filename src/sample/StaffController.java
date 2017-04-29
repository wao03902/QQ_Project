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
public class StaffController {
    public void saveAction(ActionEvent actionEvent) {
    }

    public void cancelAction(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage parentStage = (Stage) source.getScene().getWindow();
        parentStage.close();
    }
}
