package edu.ntnu.idatt1002.k01g08.fta.objects.tournaments;

import edu.ntnu.idatt1002.k01g08.fta.objects.team.Team;

import java.util.ArrayList;
import java.util.Random;

/**
 * Creates a KnockOut type of Tournament
 * @author John Fredrik Bendvold
 * @version 2022-03-22
 */
public class KnockOut extends Tournament {
    //Number of stages in the tournament
    private int stages;
    //Contains all matches from the previous stage
    //private final ArrayList<Match> previousRoundMatches = new ArrayList<>();


    public KnockOut(String tournamentName, int stages) {
        super(tournamentName);
        this.stages = stages;
    }

    /**
     * Constructor for a KnockOut-Tournament
     * @param name the name of the tournament
     * @param teams a list of all teams in the tournament
     */
    public KnockOut(String name, ArrayList<Team> teams)
            throws IllegalArgumentException{
        super(teams, name);
        if (numberOfTeamsInvalid(teams.size())) {
            throw new IllegalArgumentException("The number of teams is invalid.");
        }

        calculateStages();
        findUpcomingMatches();
    }

    /**
     * Constructor for a KnockOut-Tournament
     * @param name the name of the tournament
     * @param startDate when the tournament starts
     * @param teams a list of all teams in the tournament
     * @param matchLength the length which the match should be
     */
    public KnockOut(String name, String startDate, ArrayList<Team> teams, int matchLength)
            throws IllegalArgumentException {
        super(teams, name, startDate, matchLength);
        if (numberOfTeamsInvalid(teams.size())) {
            throw new IllegalArgumentException("The number of teams is invalid.");
        }

        calculateStages();
        findUpcomingMatches();
    }

    /**
     * Constructor for a KnockOut-Tournament
     * @param name the name of the tournament
     * @param startDate when the tournament starts
     * @param teams a list of all teams in the tournament
     * @param firstPrice what you get for achieving first place
     */
    public KnockOut(String name, String startDate, int firstPrice, ArrayList<Team> teams)
            throws IllegalArgumentException{
        super(teams, name, firstPrice, startDate);
        if (numberOfTeamsInvalid(teams.size())) {
            throw new IllegalArgumentException("The number of teams is invalid.");
        }
        calculateStages();
        findUpcomingMatches();
    }

    /**
     * Constructor for a KnockOut-Tournament
     * @param teams a list of all teams in the tournament
     * @param tournamentName the name of the tournament
     * @param firstPrize what you get for achieving first place
     * @param startDate when the tournament starts
     * @param matchLength the length which the match should be
     */
    public KnockOut(ArrayList<Team> teams, String tournamentName, int firstPrize, String startDate, int matchLength)
            throws IllegalArgumentException {
        super(teams, tournamentName, firstPrize, startDate);

        if (numberOfTeamsInvalid(teams.size())) {
            throw new IllegalArgumentException("The number of teams is invalid.");
        }

        calculateStages();
        findUpcomingMatches();
    }

    public KnockOut(String tournamentName) {
        super(tournamentName);
    }

    /**
     * Checks if the variable is in the power of 2,
     * It has to be that inorder to be a valid knockout-tournament.
     * @return true if it's valid, false if it's not
     */
    public boolean numberOfTeamsInvalid(int num) {
        if (num < 2) {
            return true;
        } else {
            return (num & num - 1) != 0;
        }
    }

    /**
     * Finds out what in the power of 2 the number of teams are,
     * and sets that variable as the stages of the tournament.
     */
    private void calculateStages() {
        int num = super.getTeams().size();
        int repetitions = 1;

        while (num != Math.pow(2, repetitions)) {
            repetitions++;
        }

        this.stages = repetitions;
    }

    /**
     * Finds what matches are going to be played,
     * then places them in the upcomingMatches-list.
     */
    @Override
    public void findUpcomingMatches() {
        Random random = new Random();

        if (!getUpcomingMatches().isEmpty()) {
            return;
        }

        if (previousRoundMatches.size() == 1 || getMatches().size() == getNumberOfTeams()-1) {
            setWinner(getMatches().get(getMatches().size()-1).getWinner());
            System.out.println("Vinner er funnet, turneringen er ferdig.");
            return;
        }

        if (previousRoundMatches.isEmpty()) { //If previousRound == null, then it's the first round.
            System.out.println("first round");
            ArrayList<Team> teamsRemaining = new ArrayList<>(getTeams());

            while (!teamsRemaining.isEmpty()) {
                //Get two random numbers within the teamsRemaining-size
                int homeTeamNum = random.nextInt(teamsRemaining.size());
                int awayTeamNum;
                do {
                    awayTeamNum = random.nextInt(teamsRemaining.size());
                } while (homeTeamNum == awayTeamNum);

                Team homeTeam = teamsRemaining.get(homeTeamNum);
                Team awayTeam = teamsRemaining.get(awayTeamNum);


                setUpcomingMatch(homeTeam, awayTeam);

                teamsRemaining.remove(homeTeam);
                teamsRemaining.remove(awayTeam);
            }

            //return;
        }
        else { //If a previous round has been played, get winners from the matches.
            System.out.println(previousRoundMatches.size() + " matches last round.");
            while (previousRoundMatches.size() != 0) {
                System.out.println("not first round");
                int homeTeamNum = random.nextInt(previousRoundMatches.size());
                int awayTeamNum;
                do {
                    awayTeamNum = random.nextInt(previousRoundMatches.size());
                } while (homeTeamNum == awayTeamNum);

                Match match1 = previousRoundMatches.get(homeTeamNum);
                Match match2 = previousRoundMatches.get(awayTeamNum);

                Team homeTeam = match1.getWinner();
                Team awayTeam = match2.getWinner();

                System.out.println(homeTeam.getName() + " vs " + awayTeam.getName());

                setUpcomingMatch(homeTeam, awayTeam);

                previousRoundMatches.remove(match1);
                previousRoundMatches.remove(match2);
            }
            previousRoundMatches = new ArrayList<>();
        }
        previousRoundMatches = new ArrayList<>();
    }
}
