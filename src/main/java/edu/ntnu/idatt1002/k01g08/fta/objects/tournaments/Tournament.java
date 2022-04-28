package edu.ntnu.idatt1002.k01g08.fta.objects.tournaments;
import edu.ntnu.idatt1002.k01g08.fta.objects.team.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class representing a Tournament
 * The Tournament's main function is to add, remove and modify matches which take place during the tournament.
 *
 * It contains information about all teams and matches in the tournament in ArrayLists, as well as it's name and
 * start date as strings, the winning prize as an integer, the winning team and the expected match length as an
 * int representing minutes.
 *
 * @author chribrev
 * @version 1.0
 */
public abstract class Tournament {
    private ArrayList<Team> teams;
    private ArrayList<Match> matches;
    private ArrayList<Match> upcomingMatches;
    private Match currentMatch;
    private final String tournamentName;
    private final int firstPrize;
    private final String startDate;
    private Team winner;
    private final int matchLength;
    protected ArrayList<Match> previousRoundMatches = new ArrayList<>();

    /**
     * Constructor for object which represents an abstract tournament with all inputs
     * @param teams a list of all teams in the tournament
     * @param tournamentName the name of the tournament
     * @param firstPrize what you get for achieving first place
     * @param startDate when the tournament starts
     */
    public Tournament(ArrayList<Team> teams, String tournamentName, int firstPrize, String startDate)
            throws IllegalArgumentException, NullPointerException {
        checkNameInput(tournamentName);
        checkFirstPriceInput(firstPrize);
        checkStartDateInput(startDate);

        this.teams = teams;
        this.tournamentName = tournamentName;
        this.firstPrize = firstPrize;
        this.startDate = startDate;
        this.matchLength = 90;
        matches = new ArrayList<>();
        upcomingMatches = new ArrayList<>();
    }

    /**
     * Constructor for object which represents an abstract tournament, without a winning prize
     * @param teams a list of all teams in the tournament
     * @param tournamentName the name of the tournament
     * @param startDate when the tournament starts
     * @param matchLength the length which the match should be
     */
    public Tournament(ArrayList<Team> teams, String tournamentName, String startDate, int matchLength)
            throws IllegalArgumentException, NullPointerException{
        checkNameInput(tournamentName);
        checkStartDateInput(startDate);

        this.teams = teams;
        this.tournamentName = tournamentName;
        this.startDate = startDate;
        this.firstPrize = 0;
        this.matchLength = matchLength;
        matches = new ArrayList<>();
        upcomingMatches = new ArrayList<>();
    }

    /**
     * Constructor for object which represents an abstract tournament, without a winning prize or date
     * @param teams a list of all teams in the tournament
     * @param tournamentName the name of the tournament
     */
    public Tournament(ArrayList<Team> teams, String tournamentName)
            throws IllegalArgumentException, NullPointerException {
        checkNameInput(tournamentName);

        this.teams = teams;
        this.tournamentName = tournamentName;
        this.startDate = "[NO DATE]";
        this.firstPrize = 0;
        this.matchLength = 90;
        matches = new ArrayList<>();
        upcomingMatches = new ArrayList<>();
    }

    /**
     * Constructor for object which represents an abstract tournament,
     * mainly used for testing purposes
     * @param tournamentName the name of the tournament
     */
    public Tournament(String tournamentName) throws IllegalArgumentException, NullPointerException {
        checkNameInput(tournamentName);

        this.tournamentName = tournamentName;
        this.firstPrize = 0;
        this.startDate = "[NO DATE]";
        this.matchLength = 90;
        matches = new ArrayList<>();
        upcomingMatches = new ArrayList<>();
    }

    /**
     * Method for planning a new match
     * @param team1 first team competing in the match
     * @param team2 second team competing in the match
     */
    public void setUpcomingMatch(Team team1, Team team2){
        Match newMatch = new Match(team1, team2);
        upcomingMatches.add(newMatch);
    }

    /**
     * Method for initiating the next match
     */
    public void startNextMatch(){
        if(upcomingMatches.isEmpty()) {
            findUpcomingMatches();
        }
        currentMatch = upcomingMatches.get(0);
    }

