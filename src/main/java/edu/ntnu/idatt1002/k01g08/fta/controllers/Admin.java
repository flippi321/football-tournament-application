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

/**
 * Admin class. Used to link GUI controllers with the other class
 * @author johnfb, teodorbi
 * @version 04-27-22
 */

public class Admin {
    private static TeamRegister teamRegister;
    private static TournamentRegister tournamentRegister;
    private static File teamRegisterFile = new File("userdata/team_register.json");
    private static File tournamentPath = new File("userdata/tournaments");
    private static ArrayList<File> tournamentFiles = new ArrayList<>();
    private static String activeStyle;

    //Temporary variables
    private static int numOfPlayersToCreate;
    private static String newestTeamCreated;
    private static int numOfTeamsToAdd;
    private static String tournamentToCreateName;
    private static Tournament activeTournament;
    private static Match activeMatch;
    private static Match currentReportedMatch;

    /**
     * Method for adding a team
     * @param teamName is the team name as a String
     * @param numOfPlayers is the amount of players in the team
     * @throws IllegalArgumentException if the team is already existing
     */
    public static void addTeam(String teamName, int numOfPlayers) throws IllegalArgumentException {
        loadTeams();
        if (teamRegister.getTeams().containsKey(teamName)) {
            throw new IllegalArgumentException("team name already exists");
        }
        teamRegister.addTeam(new Team(teamName));
        numOfPlayersToCreate = numOfPlayers;
        newestTeamCreated = teamName;
    }

    /**
     * Method for returning the amount of players to create
     * @return the amount as an int
     */
    public static int getNumOfPlayersToCreate() {
        return numOfPlayersToCreate;
    }

    /**
     * Method for adding a player to the newest team
     * @param name is the name of the player as a String
     * @param number is players shirt number as an int
     * @return true or false
     */
    public static boolean addPlayerToNewestTeam(String name, int number) {
        return addPlayerToTeam(name, number, newestTeamCreated);
    }

    /**
     * Method for adding a player to a team
     * @param playerName is the players name as a String
     * @param number is the players shirt number as an int
     * @param teamName is the teams name as a String
     * @return true or false
     */
    public static boolean addPlayerToTeam(String playerName, int number, String teamName) {
        Player player = new Player(playerName, number);
        return teamRegister.getTeam(teamName).addPlayer(player);
    }

    /**
     * Method for adding a player to an existing team
     * @param playerName is the players name as a String
     * @param number is the players shirt number as an int
     * @param teamName is the teams name as a String
     * @return true or false
     * @throws IOException if there is an error
     */
    public static boolean addPlayerToExistingTeam(String playerName, int number, String teamName) throws IOException {
        boolean result = addPlayerToTeam(playerName, number, teamName);
        saveTeams();
        return result;
    }

    /**
     * Method for saving a team to the register
     * @throws IOException if there is an error
     */
    public static void saveTeams() throws IOException {
        FileManager.saveTeamRegister(teamRegister, teamRegisterFile);
    }

    /**
     * Method for loading teams from a file
     */
    public static void loadTeams() {
        try {
            teamRegister = FileManager.loadTeamRegister(teamRegisterFile);
        } catch (IOException e) {
            teamRegister = new TeamRegister();
        }
    }

