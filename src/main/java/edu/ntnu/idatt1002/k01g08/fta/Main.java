package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.Match;
import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import edu.ntnu.idatt1002.k01g08.fta.registers.TeamRegister;
import edu.ntnu.idatt1002.k01g08.fta.registers.TournamentRegister;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    private static int numOfPlayers = 0;
    private static TeamRegister teamRegister = new TeamRegister();
    private static TournamentRegister tournamentRegister = new TournamentRegister();
    private static int numOfTeams;
    private static ArrayList<Player> playersUnderCreation;

    @Override
    public void start(Stage stage) {
        try{
            //SceneManager.setView("hello-view");

            FXMLLoader loader = SceneManager.getLoader("hello-view");
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

    public static TeamRegister getTeamRegister() {
        return teamRegister;
    }

    public static TournamentRegister getTournamentRegister() {
        return tournamentRegister;
    }

    public static int getNumOfTeams() {
        return numOfTeams;
    }

    public static void setNumOfTeams(int numOfTeams) {
        Main.numOfTeams = numOfTeams;
    }

    public static void setNumOfPlayers(int numOfPlayers) {
        Main.numOfPlayers = numOfPlayers;
    }

    public static int getNumOfPlayers() {
        return Main.numOfPlayers;
    }

    public static void initializePlayerCreation() {playersUnderCreation = new ArrayList<>();}

    public static void addPlayer(Player player) {playersUnderCreation.add(player);}

    public static ArrayList<Player> getPlayersMade() {return playersUnderCreation;}
}