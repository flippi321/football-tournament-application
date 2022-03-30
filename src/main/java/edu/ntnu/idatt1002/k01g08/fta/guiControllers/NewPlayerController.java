package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    public void createPlayer(ActionEvent actionEvent) {
       // int index = Main.getTeamRegister().getNumberOfTeams() - 1;
        //Main.getTeamRegister().getTeams().get(index).addPlayer(
          //      new Player(playerNameInput.getText(), parseInt(playerNumberInput.getText())));

        //if(Main.getTeamRegister().getTeams().size() == 2 && Main.getTeamRegister().getTeams().g == 11)
    }
}
