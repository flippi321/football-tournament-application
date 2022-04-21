package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller for the new player page
 *
 * @author jfben
 */
public class EditPlayerController {
    @FXML
    private Label errorLabel;
    @FXML
    private ComboBox teamSelectionBox;
    @FXML
    private TextField lastNameInput;
    @FXML
    private TextField playerNumberInput;
    @FXML
    private TextField firstNameInput;
    @FXML
    private ComboBox playerSelectionBox;
    @FXML
    private Button deletePlayerButton;

    @Deprecated
    public void initialize() {
        teamSelectionBox.getItems().addAll(
                "TEAM1",
                "TEAM2",
                "TEAM3"
        );
        // TODO: 18.04.2022 Add real teams in list
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
    public void discardChanges(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("teamManagement");
    }

    @FXML
    public void saveChanges(ActionEvent actionEvent) {
        if (playerSelectionBox.getValue() == null || teamSelectionBox.getValue() == null) {
            errorLabel.setText("Must select a player to edit");
            return;
        }

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

        String firstName = firstNameInput.getText();
        String lastName = lastNameInput.getText();

        errorLabel.setText("");
        // TODO: 18.04.2022 Edit the selected player
    }

    @FXML
    public void playerSelected(ActionEvent actionEvent) {
        if (playerSelectionBox.getValue() != null) {
            firstNameInput.setDisable(false);
            lastNameInput.setDisable(false);
            playerNumberInput.setDisable(false);
            deletePlayerButton.setDisable(false);
        } else {
            firstNameInput.setDisable(true);
            lastNameInput.setDisable(true);
            playerNumberInput.setDisable(true);
            deletePlayerButton.setDisable(true);
        }
    }

    @FXML
    public void deletePlayer(ActionEvent actionEvent) {
        // TODO: 18.04.2022 Add functionality, maybe add a confirm box
    }

    @FXML
    public void teamSelected(ActionEvent actionEvent) {
        playerSelectionBox.getItems().removeAll(playerSelectionBox.getItems());
        if (teamSelectionBox.getValue() != null) {
            // TODO: 18.04.2022 Import list of players in the selected team for the user to select
            playerSelectionBox.getItems().addAll(
                    "PLAYER1",
                    "PLAYER2"
            );
        }
    }
}