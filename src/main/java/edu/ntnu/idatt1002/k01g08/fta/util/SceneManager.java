package edu.ntnu.idatt1002.k01g08.fta.util;

import edu.ntnu.idatt1002.k01g08.fta.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class used to switch scene
 */
public class SceneManager {
    private static Scene scene;
    private static Stage stage;
    private static String lastScene = "";
    private static String currentScene = "";

    /**
     * Constructor
     */
    public SceneManager() {}

    /**
     * Method for setting the view
     * @param viewFxml is the name of the fxml file
     * @throws IOException if the view couldn't be loaded
     */
    public static void setView(String viewFxml) throws IOException {
        lastScene = currentScene;
        currentScene = viewFxml;
        FXMLLoader loader = getLoader(viewFxml);
        scene.setRoot(loader.load());
    }

    /**
     * Method for going to the last scene
     * @throws IOException if the view couldn't be loaded
     */
    public static void goToLastScene() throws IOException {
        setView(lastScene);
    }

    /**
     * Retrieves the FXML loader
     * @param fileName is the name of the FXML file
     * @return the FXMLLoader
     */
    public static FXMLLoader getLoader(String fileName) {
        String path = String.format("%s.fxml", fileName);
        System.out.println(path);

        return new FXMLLoader(Main.class.getResource(path));
    }

    /**
     * Sets the scenes
     * @param scene is the scene being set
     */
    public static void setScene(Scene scene) {
        SceneManager.scene = scene;
    }

    /**
     * Sets the current scene
     * @param sceneName is the name of the scene
     */
    public static void setCurrentScene(String sceneName) {
        lastScene = currentScene;
        currentScene = sceneName;
    }

    /**
     * Retrieves a scene
     * @return
     */
    public static Scene getScene() {
        return scene;
    }

    /**
     * Retrieves a stage
     * @return the stage
     */
    public static Stage getStage() {
        return stage;
    }
}
