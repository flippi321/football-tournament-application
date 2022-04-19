package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller for the new player page
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
    public void createTeam(ActionEvent actionEvent) {
        if (teamNameInput.getText().isBlank() || numOfPlayersInput.getText().isBlank()) {
            errorLabel.setText("Missing requirements");
            return;
        }

        int numberOfPlayers;
        try {
            numberOfPlayers = Integer.parseInt(numOfPlayersInput.getText());
        } catch (IllegalArgumentException e) {
            errorLabel.setText("Must enter a valid number");
            return;
        }

        errorLabel.setText("");
        // TODO: 19.04.2022 Create team and goto createNewPlayerForTeam
    }
}