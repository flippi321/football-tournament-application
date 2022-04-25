package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller for the tournament-overview page
 *
 * @author jfben
 */
public class TournamentOverviewController {

    @FXML
    private Label errorLabel;
    @FXML
    private Label registeredTeamsLabel;

    ArrayList<String> teamsAdded = new ArrayList<>();
    @FXML
    private Button nextMatchButton;
    @FXML
    private Label tournamentNameLabel;
    @FXML
    private VBox historyContainer;
    @FXML
    private Label nextMatchLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label teamsLeftLabel;

    @Deprecated
    public void initialize() {
        tournamentNameLabel.setText(Admin.getActiveTournament().getTournamentName());
        nextMatchLabel.setText(Admin.getNextMatch().getHomeTeam().getName() + " vs " + Admin.getNextMatch().getAwayTeam().getName());
        teamsLeftLabel.setText(Admin.getActiveTournament().getNumberOfTeams() + " teams are registered for this tournament");
        int matchesLeft = Admin.getActiveTournament().getUpcomingMatches().size();
        if (matchesLeft > 1) {
            statusLabel.setText(matchesLeft + " confirmed matches left");
        } else if (matchesLeft == 1) {
            statusLabel.setText(matchesLeft + " confirmed match left");
        } else {
            statusLabel.setText("All matches have been played");
        }
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
    public void nextMatch(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("match");
    }

    @FXML
    public void save(ActionEvent actionEvent) {
    }
}