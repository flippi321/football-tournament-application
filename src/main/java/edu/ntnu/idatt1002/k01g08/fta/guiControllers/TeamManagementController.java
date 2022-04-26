package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Controller for the team management page
 *
 * @author jfben
 */
public class TeamManagementController {
    @FXML
    private BorderPane root;
    @FXML
    private Label errorLabel;

    @FXML
    public void initialize() {
        root.getStylesheets().add(Main.class.getResource(Admin.getActiveStyle()).toExternalForm());
    }

    @FXML
    public void editExistingPlayer(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("editPlayer");
    }

    @FXML
    public void addPlayer(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("newPlayer");
    }

    @FXML
    public void addTeam(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("newTeam");
    }

    @FXML
    public void editTeam(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("editTeam");
    }

    @FXML
    public void exitButtonClick(Event event) throws IOException {
        SceneManager.setView("main");
    }

    @FXML
    public void settingsButtonClick(Event event) throws IOException {
        SceneManager.setView("settings");
    }

    @FXML
    public void reportButtonClick(Event event) throws IOException {
        SceneManager.setView("errorForm");
    }

    @FXML
    public void backButtonClick(Event event) throws IOException {
        SceneManager.goToLastScene();
    }
}