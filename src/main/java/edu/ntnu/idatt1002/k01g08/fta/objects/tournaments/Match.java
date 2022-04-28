package edu.ntnu.idatt1002.k01g08.fta.objects.tournaments;

import edu.ntnu.idatt1002.k01g08.fta.objects.team.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.team.Team;
import edu.ntnu.idatt1002.k01g08.fta.objects.events.Foul;
import edu.ntnu.idatt1002.k01g08.fta.objects.events.GameEvent;
import edu.ntnu.idatt1002.k01g08.fta.objects.events.Goal;
import edu.ntnu.idatt1002.k01g08.fta.objects.events.Substitution;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Represents a football match.
 * @author bendikme
 * @version 2022-03-31
 */
public class Match implements Iterable<GameEvent> {
    private Team homeTeam;
    private Team awayTeam;
    private Instant startTime;
    private int timeOffset = 0;
    private int lengthOfHalf = 45; // maximum length of a match half
    private int stage = 0; /*   0: Match has not started
                                1: First half
                                2: Pause
                                3: Second half
                                -1: Finished
    */
    final private List<GameEvent> matchHistory;
    final private static NumberFormat format = NumberFormat.getIntegerInstance();
    static {
        format.setMinimumIntegerDigits(2);
    }

    /*
    --------------------------------------------
    -- Constructors
    --------------------------------------------
     */

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
     * @throws IllegalArgumentException if the teams are equal
     * @throws NullPointerException if one of the specified teams is null
     */
    public Match(Team homeTeam, Team awayTeam) throws IllegalArgumentException, NullPointerException {
        setHomeTeam(homeTeam);
        setAwayTeam(awayTeam);
        this.matchHistory = new ArrayList<>();
    }

    /*
    --------------------------------------------
    -- Getters & setters
    --------------------------------------------
     */

    /**
     * Returns the home team for this match
     * @return home team for this match
     */
    public Team getHomeTeam() {
        return homeTeam;
    }

    /**
     * Returns the away team for this match
     * @return away team for this match
     */
    public Team getAwayTeam() {
        return awayTeam;
    }

    /**
     * Returns the team correspending to the entered boolean. True returns the home team, and false the away team.
     * @param homeTeam whether to return the home team
     * @return the home team if true, the away team if false
     */
    public Team getTeam(boolean homeTeam) {
        if (homeTeam) return this.homeTeam;
        else return awayTeam;
    }

    /**
     * Sets the specified team as the home team.
     * @param homeTeam the team to set as the home team
     * @throws IllegalArgumentException if the specified team equals the away team
     * @throws NullPointerException if the specified team is null
     * @throws IllegalStateException if the match has started
     */
    public void setHomeTeam(Team homeTeam) throws IllegalArgumentException, NullPointerException, IllegalStateException {
        if (isStarted()) throw new IllegalStateException("team change in ongoing match");
        if (homeTeam.size() < 1) throw new IllegalArgumentException("must be players on the team");
        if (Objects.equals(homeTeam, awayTeam)) throw new IllegalArgumentException("home team same as away team");
        this.homeTeam = homeTeam;
    }

    /**
     * Sets the specified team as the away team.
     * @param awayTeam the team to set as the away team
     * @throws IllegalArgumentException if the specified team equals the home team
     * @throws NullPointerException if the specified team is null
     * @throws IllegalStateException if the match has started
     */
    public void setAwayTeam(Team awayTeam) throws IllegalArgumentException, IllegalStateException, NullPointerException {
        if (isStarted()) throw new IllegalStateException("team change in ongoing match");
        if (awayTeam.size() < 1) throw new IllegalArgumentException("Must be players on the team");
        if (Objects.equals(homeTeam, awayTeam)) throw new IllegalArgumentException("home team same as away team");
        this.awayTeam = awayTeam;
    }

    /**
     * Returns the game event at the specified position in the match history.
     * @param index position of the event in the match history
     * @return game event at the specified position in the match history
     */
    public GameEvent getGameEvent(int index) {
        return matchHistory.get(index);
    }

    /**
     * Returns the game event at the specified position in the match history, counted from the last event.
     * @param index position of the event in the match history
     * @return game event at the specified position in the match history, counted from the last event
     */
    public GameEvent getLastGameEvent(int index) {
        return matchHistory.get(matchHistory.size()-++index);
    }

