package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.util.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller for the new tournament team selection page. This is where the user selects which teams are in the
 * tournament
 *
 * @author johnfn, teodorbi
 */
public class NewTournamentTeamSelectionController {
    @FXML
    private BorderPane root;
    @FXML
    private Label errorLabel;
    @FXML
    private ComboBox teamsToSelectBox;
    @FXML
    private VBox teamsSelectedList;
    @FXML
    private Label registeredTeamsLabel;
    @FXML
    private ImageView reportButton;
    @FXML
    private ImageView settingsButton;
    @FXML
    private ImageView backButton;
    @FXML
    private ImageView homeButton;

    ArrayList<String> teamsAdded = new ArrayList<>();

    /**
     * Initializes the view on load.
     */
    @Deprecated
    public void initialize() {
        registeredTeamsLabel.setText("Registered Teams (0/" + Admin.getNumOfTeamsToAdd() + ")");
        teamsToSelectBox.getItems().addAll(Admin.getTeamNames());
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
     * Lets the user confirm their selection when they have added the correct amount of teams. Goes to the tournament
     * overview page
     * @param actionEvent Click Event
     * @throws IOException if an error occurs
     */
    @FXML
    public void confirm(ActionEvent actionEvent) throws IOException {
        if (teamsAdded.size() == Admin.getNumOfTeamsToAdd()) {
            try {
                Admin.createTournament(teamsAdded);
                SceneManager.setView("tournamentOverview");
            } catch (IOException e) {
                errorLabel.setText("Was not able to create file, make sure to not use special characters such as '. or ?'");
            }
        } else {
            errorLabel.setText("You must add " + Admin.getNumOfTeamsToAdd() + " teams");
        }
    }

    /**
     * Lets the user cancel the selection and returns to home page when clicking the cancel button
     * @param actionEvent Click Event
     * @throws IOException if an error occurs
     */
    @FXML
    public void cancel(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("main");
    }

    /**
     * Lets the user select teams from the drop-down menu
     * @param event event
     */
    @FXML
    public void clickedOnSelection(Event event) {
        if (teamsToSelectBox.getValue() == null) {
            errorLabel.setText("You must select a team to add");
            return;
        }
        if (teamsAdded.size() == Admin.getNumOfTeamsToAdd()) {
            errorLabel.setText("Can't add more teams");
            return;
        }
        errorLabel.setText("");
        teamsAdded.add(teamsToSelectBox.getValue().toString());
        Label label = new Label(teamsToSelectBox.getValue().toString());
        teamsSelectedList.getChildren().add(label);
        teamsToSelectBox.getItems().remove(teamsToSelectBox.getValue());
        teamsToSelectBox.setValue(null);
        registeredTeamsLabel.setText("Registered Teams (" + teamsAdded.size() + "/" + Admin.getNumOfTeamsToAdd() + ")");
    }

    /**
     * Lets the user reset their selection when clicking the reset button
     * @param actionEvent Click Event
     * @throws IOException if an error occurs
     */
    @FXML
    public void reset(ActionEvent actionEvent) throws IOException {
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