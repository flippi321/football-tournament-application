package edu.ntnu.idatt1002.k01g08.fta.controllers;

import edu.ntnu.idatt1002.k01g08.fta.objects.*;
import edu.ntnu.idatt1002.k01g08.fta.registers.TeamRegister;
import edu.ntnu.idatt1002.k01g08.fta.registers.TournamentRegister;
import edu.ntnu.idatt1002.k01g08.fta.util.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Admin {
    private static TeamRegister teamRegister;
    private static TournamentRegister tournamentRegister;
    private static File teamRegisterFile = new File("userdata/team_register.json");
    private static File tournamentPath = new File("userdata/tournaments");
    private static ArrayList<File> tournamentFiles = new ArrayList<>();

    //Temporary variables
    private static int numOfPlayersToCreate;
    private static String newestTeamCreated;
    private static int numOfTeamsToAdd;
    private static String tournamentToCreateName;
    private static Tournament activeTournament;
    private static Match activeMatch;

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
        return addPlayerToTeam(name, number, newestTeamCreated);
    }

    public static boolean addPlayerToTeam(String playerName, int number, String teamName) {
        Player player = new Player(playerName, number);
        return teamRegister.getTeam(teamName).addPlayer(player);
    }

    public static boolean addPlayerToExistingTeam(String playerName, int number, String teamName) throws IOException {
        boolean result = addPlayerToTeam(playerName, number, teamName);
        saveTeams();
        return result;
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

    public static void loadTournaments() {
        tournamentRegister = new TournamentRegister();
        if (!tournamentPath.exists()) {
            return;
        }
        loadTeams();
        if (tournamentPath.listFiles().length > 0) {
            File[] listOfFiles = tournamentPath.listFiles();
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    try {
                        tournamentRegister.addTournament(FileManager.loadTournament(file, teamRegister));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    return;
                }
            }
        }
    }

    public static void saveTournament(Tournament tournament) throws IOException {
        File file = new File(tournamentPath + "/" + tournament.getTournamentName().replaceAll(" ", "_") + ".json");
        FileManager.saveTournament(file, tournament);
    }

    public static List<String> getTeamNames() {
        loadTeams();
        ArrayList<String> list = new ArrayList<>();
        for (Team team : teamRegister) {
            list.add(team.getName());
        }
        return list;
    }

    public static List<String> getPlayerStrings(String teamName) {
        List<String> list = new ArrayList<>();
        for (Player player : teamRegister.getTeam(teamName)) {
            list.add(player.getNumber() + " " + player.getName());
        }
        return list;
    }

    public static void editTeamName(String teamName, String newTeamName) throws IOException {
        loadTeams();
        if (teamRegister.getTeams().containsKey(newTeamName)) {
            throw new IllegalArgumentException("Can't change name into another existing teams name");
        }
        Team newTeam = new Team(newTeamName);
        for (Player player : teamRegister.getTeam(teamName)) {
            newTeam.addPlayer(player);
        }
        teamRegister.addTeam(newTeam);
        teamRegister.removeTeam(teamRegister.getTeam(teamName));
        saveTeams();
    }

    public static void deleteTeam(String teamName) throws IOException {
        teamRegister.removeTeam(teamRegister.getTeam(teamName));
        saveTeams();
    }

    public static void editPlayer(String teamName, int oldPlayerNumber, int newPlayerNumber, String playerName) throws IOException {
        loadTeams();
        Team team = teamRegister.getTeam(teamName);
        if (oldPlayerNumber != newPlayerNumber && team.getPlayer(newPlayerNumber) != null) {
            throw new IllegalArgumentException("Player number not available");
        }
        Player oldPlayer = team.getPlayer(oldPlayerNumber);
        team.removePlayer(oldPlayerNumber);

        Player newPlayer = new Player(playerName, newPlayerNumber);
        newPlayer.increaseRedCards(oldPlayer.getRedCards());
        newPlayer.increaseYellowCards(oldPlayer.getYellowCards());
        newPlayer.increaseAssists(oldPlayer.getAssists());
        newPlayer.increaseGoals(oldPlayer.getGoals());
        team.addPlayer(newPlayer);
        saveTeams();
    }

    public static void deletePlayer(String teamName, int playerNumber) throws IOException {
        teamRegister.getTeam(teamName).removePlayer(playerNumber);
        saveTeams();
    }

    public static boolean numberOfTeamsInvalid(int num) {
        if (num < 2) {
            return true;
        } else {
            return (num & num - 1) != 0;
        }
    }

    public static void setTournamentToCreateName(String name) {
        tournamentToCreateName = name;
    }

    public static void setNumOfTeamsToAdd(int num) {
        numOfTeamsToAdd = num;
    }

    public static int getNumOfTeamsToAdd() {
        return numOfTeamsToAdd;
    }

    public static void createTournament(ArrayList<String> teams) throws IOException {
        Tournament tournament = new KnockOut(tournamentToCreateName, getTeams(teams));
        activeTournament = tournament;
        saveTournament(activeTournament);
    }

    public static ArrayList<Team> getTeams(ArrayList<String> teams) {
        loadTeams();
        ArrayList<Team> teamsList = new ArrayList<>();
        for (String name : teams) {
            teamsList.add(teamRegister.getTeam(name));
        }
        return teamsList;
    }

    public static ArrayList<String> getTournamentNames() {
        loadTournaments();
        ArrayList<String> names = new ArrayList<>();

        for (Tournament tournament : tournamentRegister.getTournamentList()) {
            names.add(tournament.getTournamentName());
        }

        return names;
    }

    public static Tournament getActiveTournament() {
        return activeTournament;
    }

    public static Match getNextMatch() {
        return activeTournament.getUpcomingMatches().get(0);
    }

    public static void selectActiveTournament(int n) {
        loadTournaments();
        activeTournament = tournamentRegister.getTournamentList().get(n);
    }

    public static void loadActiveMatch() {
        if (activeTournament.getCurrentMatch() != null) {
            activeMatch = activeTournament.getCurrentMatch();
            return;
        }
        activeTournament.startNextMatch();
        activeMatch = activeTournament.getCurrentMatch();
    }

    public static Match getActiveMatch() {
        return activeMatch;
    }
}
