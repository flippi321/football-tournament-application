package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class NewTeamController {
    private final int currentTeams;

    public NewTeamController() {
        currentTeams = Main.getTournamentRegister().getTournamentList().get(0).getNumberOfTeams();
    }

    @FXML
    public void initialize() throws IOException {
        teamIdLabel.setText("Team: " + (Main.getTeamRegister().getTeams().size() + 1));

        if (Main.getTeamRegister().getTeams().size() == currentTeams) { //Then all teams have been made
            Main.getTournamentRegister().getTournamentList().get(0).findUpcomingMatches();
            SceneManager.setView("tournamentOverview");
        }
    }

    @FXML
    private TextField noOfPlayersInput;
    @FXML
    private TextField teamNameInput;
    @FXML
    private Label teamIdLabel;
    @FXML
    private Label errorLabel;

    @FXML
    public void createTeam(ActionEvent actionEvent) throws IOException {
        Main.setNumOfPlayers(parseInt(noOfPlayersInput.getText()));

        Team team = new Team(teamNameInput.getText());
        Main.getTeamRegister().addTeam(team);
        Main.getTournamentRegister().getTournamentList().get(0).addTeam(team);
        SceneManager.setView("newPlayer");
    }
}
