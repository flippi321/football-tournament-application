package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.util.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Controller for the main page
 *
 * @author johnfb, teodorbi
 */
public class MainController{

    @FXML
    private ImageView reportButton;
    @FXML
    private ImageView settingsButton;
    @FXML
    private BorderPane root;

    /**
     * Initializes the view on load.
     */
    @Deprecated
    public void initialize() {
        root.getStylesheets().add(Main.class.getResource(Admin.getActiveStyle()).toExternalForm());
        Tooltip.install(reportButton, new Tooltip("Go to report page"));
        Tooltip.install(settingsButton, new Tooltip("Go to the settings"));
    }

    /**
     * Goes to the settings view when user clicks on the settings button
     * @param event Click event
     * @throws IOException if an error occurs
     */
    @FXML
    public void settingsButtonClick(Event event) throws IOException {
        SceneManager.setView("settings");
    }

    /**
     * Goes to the load tournament page when user clicks on the load tournament button
     * @param actionEvent Click Event
     * @throws IOException if an error occurs
     */
    @FXML
    public void loadTournament(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("loadTournament");
    }

    /**
     * Goes to the team management page when the user clicks on the team management button
     * @param actionEvent Click event
     * @throws IOException if an error occurs
     */
    @FXML
    public void teamManagement(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("teamManagement");
    }

    /**
     * Goes to the report page when the user clicks on the report button
     * @param event Click event
     * @throws IOException if an error occurs
     */
    @FXML
    public void reportButtonClick(Event event) throws IOException {
        SceneManager.setView("errorForm");
    }

    /**
     * Goes to the new tournament page when the user clicks on the new tournament button
     * @param actionEvent Click event
     * @throws IOException if an error occurs
     */
    @FXML
    public void newTournament(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("newTournament");
    }

    /**
     * Enables user to use enter key to go to the settings page
     * @param event KeyEvent
     * @throws IOException if an error occurs
     */
    @FXML
    public void settingsButtonEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            settingsButtonClick(event);
        }
    }

    /**
     * Enables user to use enter key to go to the report page
     * @param event KeyEvent
     * @throws IOException if an error occurs
     */
    @FXML
    public void reportButtonEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            reportButtonClick(event);
        }
    }
}