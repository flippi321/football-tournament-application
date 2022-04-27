package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.util.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller for the match page
 *
 * @author jfben
 */
public class MatchController {
    @FXML
    private BorderPane root;
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
    private Label awayTeamLabel2;
    @FXML
    private Label homeTeamLabel2;
    @FXML
    private ImageView reportButton;
    @FXML
    private ImageView settingsButton;
    @FXML
    private ImageView backButton;
    @FXML
    private ImageView homeButton;

    private String homeTeamName;
    private String awayTeamName;
    private boolean penaltyShootout;
    private Timeline timeline;
    private ArrayList<Integer> homeYellowCards = new ArrayList<>();
    private ArrayList<Integer> awayYellowCards = new ArrayList<>();

    @Deprecated
    public void initialize() {
        root.getStylesheets().add(Main.class.getResource(Admin.getActiveStyle()).toExternalForm());
        Tooltip.install(reportButton, new Tooltip("Go to report page"));
        Tooltip.install(settingsButton, new Tooltip("Go to settings"));
        Tooltip.install(backButton, new Tooltip("Go back to last page"));
        Tooltip.install(homeButton, new Tooltip("Go to home page"));
        disableButtons();
        nextButton.setText("Start match");
        penaltyShootout = false;

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
        lastEventLabel.setText("");
    }

    private void disableButtons() {
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
    }

    private void activateButtons() {
        homeGoalButton.setDisable(false);
        homeYellowCardButton.setDisable(false);
        homeRedCardButton.setDisable(false);
        homeSubButton.setDisable(false);
        homeInjButton.setDisable(false);
        awayGoalButton.setDisable(false);
        awayYellowCardButton.setDisable(false);
        awayRedCardButton.setDisable(false);
        awaySubButton.setDisable(false);
        awayInjButton.setDisable(false);
        undoButton.setDisable(false);
    }

    private void matchEnded() {
        disableButtons();
        nextButton.setText("Go to match report");
        timeline.stop();
        timeLabel.setText("Match finished");
        Admin.getActiveTournament().endMatch();
    }

    @FXML
    public void updateTime() {
        timeLabel.setText(Admin.getActiveMatch().currentTime());
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
        if (homePlayerList.getValue() == null) {
            errorLabel.setText("You must select a player");
            return;
        }

        int playerNum = Integer.parseInt(homePlayerList.getValue().toString().split(" ")[0]);
        Object player = homePlayerList.getValue();
        homePlayerList.getItems().remove(player);

        lastEventLabel.setText(homeTeamName + "s number " + playerNum + " got substituted");
        homePlayerList.setValue(null);
        awayPlayerList.setValue(null);
        errorLabel.setText("");
    }

    @FXML
    public void homeYellowCard(ActionEvent actionEvent) {
        if (homePlayerList.getValue() == null) {
            errorLabel.setText("You must select a player");
            return;
        }

        int playerNum = Integer.parseInt(homePlayerList.getValue().toString().split(" ")[0]);
        Admin.getActiveMatch().addFoul(true, playerNum, "Yellow card", 1, null);
        if (homeYellowCards.contains(playerNum)) {
            Object player = homePlayerList.getValue().toString();
            homePlayerList.getItems().remove(player);
        } else {
            homeYellowCards.add(playerNum);
        }

        lastEventLabel.setText("Yellow card given to number " + playerNum);
        homePlayerList.setValue(null);
        awayPlayerList.setValue(null);
        errorLabel.setText("");
    }

    @FXML
    public void homeInj(ActionEvent actionEvent) {
        if (homePlayerList.getValue() == null) {
            errorLabel.setText("You must select a player");
            return;
        }

        int playerNum = Integer.parseInt(homePlayerList.getValue().toString().split(" ")[0]);
        Object player = homePlayerList.getValue();
        homePlayerList.getItems().remove(player);

        lastEventLabel.setText("Player number " + playerNum + " got injured");

        lastEventLabel.setText(Admin.getActiveMatch().getLastGameEvent().toString());
        homePlayerList.setValue(null);
        awayPlayerList.setValue(null);
        errorLabel.setText("");
    }

