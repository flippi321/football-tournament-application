package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.util.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

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
    @FXML
    private BorderPane root;
    @FXML
    private ImageView reportButton;
    @FXML
    private ImageView settingsButton;
    @FXML
    private ImageView backButton;
    @FXML
    private ImageView homeButton;

    private boolean delete = false;

    @Deprecated
    public void initialize() {
        teamSelectionBox.getItems().addAll(Admin.getTeamNames());
        root.getStylesheets().add(Main.class.getResource(Admin.getActiveStyle()).toExternalForm());
        Tooltip.install(reportButton, new Tooltip("Go to report page"));
        Tooltip.install(settingsButton, new Tooltip("Go to settings"));
        Tooltip.install(backButton, new Tooltip("Go back to last page"));
        Tooltip.install(homeButton, new Tooltip("Go to home page"));
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
        String newTeamName = teamNameInput.getText();
        if (newTeamName.isBlank()) {
            errorLabel.setText("Missing requirements");
            return;
        }

        errorLabel.setText("");
        try {
            Admin.editTeamName(teamSelectionBox.getValue().toString(), newTeamName);
        } catch (IOException e) {
            errorLabel.setText(e.getMessage());
        }
        SceneManager.setView("teamManagement");
    }

    @FXML
    public void deleteTeam(ActionEvent actionEvent) throws IOException {
        if (!delete) {
            delete = true;
            deleteTeamButton.setText("Confirm");
            return;
        }
        Admin.deleteTeam(teamSelectionBox.getValue().toString());
        SceneManager.setView("teamManagement");
    }

    @FXML
    public void teamSelected(ActionEvent actionEvent) {
        if (teamSelectionBox.getValue() != null) {
            teamNameInput.setDisable(false);
            deleteTeamButton.setDisable(false);
        } else {
            teamNameInput.setDisable(true);
            deleteTeamButton.setDisable(true);
        }
    }
}