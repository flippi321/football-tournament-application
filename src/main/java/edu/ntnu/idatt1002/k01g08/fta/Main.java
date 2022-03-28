package edu.ntnu.idatt1002.k01g08.fta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try{
            //SceneManager.setView("hello-view");

            FXMLLoader loader = SceneManager.getFXMLLoader("hello-view");
            SceneManager.setScene(new Scene(loader.load()));
            stage.setScene(SceneManager.getScene());

            stage.show();
        }catch (IOException i){
            i.printStackTrace();
        }
    }


    @Override
    public void stop() {
        System.exit(0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}