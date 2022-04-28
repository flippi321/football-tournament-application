package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.util.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Controller for the new tournament page
 *
 * @author johnfb, teodorbi
 */
public class NewTournamentController {
    @FXML
    private BorderPane root;
    @FXML
    private TextField locationInput;
    @FXML
    private TextField tournamentNameInput;
    @FXML
    private TextField numberOfTeamsInput;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField dateInput;
    @FXML
    private TextField winningPrizeInput;
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
     * Lets the user create a new tournament when all required fields are filled. Goes to the new tournament team page
     * when clicking the button
     * @param actionEvent Click Event
     * @throws IOException if an error occurs
     */
    @FXML
    public void newTournament(ActionEvent actionEvent) throws IOException {
        if (tournamentNameInput.getText().isBlank() || numberOfTeamsInput.getText().isBlank()) {
            errorLabel.setText("Missing requirements");
            return;
        }

        int numberOfTeams;
        try {
            numberOfTeams = Integer.parseInt(numberOfTeamsInput.getText());
        } catch (IllegalArgumentException e) {
            errorLabel.setText("You must enter a number in number of teams");
            return;
        }

        if (Admin.numberOfTeamsInvalid(numberOfTeams)) {
            errorLabel.setText("A knockout-tournament must contain 2-4-8-16-32 . . . teams");
            return;
        }

        int prize;
        if (!winningPrizeInput.getText().isBlank()) {
            try {
                prize = Integer.parseInt(winningPrizeInput.getText());
            } catch (IllegalArgumentException e) {
                errorLabel.setText("You must enter a valid number in winning prize");
                return;
            }
        }

        if (!dateInput.getText().isBlank()) {
            String[] inputText = dateInput.getText().split("/");
            if (inputText.length != 3) {
                errorLabel.setText("Date format must be DD/MM/YYYY");
                return;
            }

            for (String text : inputText) {
                try {
                    int i = Integer.parseInt(text);
                } catch (IllegalArgumentException e) {
                    errorLabel.setText("Date format must be DD/MM/YYYY");
                    return;
                }
            }

            if (inputText[0].length() != 2 || inputText[1].length() != 2 || inputText[2].length() != 4) {
                errorLabel.setText("Date format must be DD/MM/YYYY");
                return;
            }
        }

        Admin.setTournamentToCreateName(tournamentNameInput.getText());
        Admin.setNumOfTeamsToAdd(numberOfTeams);
        SceneManager.setView("newTournamentTeamSelection");
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