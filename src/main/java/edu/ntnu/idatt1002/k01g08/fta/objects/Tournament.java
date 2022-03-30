package edu.ntnu.idatt1002.k01g08.fta.objects;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Tournament {
    protected ArrayList<Team> teams;
    protected ArrayList<Match> matches;
    protected ArrayList<Match> upcomingMatches;
    private Match currentMatch;
    private final String tournamentName;
    private final int firstPrize;
    private final String startDate;
    private Team winner;
    private int matchLength;

    /**
     * Constructor for object which represents an abstract tournament
     * @param teams a list of all teams in the tournament
     * @param tournamentName the name of the tournament
     * @param firstPrize what you get for achieving first place
     * @param startDate when the tournament starts
     * @param matchLength the length which the match should be
     *
     */
    public Tournament(ArrayList<Team> teams, String tournamentName, int firstPrize, String startDate, int matchLength) {
        this.teams = teams;
        this.tournamentName = tournamentName;
        this.firstPrize = firstPrize;
        this.startDate = startDate;
        this.matchLength = matchLength;
        upcomingMatches = new ArrayList<>();
    }

    /**
     * Constructor for object which represents an abstract tournament, without matchLength
     * @param teams a list of all teams in the tournament
     * @param tournamentName the name of the tournament
     * @param firstPrize what you get for achieving first place
     * @param startDate when the tournament starts
     */
    public Tournament(ArrayList<Team> teams, String tournamentName, int firstPrize, String startDate) {
        this.teams = teams;
        this.tournamentName = tournamentName;
        this.firstPrize = firstPrize;
        this.startDate = startDate;
        this.matchLength = 90;
        upcomingMatches = new ArrayList<>();
    }

    /**
     * Constructor for object which represents an abstract tournament, without a winning prize
     * @param teams a list of all teams in the tournament
     * @param tournamentName the name of the tournament
     * @param startDate when the tournament starts
     * @param matchLength the length which the match should be
     */
    public Tournament(ArrayList<Team> teams, String tournamentName, String startDate, int matchLength) {
        this.teams = teams;
        this.tournamentName = tournamentName;
        this.startDate = startDate;
        this.firstPrize = 0;
        this.matchLength = matchLength;
        upcomingMatches = new ArrayList<>();
    }

    /**
     * Constructor for object which represents an abstract tournament, without a winning prize or date
     * @param teams a list of all teams in the tournament
     * @param tournamentName the name of the tournament
     */
    public Tournament(ArrayList<Team> teams, String tournamentName) {
        this.teams = teams;
        this.tournamentName = tournamentName;
        this.startDate = "[NO DATE]";
        this.firstPrize = 0;
        upcomingMatches = new ArrayList<>();
    }

    /**
     * @param tournamentName the name of the tournament
     * Constructor for object which represents an abstract tournament,
     * mainly used for tests
     */
    public Tournament(String tournamentName) {
        this.tournamentName = tournamentName;
        this.firstPrize = 0;
        this.startDate = "[NO DATE]";
        this.matchLength = 90;
    }

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
        matches.add(upcomingMatches.get(0));
        upcomingMatches.remove(0);
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

    //TODO
    // Add Javadoc
    // Change class variables 'teams', 'matches' and 'upcomingMatches' to private.
    // - Note: this will have to be reflected in the class Knockout which uses these attributes directly. Knockout must
    //          use get methods from this class.
    // Add exception handling in class constructor.
    // - You should not be able to add an empty ArrayList 'teams'.
    // - You should not be able to add 'tournamentName' as null-input or as an empty string.
}
