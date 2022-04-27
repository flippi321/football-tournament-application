package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.util.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Controller for the error-form page
 *
 * @author jfben
 */
public class ErrorFormController {
    @FXML
    private TextField inputTopic;
    @FXML
    private Label errorLabel;
    @FXML
    private BorderPane root;

    @Deprecated
    public void initialize() {
        root.getStylesheets().add(Main.class.getResource(Admin.getActiveStyle()).toExternalForm());
    }

    @FXML
    public void settingsButtonClick(Event event) throws IOException {
        SceneManager.setView("settings");
    }

    @FXML
    public void sendMail(ActionEvent actionEvent) {
        if (inputTopic.getText().isBlank()) {
            errorLabel.setText("You most enter a topic.");
            return;
        }
        errorLabel.setText("");
        // TODO: Add functionality
    }

    @FXML
    public void exitButtonClick(Event event) throws IOException {
        SceneManager.setView("main");
    }

    @FXML
    public void backButtonClick(Event event) throws IOException {
        SceneManager.goToLastScene();
    }
}
