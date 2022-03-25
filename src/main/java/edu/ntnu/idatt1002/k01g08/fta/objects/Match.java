package edu.ntnu.idatt1002.k01g08.fta.objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Represents a football match.
 * @author bendikme
 * @version 2022-03-23
 */
public class Match implements Iterable<GameEvent> {
    private Team homeTeam;
    private Team awayTeam;
    private boolean finished = false;
    final private List<GameEvent> matchHistory;

    /**
     * Creates a new match
     */
    public Match() {
        this.matchHistory = new ArrayList<>();
    }

    /**
     * Creates a new match with the specified home and away team.
     * @param homeTeam home team for this match
     * @param awayTeam away team for this match
     */
    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchHistory = new ArrayList<>();
    }

    /**
     * Returns the home team for this match
     * @return home team for this match
     */
    public Team getHomeTeam() {
        return homeTeam;
    }

    /**
     * Returns the away team  for this match
     * @return away team for this match
     */
    public Team getAwayTeam() {
        return awayTeam;
    }

    /**
     * Returns the score (number of goals) of the home team in this match
     * @return score of the home team in this match
     */
    public int getHomeTeamScore() {
        return getTeamScore(homeTeam);
    }

    /**
     * Returns the score (number of goals) of the away team in this match
     * @return score of the away team in this match
     */
    public int getAwayTeamScore() {
        return getTeamScore(awayTeam);
    }

    /**
     * Returns the scores (number of goals) for the teams in this match as an int array.
     * getTeamScores()[0] is the home team score, and getTeamScores()[1] is the away team score.
     * @return scores of the teams in this match in an array
     */
    private int[] getTeamScores() {
        int homeScore = 0;
        int awayScore = 0;
        for (GameEvent event : matchHistory) {
            homeScore += 0;
            awayScore += 0;
        }
        return new int[]{homeScore, awayScore};
    }

    /**
     * Returns the specified team's score (number of goals) in this match
     * @param team team to calculate the score for
     * @return score of the specified team
     */
    private int getTeamScore(Team team) {
        int score = 0;
        for (GameEvent event : matchHistory) {
            score += 0;
        }
        return score;
    }

    /**
     * Returns the winner of this match, if the match is finished.
     * The home team wins if it has more goals than the away team.
     * The away team wins if it has at least as many goals as the home team.
     * Returns null if the match has not ended.
     * @return winner of the match if the match is finished, or null if not
     */
    public Team getWinner() {
        if (finished) {
            int[] scores = getTeamScores();
            if (scores[0] > scores[1]) return homeTeam;
            else return awayTeam;
        }
        return null;
    }

    /**
     * Sets the specified team as the home team.
     * @param homeTeam the team to set as the home team
     */
    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    /**
     * Sets the specified team as the away team.
     * @param awayTeam the team to set as the away team
     */
    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    /**
     * Returns the game event at the specified position in the match history.
     * @param i position of the event in the match history
     * @return game event at the specified position in the match history
     */
    public GameEvent getGameEvent(int i) {
        return matchHistory.get(i);
    }

    /**
     * Returns the game event at the specified position in the match history, counted from the last event.
     * @param i position of the event in the match history
     * @return game event at the specified position in the match history, counted from the last event
     */
    public GameEvent getLastGameEvent(int i) {
        return matchHistory.get(matchHistory.size()-++i);
    }

    /**
     * Returns an iterator over the game events in the match history.
     * @return an iterator over the game events in the match history
     */
    @Override
    public Iterator<GameEvent> iterator() {
        return matchHistory.iterator();
    }

    /**
     * Returns a stream of the game events in this match's match history.
     * @return a stream of the game events in this match's match history.
     */
    public Stream<GameEvent> eventStream() {
        return matchHistory.stream();
    }

    /**
     * Adds a game event to the match history.
     * @param gameEvent game event to add to the match history.
     */
    public void addGameEvent(GameEvent gameEvent) {
        matchHistory.add(gameEvent);
    }

    /**
     * Starts the match.
     */
    public void start() {
    }

    /**
     * Ends the match and returns the winning team.
     * @return winning team of this match
     */
    public Team end() {
        finished = true;
        return getWinner();
    }
}
