package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.objects.GameEvent;
import edu.ntnu.idatt1002.k01g08.fta.objects.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

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

        ArrayList<GameEvent> events = new ArrayList<>();
        match.eventStream().forEach(events::add);
        for (GameEvent event : events) {
            Label label = new Label(event.getEvent());
            label.setPrefWidth(Region.USE_COMPUTED_SIZE);
            eventList.getChildren().add(label);
        }
    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
