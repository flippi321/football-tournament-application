package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller for the new tournament page
 *
 * @author jfben
 */
public class NewTournamentController {

    @FXML
    private TextField locationInput;
    @FXML
    private TextField tournamentNameInput;
    @FXML
    private TextField numberOfTeamsInput;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField dateInput;
    @FXML
    private TextField winningPrizeInput;

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
    public void newTournament(ActionEvent actionEvent) {
        if (tournamentNameInput.getText().isBlank() || numberOfTeamsInput.getText().isBlank()) {
            errorLabel.setText("Missing requirements");
            return;
        }

        try {
            Integer.parseInt(numberOfTeamsInput.getText());
        } catch (IllegalArgumentException e) {
            errorLabel.setText("You must enter a number in number of teams");
            return;
        }

        errorLabel.setText("");

        // TODO: Create a tournament
    }
}