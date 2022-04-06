package edu.ntnu.idatt1002.k01g08.fta;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static Scene scene;
    private static Stage stage;
    private static String lastScene = "";
    private static String currentScene = "";

    public SceneManager() {}

    public static void setView(String viewFxml) throws IOException {
        lastScene = currentScene;
        currentScene = viewFxml;
        FXMLLoader loader = getLoader(viewFxml);
        scene.setRoot(loader.load());
    }

    public static void goToLastScene() throws IOException {
        setView(lastScene);
    }

    public static FXMLLoader getLoader(String fileName) {
        String path = String.format("%s.fxml", fileName); //TODO: update to use root path
        System.out.println(path);

        return new FXMLLoader(Main.class.getResource(path));
    }

    public static void setScene(Scene scene) {
        SceneManager.scene = scene;
    }

    public static void setCurrentScene(String sceneName) {
        lastScene = currentScene;
        currentScene = sceneName;
    }

    public static Scene getScene() {
        return scene;
    }

    public static Stage getStage() {
        return stage;
    }
}
