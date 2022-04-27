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
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Controller for the new team-player page
 *
 * @author jfben
 */
public class NewTeamPlayerController {
    @FXML
    private BorderPane root;
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
    @FXML
    private ImageView reportButton;
    @FXML
    private ImageView settingsButton;
    @FXML
    private ImageView backButton;
    @FXML
    private ImageView homeButton;

    private int numOfPlayersToCreate;
    private int playersCreated = 0;

    @Deprecated
    public void initialize() {
        numOfPlayersToCreate = Admin.getNumOfPlayersToCreate();
        titleText.setText("Player " + (playersCreated+1) + " of " + numOfPlayersToCreate);
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
    public void cancel(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("teamManagement");
    }

    @FXML
    public void next(ActionEvent actionEvent) throws IOException {
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

        String name = firstNameInput.getText() + " " + lastNameInput.getText();

        if (!Admin.addPlayerToNewestTeam(name, playerNumber)) {
            errorLabel.setText("The team already has a player with that number");
            return;
        }
        errorLabel.setText("");

        playersCreated++;
        if (playersCreated >= numOfPlayersToCreate) {
            try {
                Admin.saveTeams();
            } catch (IOException e) {
                errorLabel.setText(e.getMessage());
                System.out.println(e.getMessage());
            }
            SceneManager.setView("teamManagement");
        }

        titleText.setText("Player " + (playersCreated+1) + " of " + numOfPlayersToCreate);
        playerNumberInput.setText("");
        firstNameInput.setText("");
        lastNameInput.setText("");

        // TODO: 18.04.2022 Create player and add it to the selected team
        // TODO: 19.04.2022 Go to next player if next player should be created
    }
}