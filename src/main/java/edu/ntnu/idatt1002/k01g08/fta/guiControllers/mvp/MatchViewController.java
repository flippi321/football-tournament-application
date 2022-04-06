package edu.ntnu.idatt1002.k01g08.fta.guiControllers.mvp;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.objects.Match;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.util.Duration;

import java.io.IOException;

public class MatchViewController {

    @FXML
    private Label titleLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label resultLabel;
    @FXML
    private Button startButton;
    @FXML
    private Button endButton;
    @FXML
    private Button homeGoalButton;
    @FXML
    private Button awayGoalButton;

    private Match match = Main.getTournamentRegister().getTournamentList().get(0).getUpcomingMatches().get(0);
    private boolean hasPaused = false;

    @FXML
    public void initialize() {
        endButton.setDisable(true);
        homeGoalButton.setDisable(true);
        awayGoalButton.setDisable(true);
        endButton.setText("Pause");

        Main.getTournamentRegister().getTournamentList().get(0).startNextMatch();
        titleLabel.setText(match.getHomeTeam().getName() + " vs " + match.getAwayTeam().getName());
        resultLabel.setText("0 : 0");
    }


    @FXML
    public void awayGoal(ActionEvent actionEvent) {
        match.addGoal(false, 1, 0, match.currentMatchTime());
        resultLabel.setText(match.getHomeTeamScore() + " : " + match.getAwayTeamScore());
    }

    @FXML
    public void homeGoal(ActionEvent actionEvent) {
        match.addGoal(true, 1, 0, match.currentMatchTime());
        resultLabel.setText(match.getHomeTeamScore() + " : " + match.getAwayTeamScore());
    }

    @FXML
    public void startMatch(ActionEvent actionEvent) {
        startButton.setDisable(true);
        endButton.setDisable(false);
        homeGoalButton.setDisable(false);
        awayGoalButton.setDisable(false);
        match.start();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            updateTime();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
    @FXML
    public void updateTime() {
        timeLabel.setText(match.currentMatchTime());
    }

    @FXML
    public void endMatch(ActionEvent actionEvent) throws IOException {
        endButton.setDisable(true);
        homeGoalButton.setDisable(true);
        awayGoalButton.setDisable(true);
        startButton.setDisable(false);
        endButton.setText("End match");

        if (!hasPaused) {
            hasPaused = true;
            match.pause();
        } else {
            match.end();
            SceneManager.setView("matchReport");
        }
    }
}
