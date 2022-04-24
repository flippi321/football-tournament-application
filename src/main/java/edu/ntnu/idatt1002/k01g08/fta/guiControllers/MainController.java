package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import edu.ntnu.idatt1002.k01g08.fta.controllers.Admin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * Controller for the main page
 *
 * @author jfben
 */
public class MainController{

    @FXML
    private ImageView reportButton;

    @Deprecated
    public void initialize() {
        reportButton.setStyle("-fx-background-color: WHITE");
    }

    @FXML
    public void settingsButtonClick(Event event) throws IOException {
        SceneManager.setView("settings");
    }

    @FXML
    public void loadTournament(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("loadTournament");
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