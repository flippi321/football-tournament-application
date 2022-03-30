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
        for (int i = 0; i < 12; i++) {
            Main.addPlayer(new Player(playerNameInput.getText(), Integer.parseInt(playerNumberInput.getText()) + i));
        }

        playerIdLabel.setText("Player: " + (1 + Main.getPlayersMade().size()));
        playerNameInput.setText("");
        playerNumberInput.setText("");

       if (Main.getNumOfPlayers() <= Main.getPlayersMade().size()) {
           Main.getTournamentRegister().getTournamentList().get(0).getTeams()
                   .get(Main.getTournamentRegister().getTournamentList().get(0).getNumberOfTeams()-1)
                   .addPlayers(Main.getPlayersMade());
           SceneManager.setView("newTeam");
       }
    }
}
