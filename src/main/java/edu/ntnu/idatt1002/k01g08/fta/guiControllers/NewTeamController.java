package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller for the new team page
 *
 * @author jfben
 */
public class NewTeamController {
    @FXML
    private Label errorLabel;
    @FXML
    private TextField teamNameInput;
    @FXML
    private TextField numOfPlayersInput;

    @FXML
    public void settingsButtonClick(Event event) throws IOException {
        SceneManager.setView("settings");
    }

    @FXML
    public void reportButtonClick(Event event) throws IOException {
        SceneManager.setView("errorForm");
    }

    @FXML
    public void exitButtonClick(Event event) throws IOException {
        SceneManager.setView("main");
    }

    @FXML
    public void backButtonClick(Event event) throws IOException {
        SceneManager.goToLastScene();
    }

    @FXML
    public void discardChanges(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("teamManagement");
    }

    @FXML
    public void createTeam(ActionEvent actionEvent) throws IOException {
        if (teamNameInput.getText().isBlank() || numOfPlayersInput.getText().isBlank()) {
            errorLabel.setText("Missing requirements");
            return;
        }

        int numberOfPlayers;
        try {
            numberOfPlayers = Integer.parseInt(numOfPlayersInput.getText());
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
            return;
        }

        if (numberOfPlayers < 1) {
            errorLabel.setText("The team must contain at least 1 player");
            return;
        }

        try {
            Admin.addTeam(teamNameInput.getText(), numberOfPlayers);
            SceneManager.setView("newTeamPlayer");
        } catch (IllegalArgumentException e) {
            errorLabel.setText("The team name is already in use");
        }
    }
}