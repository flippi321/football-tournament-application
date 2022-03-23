package edu.ntnu.idatt1002.k01g08.fta.objects;
import java.util.ArrayList;

public abstract class Tournament {
    protected ArrayList<Team> teams;
    protected ArrayList<Match> matches;
    protected ArrayList<Match> upcomingMatches;
    private final String tournamentName;
    private final int firstPrize;
    private final String startDate;
    private Team winner;

    /**
     * Constructor for object which represents an abstract tournament
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
    }

    /**
     * Constructor for object which represents an abstract tournament, without a winning prize
     * @param teams a list of all teams in the tournament
     * @param tournamentName the name of the tournament
     * @param startDate when the tournament starts
     */
    public Tournament(ArrayList<Team> teams, String tournamentName, String startDate) {
        this.teams = teams;
        this.tournamentName = tournamentName;
        this.startDate = startDate;
        this.firstPrize = 0;
    }

    public void setUpcomingMatch(Team team1, Team team2){
        Match newMatch = new Match();
        upcomingMatches.add(newMatch);
    }

    public void startNextMatch(){
        if(upcomingMatches.isEmpty()){
            findUpcomingMatches();
        }
        else{
            upcomingMatches.get(0).startMatch();
        }
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

    @Override
    public String toString() {
        return "Tournament{" +
                "teams=" + teams +
                '}';
    }
}
