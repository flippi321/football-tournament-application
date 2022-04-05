package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ErrorFormController {
    @FXML
    private TextField inputTopic;
    @FXML
    private Label errorLabel;

    @FXML
    public void settingsButtonClick(Event event) {
    }

    @FXML
    public void sendMail(ActionEvent actionEvent) {
        if (inputTopic.getText().isBlank()) {
            errorLabel.setText("You most enter a topic.");
            return;
        }
        errorLabel.setText("");

    }

    @FXML
    public void exitButtonClick(Event event) {
        System.exit(0);
    }

    @FXML
    public void backButtonClick(Event event) throws IOException {
        SceneManager.setView("main");
    }
}
