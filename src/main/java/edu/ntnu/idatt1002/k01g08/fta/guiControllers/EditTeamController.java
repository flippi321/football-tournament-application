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
 * Controller for the edit team page
 *
 * @author jfben
 */
public class EditTeamController {
    @FXML
    private Label errorLabel;
    @FXML
    private ComboBox teamSelectionBox;
    @FXML
    private TextField teamNameInput;
    @FXML
    private Button deleteTeamButton;

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
        if (teamNameInput.getText().isBlank()) {
            errorLabel.setText("Missing requirements");
            return;
        }

        errorLabel.setText("");
        // TODO: 19.04.2022 Change the name of the selected team
    }

    @FXML
    public void deleteTeam(ActionEvent actionEvent) {
        // TODO: 18.04.2022 Add functionality, maybe add a confirm box
    }

    @FXML
    public void teamSelected(ActionEvent actionEvent) {
        if (teamSelectionBox.getValue() != null) {
            // TODO: 18.04.2022 Import list of players in the selected team for the user to select
            teamNameInput.setDisable(false);
            deleteTeamButton.setDisable(false);
        } else {
            teamNameInput.setDisable(true);
            deleteTeamButton.setDisable(true);
        }
    }
}