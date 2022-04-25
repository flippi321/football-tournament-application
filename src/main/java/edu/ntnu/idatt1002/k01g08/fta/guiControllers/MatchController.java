package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller for the match page
 *
 * @author jfben
 */
public class MatchController {

    @FXML
    private Label errorLabel;
    @FXML
    private Button homeRedCardButton;
    @FXML
    private Button homeInjButton;
    @FXML
    private Label homeTeamLabel;
    @FXML
    private Button homeGoalButton;
    @FXML
    private Button awayYellowCardButton;
    @FXML
    private Button awayRedCardButton;
    @FXML
    private Button homeYellowCardButton;
    @FXML
    private Button homeSubButton;
    @FXML
    private Button awayGoalButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button awayInjButton;
    @FXML
    private Label tournamentNameLabel;
    @FXML
    private Label awayTeamLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private ComboBox awayPlayerList;
    @FXML
    private Label lastEventLabel;
    @FXML
    private Button awaySubButton;
    @FXML
    private Button undoButton;
    @FXML
    private ComboBox homePlayerList;
    @FXML
    private Label resultLabel;
    @FXML
    private ImageView topButton1;
    @FXML
    private ImageView topButton2;
    @FXML
    private ImageView topButton3;
    @FXML
    private ImageView topButton4;
    @FXML
    private Label awayTeamLabel2;
    @FXML
    private Label homeTeamLabel2;

    private String homeTeamName;
    private String awayTeamName;

    @Deprecated
    public void initialize() {
        homeGoalButton.setDisable(true);
        homeYellowCardButton.setDisable(true);
        homeRedCardButton.setDisable(true);
        homeSubButton.setDisable(true);
        homeInjButton.setDisable(true);
        awayGoalButton.setDisable(true);
        awayYellowCardButton.setDisable(true);
        awayRedCardButton.setDisable(true);
        awaySubButton.setDisable(true);
        awayInjButton.setDisable(true);
        undoButton.setDisable(true);
        nextButton.setText("Start match");

        Admin.loadActiveMatch();
        homeTeamName = Admin.getActiveMatch().getHomeTeam().getName();
        awayTeamName = Admin.getActiveMatch().getAwayTeam().getName();
        tournamentNameLabel.setText(homeTeamName + " vs " + awayTeamName);
        resultLabel.setText(Admin.getActiveMatch().getHomeTeamScore() + " : " + Admin.getActiveMatch().getAwayTeamScore());
        homeTeamLabel.setText(homeTeamName);
        homeTeamLabel2.setText(homeTeamName);
        awayTeamLabel.setText(awayTeamName);
        awayTeamLabel2.setText(awayTeamName);
        homePlayerList.getItems().addAll(Admin.getPlayerStrings(homeTeamName));
        awayPlayerList.getItems().addAll(Admin.getPlayerStrings(awayTeamName));
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
    public void homeSub(ActionEvent actionEvent) {
    }

    @FXML
    public void homeYellowCard(ActionEvent actionEvent) {
    }

    @FXML
    public void homeInj(ActionEvent actionEvent) {
    }

    @FXML
    public void awayGoal(ActionEvent actionEvent) {
    }

    @FXML
    public void awayYellowCard(ActionEvent actionEvent) {
    }

    @FXML
    public void awayInj(ActionEvent actionEvent) {
    }

    @FXML
    public void homeGoal(ActionEvent actionEvent) {
    }

    @FXML
    public void awaySub(ActionEvent actionEvent) {
    }

    @FXML
    public void awayRedCard(ActionEvent actionEvent) {
    }

    @FXML
    public void homeRedCard(ActionEvent actionEvent) {
    }

    @FXML
    public void undo(ActionEvent actionEvent) {
    }

    @FXML
    public void next(ActionEvent actionEvent) {
        topButton1.setOpacity(0.2);
        topButton1.setDisable(true);
        topButton2.setOpacity(0.2);
        topButton2.setDisable(true);
        topButton3.setOpacity(0.2);
        topButton3.setDisable(true);
        topButton4.setOpacity(0.2);
        topButton4.setDisable(true);
    }
}