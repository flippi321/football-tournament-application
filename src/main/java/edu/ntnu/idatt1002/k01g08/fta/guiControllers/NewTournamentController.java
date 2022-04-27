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
 * Controller for the new tournament page
 *
 * @author jfben
 */
public class NewTournamentController {
    @FXML
    private BorderPane root;
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

    @Deprecated
    public void initialize() {
        root.getStylesheets().add(Main.class.getResource(Admin.getActiveStyle()).toExternalForm());
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
    public void exitButtonClick(Event event) throws IOException {
        SceneManager.setView("main");
    }

    @FXML
    public void backButtonClick(Event event) throws IOException {
        SceneManager.goToLastScene();
    }

    @FXML
    public void newTournament(ActionEvent actionEvent) throws IOException {
        if (tournamentNameInput.getText().isBlank() || numberOfTeamsInput.getText().isBlank()) {
            errorLabel.setText("Missing requirements");
            return;
        }

        int numberOfTeams;
        try {
            numberOfTeams = Integer.parseInt(numberOfTeamsInput.getText());
        } catch (IllegalArgumentException e) {
            errorLabel.setText("You must enter a number in number of teams");
            return;
        }

        if (Admin.numberOfTeamsInvalid(numberOfTeams)) {
            errorLabel.setText("In a knockout-tournament the number of teams must be 2-4-8-16-32 . . .");
            return;
        }

        Admin.setTournamentToCreateName(tournamentNameInput.getText());
        Admin.setNumOfTeamsToAdd(numberOfTeams);
        SceneManager.setView("newTournamentTeamSelection");
    }
}