    @FXML
    public void awayGoal(ActionEvent actionEvent) {
        if (penaltyShootout) {
            int scoringPlayer = Integer.parseInt(awayPlayerList.getItems().get(0).toString().split(" ")[0]);
            Admin.getActiveMatch().addGoal(false, scoringPlayer, -1, null);
            matchEnded();
            Admin.getActiveMatch().end();
            errorLabel.setText("");
            lastEventLabel.setText(awayTeamName + " won the match on penalties!");
            resultLabel.setText(Admin.getActiveMatch().getHomeTeamScore() + " : " + Admin.getActiveMatch().getAwayTeamScore());
            return;
        }

        if (homePlayerList.getValue() == null && awayPlayerList.getValue() == null) {
            errorLabel.setText("You must select a scoring player.");
            return;
        }
        else if (awayPlayerList.getValue() != null) {
            int scoringPlayer = Integer.parseInt(awayPlayerList.getValue().toString().split(" ")[0]);
            Admin.getActiveMatch().addGoal(false, scoringPlayer, -1, null);
        } else {
            int scoringPlayer = Integer.parseInt(homePlayerList.getValue().toString().split(" ")[0]);
            Admin.getActiveMatch().addSelfGoal(true, scoringPlayer, null);
        }
        errorLabel.setText("");
        lastEventLabel.setText(Admin.getActiveMatch().getLastGameEvent().toString());
        resultLabel.setText(Admin.getActiveMatch().getHomeTeamScore() + " : " + Admin.getActiveMatch().getAwayTeamScore());
        homePlayerList.setValue(null);
        awayPlayerList.setValue(null);
    }

    @FXML
    public void awayYellowCard(ActionEvent actionEvent) {
        if (awayPlayerList.getValue() == null) {
            errorLabel.setText("You must select a player");
            return;
        }

        int playerNum = Integer.parseInt(awayPlayerList.getValue().toString().split(" ")[0]);
        Admin.getActiveMatch().addFoul(false, playerNum, "Yellow card", 1, null);
        if (awayYellowCards.contains(playerNum)) {
            Object player = awayPlayerList.getValue().toString();
            awayPlayerList.getItems().remove(player);
        } else {
            awayYellowCards.add(playerNum);
        }

        lastEventLabel.setText("Yellow card given to number " + playerNum);
        homePlayerList.setValue(null);
        awayPlayerList.setValue(null);
        errorLabel.setText("");
    }

    @FXML
    public void awayInj(ActionEvent actionEvent) {
        if (awayPlayerList.getValue() == null) {
            errorLabel.setText("You must select a player");
            return;
        }

        int playerNum = Integer.parseInt(awayPlayerList.getValue().toString().split(" ")[0]);
        Object player = awayPlayerList.getValue();
        awayPlayerList.getItems().remove(player);

        lastEventLabel.setText("Player number " + playerNum + " got injured");
        homePlayerList.setValue(null);
        awayPlayerList.setValue(null);
        errorLabel.setText("");
    }

    @FXML
    public void homeGoal(ActionEvent actionEvent) {
        if (penaltyShootout) {
            int scoringPlayer = Integer.parseInt(homePlayerList.getItems().get(0).toString().split(" ")[0]);
            Admin.getActiveMatch().addGoal(true, scoringPlayer, -1, null);
            matchEnded();
            Admin.getActiveMatch().end();
            errorLabel.setText("");
            lastEventLabel.setText(homeTeamName + " won the match on penalties!");
            resultLabel.setText(Admin.getActiveMatch().getHomeTeamScore() + " : " + Admin.getActiveMatch().getAwayTeamScore());
            return;
        }

        if (homePlayerList.getValue() == null && awayPlayerList.getValue() == null) {
            errorLabel.setText("You must select a scoring player.");
            return;
        }
        else if (homePlayerList.getValue() != null) {
            int scoringPlayer = Integer.parseInt(homePlayerList.getValue().toString().split(" ")[0]);
            Admin.getActiveMatch().addGoal(true, scoringPlayer, -1, null);
        } else {
            int scoringPlayer = Integer.parseInt(awayPlayerList.getValue().toString().split(" ")[0]);
            Admin.getActiveMatch().addSelfGoal(false, scoringPlayer, null);
        }
        errorLabel.setText("");
        lastEventLabel.setText(Admin.getActiveMatch().getLastGameEvent().toString());
        resultLabel.setText(Admin.getActiveMatch().getHomeTeamScore() + " : " + Admin.getActiveMatch().getAwayTeamScore());
        homePlayerList.setValue(null);
        awayPlayerList.setValue(null);
    }

    @FXML
    public void awaySub(ActionEvent actionEvent) {
        if (awayPlayerList.getValue() == null) {
            errorLabel.setText("You must select a player");
            return;
        }

        int playerNum = Integer.parseInt(awayPlayerList.getValue().toString().split(" ")[0]);
        Object player = awayPlayerList.getValue();
        awayPlayerList.getItems().remove(player);

        lastEventLabel.setText(awayTeamName + "s number " + playerNum + " got substituted");
        homePlayerList.setValue(null);
        awayPlayerList.setValue(null);
        errorLabel.setText("");
    }

