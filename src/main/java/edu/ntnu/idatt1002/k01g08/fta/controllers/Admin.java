package edu.ntnu.idatt1002.k01g08.fta.controllers;

import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import edu.ntnu.idatt1002.k01g08.fta.registers.TeamRegister;
import edu.ntnu.idatt1002.k01g08.fta.registers.TournamentRegister;
import edu.ntnu.idatt1002.k01g08.fta.util.FileManager;

import java.io.File;
import java.io.IOException;

public class Admin {
    private static TeamRegister teamRegister;
    private static TournamentRegister tournamentRegister;
    private static File teamRegisterFile = new File("team_register.json");

    //Temporary variables
    private static int numOfPlayersToCreate;
    private static String newestTeamCreated;

    public static void addTeam(String teamName, int numOfPlayers) {
        loadTeams();
        if (teamRegister.getTeam(teamName) == null) {
            teamRegister.addTeam(new Team(teamName));
        } else {
            throw new IllegalArgumentException("team name already exists");
        }
        numOfPlayersToCreate = numOfPlayers;
        newestTeamCreated = teamName;
    }

    public static int getNumOfPlayersToCreate() {
        return numOfPlayersToCreate;
    }

    public static boolean addPlayerToNewestTeam(String name, int number) {
        Player player = new Player(name, number);
        return teamRegister.getTeam(newestTeamCreated).addPlayer(player);
    }

    public static void saveTeams() throws IOException {
        if (!teamRegisterFile.exists()) {
            teamRegisterFile.createNewFile();
        }
        FileManager.saveTeamRegister(teamRegister, teamRegisterFile);
    }

    public static void loadTeams() {
        try {
            teamRegister = FileManager.loadTeamRegister(teamRegisterFile);
        } catch (IOException e) {
            teamRegister = new TeamRegister();
        }
    }
}
