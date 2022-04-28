package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.util.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller for the tournament-overview page
 *
 * @author johnfb, teodorbi
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
    @FXML
    private ImageView reportButton;
    @FXML
    private ImageView settingsButton;
    @FXML
    private ImageView backButton;
    @FXML
    private ImageView homeButton;

    private String homeTeam;
    private String awayTeam;

    /**
     * Initializes the view on load.
     */
    @Deprecated
    public void initialize() {
        root.getStylesheets().add(Main.class.getResource(Admin.getActiveStyle()).toExternalForm());
        Tooltip.install(reportButton, new Tooltip("Go to report page"));
        Tooltip.install(settingsButton, new Tooltip("Go to settings"));
        Tooltip.install(backButton, new Tooltip("Go back to last page"));
        Tooltip.install(homeButton, new Tooltip("Go to home page"));
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
     * Goes to the report page view when the user clicks on the report button
     * @param event Click event
     * @throws IOException if an error occurs
     */
    @FXML
    public void reportButtonClick(Event event) throws IOException {
        SceneManager.setView("errorForm");
    }

    /**
     * Goes to the main page view when the user clicks on the home button
     * @param event Click event
     * @throws IOException if an error occurs
     */
    @FXML
    public void exitButtonClick(Event event) throws IOException {
        SceneManager.setView("main");
    }

    /**
     * Goes to the last page view when the user clicks on the back button
     * @param event Click event
     * @throws IOException if an error occurs
     */
    @FXML
    public void backButtonClick(Event event) throws IOException {
        SceneManager.goToLastScene();
    }

    /**
     * Goes to tournament overview page when the user clicks on the next button
     * @param actionEvent Click Event
     * @throws IOException if an error occurs
     */
    @FXML
    public void next(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("tournamentOverview");
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

    /**
     * Enables user to use enter key to use the back button
     * @param event KeyEvent
     * @throws IOException if an error occurs
     */
    @FXML
    public void backButtonEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            backButtonClick(event);
        }
    }

    /**
     * Enables user to use enter key to go to the home page
     * @param event KeyEvent
     * @throws IOException if an error occurs
     */
    @FXML
    public void homeButtonEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            exitButtonClick(event);
        }
    }
}