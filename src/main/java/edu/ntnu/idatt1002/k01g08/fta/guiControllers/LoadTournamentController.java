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
 * Controller for the load tournament page
 *
 * @author jfben
 */
public class LoadTournamentController {
    @FXML
    private Label errorLabel;
    @FXML
    private Button loadTournamentButton;
    @FXML
    private ComboBox tournamentSelectionBox;

    @Deprecated
    public void initialize() {
        tournamentSelectionBox.getItems().addAll(
                "T1",
                "T2",
                "T3"
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
    public void loadTournament(ActionEvent actionEvent) {
        // TODO: 19.04.2022 Add functionality
    }

    @FXML
    public void tournamentSelected(ActionEvent actionEvent) {
        loadTournamentButton.setDisable(tournamentSelectionBox.getValue() == null);
    }
}