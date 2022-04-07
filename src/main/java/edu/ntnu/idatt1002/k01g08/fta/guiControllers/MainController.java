package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Controller for the main page
 *
 * @author jfben
 */
public class MainController{

    @FXML
    public void settingsButtonClick(Event event) throws IOException {
        SceneManager.setView("settings");
    }

    @FXML
    public void loadTournament(ActionEvent actionEvent) {
    }

    @FXML
    public void teamManagement(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("teamManagement");
    }

    @FXML
    public void reportButtonClick(Event event) throws IOException {
        SceneManager.setView("errorForm");
    }

    @FXML
    public void newTournament(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("newTournament");
    }
}