package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller for the new team-player page
 *
 * @author jfben
 */
public class NewTeamPlayerController {
    @FXML
    private Label errorLabel;
    @FXML
    private TextField lastNameInput;
    @FXML
    private TextField playerNumberInput;
    @FXML
    private TextField firstNameInput;
    @FXML
    private Label titleText;

    private int numOfPlayersToCreate;
    private int playersCreated = 0;

    @Deprecated
    public void initialize() {
        numOfPlayersToCreate = Admin.getNumOfPlayersToCreate();
        titleText.setText("Player " + (playersCreated+1) + " of " + numOfPlayersToCreate);
        // TODO: 19.04.2022 Show real progress
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
    public void cancel(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("teamManagement");
    }

    @FXML
    public void next(ActionEvent actionEvent) throws IOException {
        if (playerNumberInput.getText().isBlank() || firstNameInput.getText().isBlank() || lastNameInput.getText().isBlank()) {
            errorLabel.setText("Missing requirements");
            return;
        }

        int playerNumber;
        try {
            playerNumber = Integer.parseInt(playerNumberInput.getText());
        } catch (IllegalArgumentException e) {
            errorLabel.setText("Must enter a valid number");
            return;
        }

        String name = firstNameInput.getText() + " " + lastNameInput.getText();

        if (!Admin.addPlayerToNewestTeam(name, playerNumber)) {
            errorLabel.setText("The team already has a player with that number");
            return;
        }
        errorLabel.setText("");

        playersCreated++;
        if (playersCreated >= numOfPlayersToCreate) {
            try {
                Admin.saveTeams();
            } catch (IOException e) {
                errorLabel.setText(e.getMessage());
                System.out.println(e.getMessage());
            }
            SceneManager.setView("teamManagement");
        }

        titleText.setText("Player " + (playersCreated+1) + " of " + numOfPlayersToCreate);
        playerNumberInput.setText("");
        firstNameInput.setText("");
        lastNameInput.setText("");

        // TODO: 18.04.2022 Create player and add it to the selected team
        // TODO: 19.04.2022 Go to next player if next player should be created
    }
}