package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

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

    ArrayList<String> teamsAdded = new ArrayList<>();

    @Deprecated
    public void initialize() {
        registeredTeamsLabel.setText("Registered Teams (0/" + Admin.getNumOfTeamsToAdd() + ")");
        teamsToSelectBox.getItems().addAll(Admin.getTeamNames());
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
    public void confirm(ActionEvent actionEvent) throws IOException {
        if (teamsAdded.size() == Admin.getNumOfTeamsToAdd()) {
            Admin.createTournament(teamsAdded);
            SceneManager.setView("main");
        } else {
            errorLabel.setText("You must add " + Admin.getNumOfTeamsToAdd() + " teams");
        }
    }

    @FXML
    public void cancel(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("main");
    }

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

    @FXML
    public void reset(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("newTournamentTeamSelection");
    }
}