    /**
     * Method for loading tournaments from a file
     */
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
                    } catch (Exception e) {

                    }
                } else {
                    return;
                }
            }
        }
    }

    /**
     * Method for saving a tournament
     * @param tournament is the tournament being saved
     * @throws IOException if there was an error when saving the tournament
     */
    public static void saveTournament(Tournament tournament) throws IOException {
        File file = new File(tournamentPath + "/" + tournament.getTournamentName().replaceAll(" ", "_") + ".json");
        FileManager.saveTournament(file, tournament);
    }

    /**
     * Method for retrieving the names of every team in the register
     * @return the team names as an ArrayList
     */
    public static List<String> getTeamNames() {
        loadTeams();
        ArrayList<String> list = new ArrayList<>();
        for (Team team : teamRegister) {
            list.add(team.getName());
        }
        return list;
    }

    /**
     * Method for retrieving every player in a team
     * @param teamName is the name of the team as a String
     * @return the players in the team as an ArrayList
     */
    public static List<String> getPlayerStrings(String teamName) {
        List<String> list = new ArrayList<>();
        for (Player player : teamRegister.getTeam(teamName)) {
            list.add(player.getNumber() + " " + player.getName());
        }
        return list;
    }

    /**
     * Method for editing a teams name
     * @param teamName is the current name of the team as a String
     * @param newTeamName is the teams new name as a String
     * @throws IOException if there was an error
     * @throws IllegalArgumentException if there already is a team with the same name
     */
    public static void editTeamName(String teamName, String newTeamName) throws IOException, IllegalArgumentException {
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

    /**
     * Method for deleting a team
     * @param teamName is the name of the team being deleted as a String
     * @throws IOException if there was an error when deleting the team
     */
    public static void deleteTeam(String teamName) throws IOException {
        teamRegister.removeTeam(teamRegister.getTeam(teamName));
        saveTeams();
    }

    /**
     * Method for editing a player
     * @param teamName is the name of the team the player plays for
     * @param oldPlayerNumber is the old shirt number of the player as an int
     * @param newPlayerNumber is the new shirt number of the player as an int
     * @param playerName is the players name as a String
     * @throws IOException if there was an error when editing the player
     */
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

    /**
     * Method for deleting a player
     * @param teamName is the name of the team the player plays for
     * @param playerNumber is the players shirt number as an int
     * @throws IOException if there was an error when deleting the player
     */
    public static void deletePlayer(String teamName, int playerNumber) throws IOException {
        teamRegister.getTeam(teamName).removePlayer(playerNumber);
        saveTeams();
    }

    /**
     * Method for determine whether the number of teams is invalid
     * @param num is the amount of teams
     * @return true or false
     */
    public static boolean numberOfTeamsInvalid(int num) {
        if (num < 2) {
            return true;
        } else {
            return (num & num - 1) != 0;
        }
    }

    /**
     * Temporary storage of the tournament name when creating a tournament
     * @param name is the temporary name of the tournament
     */
    public static void setTournamentToCreateName(String name) {
        tournamentToCreateName = name;
    }

    /**
     * Method for setting the amount of teams to add
     * @param num is the amount as an int
     */
    public static void setNumOfTeamsToAdd(int num) {
        numOfTeamsToAdd = num;
    }

    /**
     * Method for retrieving the amount of teams to add to a tournamnt
     * @return the amount as an int
     */
    public static int getNumOfTeamsToAdd() {
        return numOfTeamsToAdd;
    }

    /**
     * Method for creating a tournament
     * @param teams is the teams in the tournament
     * @throws IOException if there was an error when creating the tournament
     */
    public static void createTournament(ArrayList<String> teams) throws IOException {
        activeTournament = new KnockOut(tournamentToCreateName, getTeams(teams));
        saveTournament(activeTournament);
    }

    /**
     * Method for retrieving the teams when creating a tournament
     * @param teams is the list of the teams
     * @return the list of teams
     */
    public static ArrayList<Team> getTeams(ArrayList<String> teams) {
        loadTeams();
        ArrayList<Team> teamsList = new ArrayList<>();
        for (String name : teams) {
            teamsList.add(teamRegister.getTeam(name));
        }
        return teamsList;
    }

    /**
     * Method for retrieving the teams names in a tournament
     * @return the names of teams in a tournament as an ArrayList
     */
    public static ArrayList<String> getTournamentNames() {
        loadTournaments();
        ArrayList<String> names = new ArrayList<>();

        for (Tournament tournament : tournamentRegister.getTournamentList()) {
            names.add(tournament.getTournamentName());
        }

        return names;
    }

    /**
     * Method for retrieving the active tournament
     * @return the tournament
     */
    public static Tournament getActiveTournament() {
        return activeTournament;
    }

    /**
     * Method for retrieving the next scheduled match
     * @return the next match
     */
    public static Match getNextMatch() {
        activeTournament.findUpcomingMatches();
        return activeTournament.getUpcomingMatches().get(0);
    }

    /**
     * Method for setting the active tournament
     * @param n is the index of the tournament in the register as an int
     */
    public static void selectActiveTournament(int n) {
        loadTournaments();
        activeTournament = tournamentRegister.getTournamentList().get(n);
    }

    /**
     * Method for loading the active match
     */
    public static void loadActiveMatch() {
        if (activeTournament.getCurrentMatch() != null) {
            activeMatch = activeTournament.getCurrentMatch();
            return;
        }
        activeTournament.startNextMatch();
        activeMatch = activeTournament.getCurrentMatch();
    }

    /**
     * Method for setting the current reported match
     * @param n is the index of the match in the register
     */
    public static void setCurrentReportedMatch(int n) {
        if (n == -1) {
            currentReportedMatch = null;
        } else {
            currentReportedMatch = activeTournament.getMatches().get(n);
        }
    }

    /**
     * Method for retrieving the current reported match
     * @return the current reported match
     */
    public static Match getCurrentReportedMatch() {
        if (currentReportedMatch == null) { //Return the newest
            return activeTournament.getMatches().get(activeTournament.getMatches().size()-1);
        }
        return currentReportedMatch;
    }

    /**
     * Method for retrieving the current active match
     * @return the active match
     */
    public static Match getActiveMatch() {
        return activeMatch;
    }

    /**
     * Method for retrieving the active style
     * @return the active style as a String
     */
    public static String getActiveStyle() {
        if (activeStyle == null) {
            return "style/main.css";
        } else {
            return "style/" + activeStyle + ".css";
        }
    }

    /**
     * Method for setting the active style
     * @param style is the new style
     */
    public static void setActiveStyle(String style) {
        activeStyle = style;
    }
}
