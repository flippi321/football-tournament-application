package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.objects.GameEvent;
import edu.ntnu.idatt1002.k01g08.fta.objects.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MatchReportController {
    @FXML
    private Label teamTitleLabel;
    @FXML
    private VBox eventList;
    @FXML
    private Label resultLabel;

    private Match match = Main.getTournamentRegister().getTournamentList().get(0).getCurrentMatch();

    @FXML
    public void initialize() {
        teamTitleLabel.setText(match.getHomeTeam().getName() + " vs " + match.getAwayTeam().getName());
        resultLabel.setText(match.getHomeTeamScore() + " : " + match.getAwayTeamScore());

        match.eventStream().forEach(gameEvent -> eventList.getChildren().add(new Label(gameEvent.toString())));
    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
