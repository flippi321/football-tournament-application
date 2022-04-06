package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.io.IOException;

/**
 * Controller for the settings page
 *
 * @author jfben
 */
public class SettingsController {
    @FXML
    private ComboBox textSizeBox;
    @FXML
    private Label errorLabel;
    @FXML
    private RadioButton highContrastButton;

    @FXML
    public void initialize() {
        textSizeBox.getItems().addAll(
          "Tiny",
          "Normal",
          "Large"
        );
    }

    @FXML
    public void changeText(ActionEvent actionEvent) {
        // TODO: Add functionality
    }

    @FXML
    public void settingsButtonClick(Event event) {
    }

    @FXML
    public void resetSettings(ActionEvent actionEvent) {
        // TODO: Add functionality
    }

    @FXML
    public void exitButtonClick(Event event) throws IOException {
        SceneManager.setView("main");
    }

    @FXML
    public void saveSettings(ActionEvent actionEvent) {
        // TODO: Add functionality
    }

    @FXML
    public void reportButtonClick(Event event) throws IOException {
        SceneManager.setView("errorForm");
    }

    @FXML
    public void changeContrast(ActionEvent actionEvent) {
        // TODO: Add functionality
    }

    @FXML
    public void backButtonClick(Event event) throws IOException {
        SceneManager.goToLastScene();
    }
}