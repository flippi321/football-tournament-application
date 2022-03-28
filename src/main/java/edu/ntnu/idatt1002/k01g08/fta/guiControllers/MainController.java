package edu.ntnu.idatt1002.k01g08.fta.guiControllers;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import edu.ntnu.idatt1002.k01g08.fta.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class MainController{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button createNewTournament;

    @FXML
    public void createNewTournamentClick(ActionEvent actionEvent) throws IOException {
        SceneManager.setView("newTournament");
    }
}