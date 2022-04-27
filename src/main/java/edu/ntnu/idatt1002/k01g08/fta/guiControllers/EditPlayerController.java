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

    @FXML
    public void teamSelected(ActionEvent actionEvent) {
        playerSelectionBox.getItems().removeAll(playerSelectionBox.getItems());
        if (teamSelectionBox.getValue() != null) {
            playerSelectionBox.getItems().addAll(
                    Admin.getPlayerStrings(teamSelectionBox.getValue().toString())
            );
        }
    }

    @FXML
    public void settingsButtonEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            settingsButtonClick(event);
        }
    }

    @FXML
    public void reportButtonEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            reportButtonClick(event);
        }
    }

    @FXML
    public void backButtonEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            backButtonClick(event);
        }
    }

    @FXML
    public void homeButtonEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            exitButtonClick(event);
        }
    }
}