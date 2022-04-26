package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller for the new player page
 *
 * @author jfben
 */
public class NewPlayerController {
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
    public void initialize() {
        teamSelectionBox.getItems().addAll(
                Admin.getTeamNames()
        );
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
    public void saveChanges(ActionEvent actionEvent) throws IOException {
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
        String playerName = firstName + " " + lastName;

        errorLabel.setText("");
        // TODO: 18.04.2022 Create player and add it to the selected team

        try{
            Admin.addPlayerToExistingTeam(playerName, playerNumber, teamSelectionBox.getValue().toString());
            SceneManager.setView("teamManagement");
        } catch (IllegalArgumentException e){
            errorLabel.setText(e.getMessage());
        }
    }
}