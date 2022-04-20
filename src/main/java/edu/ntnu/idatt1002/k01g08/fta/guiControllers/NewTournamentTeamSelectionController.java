package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller for the new tournament page
 *
 * @author jfben
 */
public class NewTournamentTeamSelectionController {

    @FXML
    private Label errorLabel;
    @FXML
    private ComboBox teamsToSelectBox;
    @FXML
    private VBox teamsSelectedList;
    @FXML
    private Label registeredTeamsLabel;

    @FXML
    public void initialize() {
        registeredTeamsLabel.setText("Registered Teams (0/4)");
        // TODO: 20.04.2022 Get actual amount of teams into the title
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
    public void confirm(ActionEvent actionEvent) {
        // TODO: 20.04.2022 Create tournament with the selected teams
    }

    @FXML
    public void cancel(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("main");
    }

    @FXML
    public void selectedNewTeam(ActionEvent actionEvent) {
        // TODO: 20.04.2022 Put the team into the vBox and remove it from the ComboBox
    }
}