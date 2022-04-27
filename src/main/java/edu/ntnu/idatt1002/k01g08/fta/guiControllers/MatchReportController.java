package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.util.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller for the tournament-overview page
 *
 * @author jfben
 */
public class MatchReportController {
    @FXML
    private BorderPane root;
    @FXML
    private Label awayTeamLabel;
    @FXML
    private VBox eventList;
    @FXML
    private Label teamsLabel;
    @FXML
    private Label homeTeamLabel;
    @FXML
    private VBox awayPlayerList;
    @FXML
    private Label resultLabel;
    @FXML
    private VBox homePlayerList;

    private String homeTeam;
    private String awayTeam;

    @Deprecated
    public void initialize() {
        root.getStylesheets().add(Main.class.getResource(Admin.getActiveStyle()).toExternalForm());
        homeTeam = Admin.getCurrentReportedMatch().getHomeTeam().getName();
        awayTeam = Admin.getCurrentReportedMatch().getAwayTeam().getName();

        homeTeamLabel.setText(homeTeam);
        awayTeamLabel.setText(awayTeam);
        teamsLabel.setText(homeTeam + " vs " + awayTeam);
        resultLabel.setText(Admin.getCurrentReportedMatch().getHomeTeamScore() + " : " + Admin.getCurrentReportedMatch().getAwayTeamScore());
        for (String playerString : Admin.getPlayerStrings(homeTeam)) {
            Label label = new Label(playerString);
            homePlayerList.getChildren().add(label);
        }
        for (String playerString : Admin.getPlayerStrings(awayTeam)) {
            Label label = new Label(playerString);
            awayPlayerList.getChildren().add(label);
        }

        for (int i = 0; i < Admin.getCurrentReportedMatch().getMatchHistorySize(); i++) {
            Label label = new Label(Admin.getCurrentReportedMatch().getGameEvent(i).getEvent());
            eventList.getChildren().add(label);
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
    public void next(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("tournamentOverview");
    }
}