    /**
     * Returns the very last game event from the match history.
     * @return the very last game event in the match history
     */
    public GameEvent getLastGameEvent() {
        return getLastGameEvent(0);
    }

    /**
     * @return the amount of match histories
     */
    public int getMatchHistorySize() {
        return matchHistory.size();
    }

    /**
     * Returns the length of one half match.
     * @return the length of one half match
     */
    public int getLengthOfHalf() {
        return lengthOfHalf;
    }

    /**
     * Sets the length of one half match. This does not impact halves that have already been played,
     * but can be used to set the length of one in progress.
     * @param minutes number of minutes to set half length to
     */
    public void setLengthOfHalf(int minutes) {
        if (minutes < 1) throw new IllegalArgumentException("match length too low");
        lengthOfHalf = minutes;
    }

    /*
    --------------------------------------------
    -- Results & team scores
    --------------------------------------------
     */

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
            if (event instanceof Goal) {
                if (Objects.equals(homeTeam, event.getTeam())) homeScore++;
                else if (Objects.equals(awayTeam, event.getTeam())) awayScore++;
            }
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
            if (event instanceof Goal && Objects.equals(team, event.getTeam())) score++;
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
        if (isFinished()) {
            int[] scores = getTeamScores();
            if (scores[0] > scores[1]) return homeTeam;
            else return awayTeam;
        }
        System.out.println("Home goals: " + getHomeTeamScore());
        return null;
    }

    /*
    --------------------------------------------
    -- Match control methods
    --------------------------------------------
     */

    /**
     * Starts the match, and the match clock, if both teams have been registered.
     * If this is the third half, sets length of half to 15.
     * Returns true if this match's state changed because of the call.
     * @return true if this match's state changed because of the call
     */
    public boolean start() {
        if (onPause() && (homeTeam != null && awayTeam != null)) {
            stage++;
            if (stage == 5) setLengthOfHalf(15);
            startTime = Instant.now();
        }
        return isStarted();
    }

    /**
     * Pauses the match. Returns true if this match's state changed because of the call.
     * @return true if this match's state changed.
     */
    public boolean pause() {
        if (isPlaying()) {
            stage++;
            timeOffset += lengthOfHalf;
            return onPause();
        }
        return false;
    }

    /**
     * Ends the match and returns the winning team.
     * @return winning team of this match
     * @throws IllegalStateException if the match has not started
     */
    public Team end() throws IllegalStateException{
        //if (!isStarted()) throw new IllegalStateException("match not started");
        stage = -1;
        return getWinner();
    }

    /**
     * Returns true if the match is finished.
     * @return true if the match is finished
     */
    public boolean isFinished() {
        return stage == -1;
    }

    /**
     * Returns true if the match has started.
     * @return true if the match has started
     */
    public boolean isStarted() {
        return stage != 0;
    }

    /**
     * Returns true if the match is on pause.
     * @return true if the match is on pause
     */
    public boolean onPause() {
        return (stage&1) == 0; // checks if stage is even
    }

    /**
     * Returns true if the match is playing.
     * @return true if the match is playing
     */
    public boolean isPlaying() {
        return !isFinished()&&!onPause();
    }

    /**
     * Returns the number of the match half currently being played. If on pause, returns the previous one.
     * @return the half currently being played
     */
    public int currentHalf() {
        return (stage+1)/2;
    }

    /**
     * Returns the current match time as a string.
     * If the time is over the maximum length of a half, the time will be returned on the form ("[max length]+[difference]".
     * @return the current match time as a string.
     * @deprecated use currentMinute() instead
     */
    public String currentMatchTime() {
        if (onPause()) return Integer.toString(timeOffset);
        long minutes = currentDuration().toMinutes()+1;
        if (minutes > lengthOfHalf) {
            return lengthOfHalf+timeOffset + "+" + (minutes-lengthOfHalf);
        } else return Long.toString(minutes+timeOffset);
    }

    /**
     * Returns the current duration av the half being played.
     * @return the current duration av the half being played
     */
    private Duration currentDuration() {
        return Duration.between(startTime, Instant.now());
    }

    /**
     * Returns the current minute of the match as a string.
     * The first minute of the match is "01", the second "02", and so on.
     * Extra minutes get returned on the form "45+1".
     * @return the current minute of the match as a string
     */
    public String currentMinute() {
        if (onPause()) return format.format(timeOffset);
        long minutes = currentDuration().toMinutes()+1;
        if (minutes > lengthOfHalf) {
            return format.format(lengthOfHalf+timeOffset) + "+" + (minutes-lengthOfHalf);
        } else return format.format(minutes+timeOffset);
    }

    /**
     * The current timestamp of the match as a string.
     * The time is always returned on the form "MM:SS".
     * Each half starts counting without regard for extra minutes in the previous half
     * (so the second half in a default match always starts at "45:00").
     * @return the current timestamp of the match as a string
     */
    public String currentTime() {
        if (onPause()) return format.format(timeOffset) + ":00";
        Duration duration = currentDuration();
        long minutes =  duration.toMinutes() + timeOffset;
        int seconds = duration.toSecondsPart();
        return format.format(minutes) + ":" + format.format(seconds);
    }

    /*
    --------------------------------------------
    -- Game events
    --------------------------------------------
     */

    /**
     * Adds a game event to the match history, if the match has started.
     * @param gameEvent game event to add to the match history.
     * @throws IllegalStateException if match has not started yet
     */
    public void addGameEvent(GameEvent gameEvent) throws IllegalStateException {
        if (!isStarted()) throw new IllegalStateException("add game event before match start");
        matchHistory.add(gameEvent);
    }

    /**
     * Removes the game event at the specified position in the match history. Returns the event that was removed.
     * @param index position of the event to be removed
     * @return the event that was removed
     * @throws IndexOutOfBoundsException if no event exists at the specified position
     */
    public GameEvent removeGameEvent(int index) throws IndexOutOfBoundsException {
        return matchHistory.remove(index);
    }

    /**
     * Removes the game event at the specified position in the match history, counted from the last. Returns the event that was removed.
     * @param index position of the event to be removed, counted from the last
     * @return the event that was removed
     * @throws IndexOutOfBoundsException if no event exists at the specified position
     */
    public GameEvent removeLastGameEvent(int index) throws IndexOutOfBoundsException {
        return matchHistory.remove(matchHistory.size()-++index);
    }

    /**
     * Removes the game event at the specified position in the match history, counted from the last. Returns the event that was removed.
     * @return the event that was removed
     */
    public GameEvent removeLastGameEvent() {
        return removeLastGameEvent(0);
    }

    /**
     * Adds a new goal to the match history. If time stamp is null or blank, uses current match time.
     * @param team the scoring team
     * @param scoringPlayer the scoring player
     * @param assistingPlayer the assisting player
     * @param timeStamp the time stamp of the goal
     * @throws IllegalStateException if the match has not started yet
     * @throws NullPointerException if the team or scoring player is null
     */
    public void addGoal(Team team, Player scoringPlayer, Player assistingPlayer, String timeStamp)
            throws IllegalStateException, NullPointerException {
        if (scoringPlayer == null) throw new NullPointerException("player is null");
        if (team == null) throw new NullPointerException("team is null");
        if (timeStamp == null || timeStamp.isEmpty()) timeStamp = currentTime();
        addGameEvent(new Goal(scoringPlayer, team, timeStamp, assistingPlayer));
    }

    /**
     * Adds a new goal to the match history. If time stamp is null or blank, uses current match time.
     * @param homeTeam whether to add the goal to the home team
     * @param scoringPlayerNumber the squad number of the scoring player on the scoring team
     * @param assistingPlayerNumber the squad number of the assisting player on the scoring team
     * @param timeStamp the time stamp of the goal, or null or blank to use current match time
     * @throws IllegalStateException if the match has not started yet
     * @throws NullPointerException if the scoring player does not exist on the team
     */
    public void addGoal(boolean homeTeam, int scoringPlayerNumber, int assistingPlayerNumber, String timeStamp)
            throws IllegalStateException, NullPointerException {
        Team team = getTeam(homeTeam);
        addGoal(team, team.getPlayer(scoringPlayerNumber), team.getPlayer(assistingPlayerNumber), timeStamp);
    }

    /**
     * Adds a new goal to the match history. If time stamp is null or blank, uses current match time.
     * @param scoringPlayerIsHomeTeam whether to add the goal to the away team
     * @param scoringPlayerNumber the squad number of the scoring player
     * @param timeStamp the time stamp of the goal, or null or blank to use current match time
     * @throws IllegalStateException if the match has not started yet
     * @throws NullPointerException if the scoring player does not exist on the team
     */
    public void addSelfGoal(boolean scoringPlayerIsHomeTeam, int scoringPlayerNumber, String timeStamp)
            throws IllegalStateException, NullPointerException {
        Team team = getTeam(!scoringPlayerIsHomeTeam);
        Player player = getTeam(scoringPlayerIsHomeTeam).getPlayer(scoringPlayerNumber);
        addGoal(team, player, null, timeStamp);
    }

    /**
     * Adds a new substitution to the match history. If time stamp is null or blank, uses current match time.
     * @param team the scoring team
     * @param playerIn the player that is subbed in
     * @param playerOut the player that is subbed out
     * @param timeStamp the time stamp of the substitution
     * @throws IllegalStateException if the match has not started yet
     * @throws NullPointerException if the team or one of the players is null
     */
    public void addSubstitution(Team team, Player playerIn, Player playerOut, String timeStamp)
            throws IllegalStateException, NullPointerException {
        if (playerIn == null || playerOut == null) throw new NullPointerException("player is null");
        if (team == null) throw new NullPointerException("team is null");
        if (timeStamp == null || timeStamp.isEmpty()) timeStamp = currentTime();
        addGameEvent(new Substitution(timeStamp, team, playerIn, playerOut));
    }

    /**
     * Adds a new substitution to the match history. If time stamp is null or blank, uses current match time.
     * @param homeTeam whether to add the goal to the home team
     * @param playerInNumber the squad number of the player that is subbed in
     * @param playerOutNumber the squad number of the player that is subbed out
     * @param timeStamp the time stamp of the substitution
     * @throws IllegalStateException if the match has not started yet
     * @throws NullPointerException if one of the players is not on the team
     */
    public void addSubstitution(boolean homeTeam, int playerInNumber, int playerOutNumber, String timeStamp)
            throws IllegalStateException, NullPointerException {
        Team team = getTeam(homeTeam);
        addSubstitution(team, team.getPlayer(playerInNumber), team.getPlayer(playerOutNumber), timeStamp);
    }

    /**
     * Adds a new foul to the match history. If time stamp is null or blank, uses current match time.
     * @param team the team of the offending player
     * @param player the offending player
     * @param foulTag the tag of the foul
     * @param timeStamp the time stamp of the substitution
     * @throws IllegalStateException if the match has not started yet
     * @throws NullPointerException if the team or the player is null
     */
    public void addFoul(Team team, Player player, String foulTag, int giveCard, String timeStamp)
            throws IllegalStateException, NullPointerException {
        if (player == null) throw new NullPointerException("player is null");
        if (team == null) throw new NullPointerException("team is null");
        if (timeStamp == null || timeStamp.isEmpty()) timeStamp = currentTime();
        addGameEvent(new Foul(foulTag, timeStamp, player, team, giveCard));
    }

    /**
     * Adds a new foul to the match history. If time stamp is null or blank, uses current match time.
     * @param homeTeam whether the offending player is on the home team
     * @param playerNumber the squad number of the offending player
     * @param foulTag the tag of the foul
     * @param timeStamp the time stamp of the substitution
     * @throws IllegalStateException if the match has not started yet
     * @throws NullPointerException if a player with the squad number does not exist on the team
     */
    public void addFoul(boolean homeTeam, int playerNumber, String foulTag, int giveCard, String timeStamp)
            throws IllegalStateException, NullPointerException {
        Team team = getTeam(homeTeam);
        addFoul(team, team.getPlayer(playerNumber), foulTag, giveCard, timeStamp);
    }

    /*
    --------------------------------------------
    -- Utility methods
    --------------------------------------------
     */

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder().append(homeTeam.getName()).append(" vs. ").append(awayTeam.getName());
        if (!matchHistory.isEmpty()) {
            builder.append(":\n").append(getGameEvent(0).toString());
            for (int i = 1; i < matchHistory.size(); i++) {
                builder.append("\n").append(getGameEvent(i).toString());
            }
            builder.append(' ');
        }
        return builder.append(' ').toString();
    }
}
