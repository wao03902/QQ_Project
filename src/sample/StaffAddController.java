package sample;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Created by Администратор on 26.04.2017.
 */
public class StaffAddController {

    public void uploadFotoAction(ActionEvent actionEvent) {
    }

    public void saveAction(ActionEvent actionEvent) {
    }

    public void exitAction(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }
}
