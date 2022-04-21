package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
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

    @Deprecated
    public void initialize() {
        titleText.setText("Player 1 of 11");
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
    public void next(ActionEvent actionEvent) {
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
        // TODO: 18.04.2022 Create player and add it to the selected team
        // TODO: 19.04.2022 Go to next player if next player should be created
    }
}