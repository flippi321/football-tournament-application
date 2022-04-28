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
 * Controller for the edit player page
 * @author johnfb, teodorbi
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

    private boolean delete;
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

    /**
     * Initializes the view on load.
     */
    @Deprecated
    public void initialize() {
        teamSelectionBox.getItems().addAll(
                Admin.getTeamNames()
        );
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
    public void saveChanges(ActionEvent actionEvent) throws IOException{
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
        String playerName = firstName + ' ' + lastName;
        String teamName = teamSelectionBox.getValue().toString();
        int oldPlayerNumber = Integer.parseInt(playerSelectionBox.getValue().toString().split(" ")[0]);

        errorLabel.setText("");
        try {
            Admin.editPlayer(teamName, oldPlayerNumber, playerNumber, playerName);
            SceneManager.setView("teamManagement");
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    /**
     * Enables input when a player is selected
     * @param actionEvent event
     */
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

    /**
     * Deletes selected player when the user clicks on the delete player button
     * @param actionEvent Click event
     * @throws IOException if an error occurs
     */
    @FXML
    public void deletePlayer(ActionEvent actionEvent) throws IOException {
        if (!delete) {
            delete = true;
            deletePlayerButton.setText("Confirm");
            return;
        }
        Admin.deletePlayer(teamSelectionBox.getValue().toString(),
                Integer.parseInt(playerSelectionBox.getValue().toString().split(" ")[0]));
        SceneManager.setView("teamManagement");
    }

    /**
     * Enables to select a player when a team is selected
     * @param actionEvent event
     */
    @FXML
    public void teamSelected(ActionEvent actionEvent) {
        playerSelectionBox.getItems().removeAll(playerSelectionBox.getItems());
        if (teamSelectionBox.getValue() != null) {
            playerSelectionBox.getItems().addAll(
                    Admin.getPlayerStrings(teamSelectionBox.getValue().toString())
            );
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