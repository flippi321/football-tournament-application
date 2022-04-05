package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class NewPlayerController {
    @FXML
    private TextField playerNameInput;
    @FXML
    private Label playerIdLabel;
    @FXML
    private TextField playerNumberInput;
    @FXML
    private Label errorLabel;

    @FXML
    public void initialize() {
        playerIdLabel.setText("Player: " + (1 + Main.getPlayersMade().size()));
    }

    @FXML
    public void createPlayer(ActionEvent actionEvent) throws IOException {
        if (playerNameInput.getText().isBlank() || playerNumberInput.getText().isBlank()) {
            errorLabel.setText("You must fill both values.");
            return;
        }

        try {
            int num = Integer.parseInt(playerNumberInput.getText());
        } catch (IllegalArgumentException e) {
            errorLabel.setText("You must enter a number.");
            return;
        }

        for (int i = 1; i < 12; i++) {
            Main.addPlayer(new Player(playerNameInput.getText(), i));
        }

        playerIdLabel.setText("Player: " + (1 + Main.getPlayersMade().size()));
        playerNameInput.setText("");
        playerNumberInput.setText("");

       if (Main.getNumOfPlayers() <= Main.getPlayersMade().size()) {
           Main.getTournamentRegister().getTournamentList().get(0).getTeams()
                   .get(Main.getTournamentRegister().getTournamentList().get(0).getNumberOfTeams()-1)
                   .addPlayers(Main.getPlayersMade());
           if (Main.getTournamentRegister().getTournamentList().get(0).getNumberOfTeams()
                   == Main.getNumOfTeams()) {
               Main.getTournamentRegister().getTournamentList().get(0).findUpcomingMatches();
               SceneManager.setView("tournamentOverview");
           }
           else {
               SceneManager.setView("newTeam");
           }
       }
    }
}
