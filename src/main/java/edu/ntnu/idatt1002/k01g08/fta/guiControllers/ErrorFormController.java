package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ErrorFormController {
    @FXML
    private TextField inputTopic;

    @FXML
    public void settingsButtonClick(Event event) {
    }

    @FXML
    public void sendMail(ActionEvent actionEvent) {

    }

    @FXML
    public void exitButtonClick(Event event) {
        System.exit(0);
    }

    @FXML
    public void reportButtonClick(Event event) {
    }
}
