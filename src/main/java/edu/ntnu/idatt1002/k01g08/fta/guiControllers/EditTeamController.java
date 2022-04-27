package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.util.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Controller for the edit team page
 *
 * @author johnfb, teodorbi
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

    /**
     * Initializes the view on load.
     */
    @Deprecated
    public void initialize() {
        teamSelectionBox.getItems().addAll(Admin.getTeamNames());
        root.getStylesheets().add(Main.class.getResource(Admin.getActiveStyle()).toExternalForm());
        Tooltip.install(reportButton, new Tooltip("Go to report page"));
        Tooltip.install(settingsButton, new Tooltip("Go to settings"));
        Tooltip.install(backButton, new Tooltip("Go back to last page"));
        Tooltip.install(homeButton, new Tooltip("Go to home page"));
    }

    /**
     * Goes to the settings view when user clicks on the settings button
     * @param event Click event
     * @throws IOException if an error occurs
     */
    @FXML
    public void settingsButtonClick(Event event) throws IOException {
        SceneManager.setView("settings");
    }

    /**
     * Goes to the report page view when the user clicks on the report button
     * @param event Click event
     * @throws IOException if an error occurs
     */
    @FXML
    public void reportButtonClick(Event event) throws IOException {
        SceneManager.setView("errorForm");
    }

    /**
     * Goes to the main page view when the user clicks on the home button
     * @param event Click event
     * @throws IOException if an error occurs
     */
    @FXML
    public void exitButtonClick(Event event) throws IOException {
        SceneManager.setView("main");
    }

    /**
     * Goes to the last page view when the user clicks on the back button
     * @param event Click event
     * @throws IOException if an error occurs
     */
    @FXML
    public void backButtonClick(Event event) throws IOException {
        SceneManager.goToLastScene();
    }

    /**
     * Goes to the team management page view when the user clicks on the discard changes button
     * @param actionEvent Click event
     * @throws IOException if an error occurs
     */
    @FXML
    public void discardChanges(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("teamManagement");
    }

    /**
     * Saves changes when the user clicks on the save button
     * @param actionEvent Click event
     * @throws IOException if an error occurs
     */
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

    /**
     * Deletes selected team when user clicks on delete button
     * @param actionEvent Click event
     * @throws IOException when an error occurs
     */
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

    /**
     * Enables input when a team is selected
     * @param actionEvent Click event
     */
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


    /**
     * Enables user to use enter key to go to the settings page
     * @param event KeyEvent
     * @throws IOException if an error occurs
     */
    @FXML
    public void settingsButtonEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            settingsButtonClick(event);
        }
    }

    /**
     * Enables user to use enter key to go to the report page
     * @param event KeyEvent
     * @throws IOException if an error occurs
     */
    @FXML
    public void reportButtonEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            reportButtonClick(event);
        }
    }

    /**
     * Enables user to use enter key to use the back button
     * @param event KeyEvent
     * @throws IOException if an error occurs
     */
    @FXML
    public void backButtonEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            backButtonClick(event);
        }
    }

    /**
     * Enables user to use enter key to go to the home page
     * @param event KeyEvent
     * @throws IOException if an error occurs
     */
    @FXML
    public void homeButtonEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            exitButtonClick(event);
        }
    }
}