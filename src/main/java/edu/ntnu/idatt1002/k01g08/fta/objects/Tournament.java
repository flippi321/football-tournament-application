package edu.ntnu.idatt1002.k01g08.fta.objects;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Tournament {
    private ArrayList<Team> teams;
    private ArrayList<Match> matches;
    private ArrayList<Match> upcomingMatches;
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
        if(tournamentName.isBlank()) throw new IllegalArgumentException("The tournament must have a name, you gave none");
        if(firstPrize<0) throw new IllegalArgumentException("First price must be a positive number");
        if(startDate.isBlank() | startDate.length()>5) throw new IllegalArgumentException("Must be a valid date on the format: " +
                "mm:ss, (ex: 12:05 or 90:00)");
        if(matchLength<10 | matchLength > 120 | matchLength%2 != 0) throw new IllegalArgumentException("The match length must be a " +
                "number between 10 and 120 and be divisible by 2");
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
        if(tournamentName.isBlank()) throw new IllegalArgumentException("The tournament must have a name, you gave none");
        if(firstPrize<0) throw new IllegalArgumentException("First price must be a positive number");
        if(startDate.isBlank() | startDate.length()>5) throw new IllegalArgumentException("Must be a valid date on the format: " +
                "mm:ss, (ex: 12:05 or 90:00)");
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
        if (teams.isEmpty()) throw new IllegalArgumentException("Must have a valid list of teams, yours was empty");
        if(tournamentName.isBlank()) throw new IllegalArgumentException("The tournament must have a name, you gave none");
        if(startDate.isBlank() | startDate.length()>5) throw new IllegalArgumentException("Must be a valid date on the format: " +
                "mm:ss, (ex: 12:05 or 90:00)");
        if(matchLength<10 | matchLength > 120 | matchLength%2 != 0) throw new IllegalArgumentException("The match length must be a " +
                "number between 10 and 120 and be divisible by 2");
        this.teams = teams;
        this.tournamentName = tournamentName;
        this.startDate = startDate;
        this.firstPrize = 0;
        this.matchLength = matchLength;
        upcomingMatches = new ArrayList<>();
    }

    /**
     * Constructor for object which represents an abstract tournament, without a winning prize, match length or date
     * @param teams a list of all teams in the tournament
     * @param tournamentName the name of the tournament
     */
    public Tournament(ArrayList<Team> teams, String tournamentName) {
        if (teams.isEmpty()) throw new IllegalArgumentException("Must have a valid list of teams, yours was empty");
        if(tournamentName.isBlank()) throw new IllegalArgumentException("The tournament must have a name, you gave none");
        this.teams = teams;
        this.tournamentName = tournamentName;
        this.startDate = "[NO DATE]";
        this.firstPrize = 0;
        this.matchLength = 90;
        upcomingMatches = new ArrayList<>();
    }

    /**
     * @param tournamentName the name of the tournament
     * Constructor for object which represents an abstract tournament,
     * mainly used for testing purposes
     */
    public Tournament(String tournamentName) {
        if(tournamentName.isBlank()) throw new IllegalArgumentException("The tournament must have a name, you gave none");
        this.tournamentName = tournamentName;
        this.firstPrize = 0;
        this.startDate = "[NO DATE]";
        this.matchLength = 90;
        upcomingMatches = new ArrayList<>();
    }

    public void setUpcomingMatch(Team team1, Team team2){
        Match newMatch = new Match(team1, team2);
        upcomingMatches.add(newMatch);
    }

    public void startNextMatch(){
        if(upcomingMatches.isEmpty()) {
            findUpcomingMatches();
        }
        currentMatch = upcomingMatches.get(0);
    }

    public void endMatch() {
        matches.add(upcomingMatches.get(0));
        upcomingMatches.remove(0);
        currentMatch = null;
    }

    public Match getCurrentMatch() {
        return currentMatch;
    }

    public abstract void findUpcomingMatches();

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public ArrayList<Match> getUpcomingMatches() {
        return upcomingMatches;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public int getFirstPrize() {
        return firstPrize;
    }

    public String getStartDate() {
        return startDate;
    }

    public int getNumberOfTeams(){
        return teams.size();
    }

    @Override
    public String toString() {
        return "TournamentName: " + tournamentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return Objects.equals(tournamentName, that.tournamentName);
    }
}
