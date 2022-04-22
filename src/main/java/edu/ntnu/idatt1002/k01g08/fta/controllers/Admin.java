package edu.ntnu.idatt1002.k01g08.fta.controllers;

import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import edu.ntnu.idatt1002.k01g08.fta.registers.TeamRegister;
import edu.ntnu.idatt1002.k01g08.fta.registers.TournamentRegister;
import edu.ntnu.idatt1002.k01g08.fta.util.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Admin {
    private static TeamRegister teamRegister;
    private static TournamentRegister tournamentRegister;
    private static File teamRegisterFile = new File("userdata/team_register.json");

    //Temporary variables
    private static int numOfPlayersToCreate;
    private static String newestTeamCreated;

    public static void addTeam(String teamName, int numOfPlayers) throws IllegalArgumentException {
        loadTeams();
        if (teamRegister.getTeams().containsKey(teamName)) {
            throw new IllegalArgumentException("team name already exists");
        }
        teamRegister.addTeam(new Team(teamName));
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
        FileManager.saveTeamRegister(teamRegister, teamRegisterFile);
    }

    public static void loadTeams() {
        try {
            teamRegister = FileManager.loadTeamRegister(teamRegisterFile);
        } catch (IOException e) {
            teamRegister = new TeamRegister();
        }
    }

    public static ArrayList<String> getTeamNames() {
        loadTeams();
        ArrayList<String> list = new ArrayList<>();
        for (Team team : teamRegister) {
            list.add(team.getName());
        }
        return list;
    }

    public static void editTeamName(String teamName, String newTeamName) {
        loadTeams();
        if (teamRegister.getTeams().containsKey(newTeamName)) {
            throw new IllegalArgumentException("Can't change name into another existing teams name");
        }
    }

    public static void deleteTeam(String teamName) throws IOException {
        teamRegister.removeTeam(teamRegister.getTeam(teamName));
        saveTeams();
    }
}
