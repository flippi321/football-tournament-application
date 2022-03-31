package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.objects.Match;
import edu.ntnu.idatt1002.k01g08.fta.objects.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TournamentOverviewController {
    @FXML
    private Label tournamentNameLabel;
    @FXML
    private Label nextMatchLabel;

    @FXML
    public void initialize() {
        Tournament tournament = Main.getTournamentRegister().getTournamentList().get(0);
        tournament.findUpcomingMatches();

        tournamentNameLabel.setText(tournament.getTournamentName());

        Match match = tournament.getUpcomingMatches().get(0);

        String newMatchString = match.getHomeTeam().getName() + " vs " + match.getAwayTeam().getName();
        nextMatchLabel.setText(newMatchString);
    }

    @FXML
    public void startMatch(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("matchViewController");
    }
}
