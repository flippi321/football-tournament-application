package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainController{

    @FXML
    public void settingsButtonClick(Event event) {
    }

    @FXML
    public void loadTournament(ActionEvent actionEvent) {
    }

    @FXML
    public void exitButtonClick(Event event) {
        System.exit(0);
    }

    @FXML
    public void teamManagement(ActionEvent actionEvent) {

    }

    @FXML
    public void reportButtonClick(Event event) throws IOException {
        SceneManager.setView("errorForm");
    }

    @FXML
    public void newTournament(ActionEvent actionEvent) {
    }
}