    @FXML
    public void awayRedCard(ActionEvent actionEvent) {
        if (awayPlayerList.getValue() == null) {
            errorLabel.setText("You must select a player");
            return;
        }

        int playerNum = Integer.parseInt(awayPlayerList.getValue().toString().split(" ")[0]);
        Admin.getActiveMatch().addFoul(false, playerNum, "Red card", 2, null);
        Object player = awayPlayerList.getValue().toString();
        awayPlayerList.getItems().remove(player);

        lastEventLabel.setText("Red card given to number " + playerNum);
        homePlayerList.setValue(null);
        awayPlayerList.setValue(null);
        errorLabel.setText("");
    }

    @FXML
    public void homeRedCard(ActionEvent actionEvent) {
        if (homePlayerList.getValue() == null) {
            errorLabel.setText("You must select a player");
            return;
        }

        int playerNum = Integer.parseInt(homePlayerList.getValue().toString().split(" ")[0]);
        Admin.getActiveMatch().addFoul(true, playerNum, "Red card", 2, null);
        Object player = homePlayerList.getValue();
        homePlayerList.getItems().remove(player);

        lastEventLabel.setText("Red card given to number " + playerNum);
        homePlayerList.setValue(null);
        awayPlayerList.setValue(null);
        errorLabel.setText("");
    }

    @FXML
    public void undo(ActionEvent actionEvent) {
        try {
            Admin.getActiveMatch().getLastGameEvent();
        } catch (IndexOutOfBoundsException e) {
            errorLabel.setText("No game event to undo");
            return;
        }
        if (!Admin.getActiveMatch().getLastGameEvent().getClass().getSimpleName().equals("Goal")) {
            errorLabel.setText("Can only undo goals");
            return;
        }
        Admin.getActiveMatch().removeLastGameEvent();
        lastEventLabel.setText("Removed last game event");

        resultLabel.setText(Admin.getActiveMatch().getHomeTeamScore() + " : " + Admin.getActiveMatch().getAwayTeamScore());
        homePlayerList.setValue(null);
        awayPlayerList.setValue(null);
        errorLabel.setText("");
    }

    @FXML
    public void next(ActionEvent actionEvent) throws IOException {
        settingsButton.setOpacity(0.2);
        settingsButton.setDisable(true);
        reportButton.setOpacity(0.2);
        reportButton.setDisable(true);
        backButton.setOpacity(0.2);
        backButton.setDisable(true);
        homeButton.setOpacity(0.2);
        homeButton.setDisable(true);

        if (!Admin.getActiveMatch().isStarted()) {
            Admin.getActiveMatch().start();
            activateButtons();
            nextButton.setText("Halftime");
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                updateTime();
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
        else if (Admin.getActiveMatch().isPlaying()) {
            if (Admin.getActiveMatch().currentHalf() == 4) { //Overtime
                if (Admin.getActiveMatch().getHomeTeamScore() == Admin.getActiveMatch().getAwayTeamScore()) {
                    penaltyShootout = true;
                    disableButtons();
                    homeGoalButton.setDisable(false);
                    awayGoalButton.setDisable(false);
                    timeline.stop();
                    timeLabel.setText("120:00");
                    lastEventLabel.setText("Select the winner by giving a winning goal.");
                } else {
                    Admin.getActiveMatch().end();
                    matchEnded();
                }
            }
            else if (Admin.getActiveMatch().currentHalf() == 2) { //Second half is finished
                if (Admin.getActiveMatch().getHomeTeamScore() == Admin.getActiveMatch().getAwayTeamScore()) {
                    Admin.getActiveMatch().pause();
                    disableButtons();
                    nextButton.setText("Start overtime");
                } else {
                    Admin.getActiveMatch().end();
                    matchEnded();
                }
            } else { //First half finished
                Admin.getActiveMatch().pause();
                disableButtons();
                nextButton.setText("Start second-half");
            }
        }
        else if (Admin.getActiveMatch().onPause()) {
            activateButtons();
            Admin.getActiveMatch().start();
            lastEventLabel.setText("Match started!");
            nextButton.setText("End half");
        }
        else if (Admin.getActiveMatch().isFinished()) {
            SceneManager.setView("matchReport");
        }
    }
}