    /**
     * Method for ending a match,
     * will remove match from list of future matches
     * and set the current match to empty
     */
    public void endMatch() {
        System.out.println("---------------------Match ended--------------------");
        matches.add(currentMatch);
        //previousRoundMatches.remove(0);
        previousRoundMatches.add(currentMatch);
        upcomingMatches.remove(currentMatch);
        currentMatch = null;
    }


    /**
     * Method for finding the match currently being played
     * @return the match currently being played
     */
    public Match getCurrentMatch() {
        return currentMatch;
    }

    /**
     * Method for finding the planned match time
     * This is the length of the match in its entirety, so halftime starts at the middle of the inputted time
     * @return int showing how many minutes a match should last
     */
    public int getMatchLength() {
        return matchLength;
    }

    /**
     * Abstract method for generating the next set of matches
     * Will be called if there are no planned matches yet more teams left
     */
    public abstract void findUpcomingMatches();

    /**
     * Method for finding the team which won the tournament
     * @return the team who won the tournament
     */
    public Team getWinner() {
        return winner;
    }

    /**
     * Method for changing match winner
     * @param winner which team who should win the tournament
     */
    public void setWinner(Team winner) {
        this.winner = winner;
    }

    /**
     * Method for finding all teams in the tournament
     * @return a list of teams
     */
    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * Method for finding all finished matches
     * @return a list of matches
     */
    public ArrayList<Match> getMatches() {
        return matches;
    }

    /**
     * Method for finding all planned matches
     * @return a list of matches
     */
    public ArrayList<Match> getUpcomingMatches() {
        return upcomingMatches;
    }

    /**
     * Method for finding the name of the tournament
     * @return Name of the tournament
     */
    public String getTournamentName() {
        return tournamentName;
    }

    /**
     * Method for finding the prize which the winner will receive
     * @return a number representing the first price value in kr
     */
    public int getFirstPrize() {
        return firstPrize;
    }

    /**
     * Method for finding the date of the tournament
     * @return a String representing the date of the tournament
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Method for finding how many teams who will participate in the tournament
     * @return a number representing the number of teams
     */
    public int getNumberOfTeams(){
        return teams.size();
    }

    /**
     * Method for adding a team into the tournament
     * @param team to add
     */
    public void addTeam(Team team){
        this.teams.add(team);
    }

    /**
     * Method for adding all teams in a list
     * @param newTeams to add
     */
    public void addAllTeams(List<Team> newTeams){
        this.teams.addAll(newTeams);
    }

    /**
     * Method for checking if Input name is null or blank
     * @param name is the name of the tournament
     */
    private void checkNameInput(String name) throws IllegalArgumentException, NullPointerException {
        if (name == null) {
            throw new NullPointerException("The tournament must have a name, you gave none");
        } else if (name.isBlank()) {
            throw new IllegalArgumentException("The tournament must have a name, you gave none");
        }
    }

    /**
     * Method which throws an IllegalArgumentException if the tournament prize is below 0
     * @param firstPrize is the reward for winning the tournament
     */
    private void checkFirstPriceInput(int firstPrize) throws IllegalArgumentException {
        if (firstPrize < 0) {
            throw new IllegalArgumentException("First price must be a positive number");
        }
    }

    /**
     * Method which throws an IllegalArgumentException if the start date is empty is not on the valid format
     * @param startDate is the date when the tournament starts
     */
    private void checkStartDateInput(String startDate) throws IllegalArgumentException {
        if(startDate.contains(":") && startDate.length() == 5) {
            try {
                String[] startDateValues = startDate.split(":");
                Integer.parseInt(startDateValues[0]);
                Integer.parseInt(startDateValues[1]);
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        else if (startDate.equals("[NO DATE]")) {
            return;
        }
        else {
            throw new IllegalArgumentException("Your date is empty or is using the wrong format. " +
                    "The date must be on the format mm:ss (ex 09:45 or 65:55)");
        }
    }

    /**
     * Method for acquiring information about the tournament
     * @return a string representing various tournament information
     */
    @Override
    public String toString() {
        return "Name: " + tournamentName +
                "\nDate: " + startDate +
                "\nPrize: " + firstPrize +
                "\nNumber of teams: " + teams.size() +
                "\nNext match: " + upcomingMatches.get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return Objects.equals(tournamentName, that.tournamentName);
    }
}