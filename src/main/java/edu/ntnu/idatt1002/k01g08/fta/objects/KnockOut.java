package edu.ntnu.idatt1002.k01g08.fta.objects;

import java.util.ArrayList;

public class KnockOut extends Tournament {
    private int stages;

    public KnockOut(String name, String startDate, ArrayList<Team> teams) {
        super(teams, name, startDate);
        if (numberOfTeamsInvalid(teams.size())) {
            throw new IllegalArgumentException("The number of teams is invalid.");
        }

        calculateStages();
        findUpcomingMatches();
    }

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
    private void findUpcomingMatches() {

    }
}
