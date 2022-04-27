package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.util.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Controller for the settings page
 *
 * @author jfben
 */
public class SettingsController {
    @FXML
    private BorderPane root;
    @FXML
    private Label errorLabel;
    @FXML
    private RadioButton highContrastButton;
    @FXML
    private ImageView reportButton;
    @FXML
    private ImageView settingsButton;
    @FXML
    private ImageView backButton;
    @FXML
    private ImageView homeButton;

    @FXML
    public void initialize() {
        root.getStylesheets().add(Main.class.getResource(Admin.getActiveStyle()).toExternalForm());
        Tooltip.install(reportButton, new Tooltip("Go to report page"));
        Tooltip.install(settingsButton, new Tooltip("Go to settings"));
        Tooltip.install(backButton, new Tooltip("Go back to last page"));
        Tooltip.install(homeButton, new Tooltip("Go to home page"));
    }

    @FXML
    public void settingsButtonClick(Event event) {
    }

    @FXML
    public void resetSettings(ActionEvent actionEvent) {
        Admin.setActiveStyle("main");
    }

    @FXML
    public void exitButtonClick(Event event) throws IOException {
        SceneManager.setView("main");
    }

    @FXML
    public void saveSettings(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("main");
    }

    @FXML
    public void reportButtonClick(Event event) throws IOException {
        SceneManager.setView("errorForm");
    }

    @FXML
    public void changeContrast(ActionEvent actionEvent) {
        Admin.setActiveStyle("contrast2");
    }

    @FXML
    public void backButtonClick(Event event) throws IOException {
        SceneManager.goToLastScene();
    }
}