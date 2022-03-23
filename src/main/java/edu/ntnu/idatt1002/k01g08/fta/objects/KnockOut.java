package edu.ntnu.idatt1002.k01g08.fta.objects;

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
    private ArrayList<Match> previousRoundMatches = new ArrayList<>();

    /**
     * Constructor for a KnockOut-Tournament
     * @param name the name of the tournament
     * @param startDate when the tournament starts
     * @param teams a list of all teams in the tournament
     */
    public KnockOut(String name, String startDate, ArrayList<Team> teams) {
        super(teams, name, startDate);
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
    public KnockOut(String name, String startDate, ArrayList<Team> teams, int firstPrice) {
        super(teams, name, firstPrice, startDate);
        if (numberOfTeamsInvalid(teams.size())) {
            throw new IllegalArgumentException("The number of teams is invalid.");
        }

        calculateStages();
        findUpcomingMatches();
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
        int num = super.teams.size();
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
        ArrayList<Team> teamsRemaining;

        Random random = new Random();

        if (previousRoundMatches.isEmpty()) { //If previousRound == null, then it's the first round.
            teamsRemaining = teams;

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
        }
        else { //If a previous round has been played, get winners from the matches.
            while (!previousRoundMatches.isEmpty()) {
                int homeTeamNum = random.nextInt(previousRoundMatches.size());
                int awayTeamNum;
                do {
                    awayTeamNum = random.nextInt(previousRoundMatches.size());
                } while (homeTeamNum == awayTeamNum);

                Team homeTeam = previousRoundMatches.get(homeTeamNum).getWinner();
                Team awayTeam = previousRoundMatches.get(awayTeamNum).getWinner();

                setUpcomingMatch(homeTeam, awayTeam);

                previousRoundMatches.remove(previousRoundMatches.get(homeTeamNum));
                previousRoundMatches.remove(previousRoundMatches.get(awayTeamNum));
            }
        }

        previousRoundMatches.addAll(getUpcomingMatches());
    }
}
