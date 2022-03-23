package edu.ntnu.idatt1002.k01g08.fta.objects;

/**
 * Represents a game event.
 * @author bjornjob
 * @version 2022-03-21
 */
public abstract class GameEvent {
    private Player player;
    private Team team;
    private String timeStamp;

    public GameEvent(Player player, Team team, String timeStamp) throws IllegalArgumentException {
        if(timeStamp == null) throw new IllegalArgumentException("The value of the timestamp of " +
                "substitution was 'null', please try again.");
        if(timeStamp.isBlank()) throw new IllegalArgumentException("The timestamp was inputted " +
                "an empty string, please try again");
        this.player = player;
        this.team = team;
        this.timeStamp = timeStamp;
    }

    public String getTimeStampOfMatchTime() {
        return timeStamp;
    }
    /**
     * Mutator method to alter the timestamp of this foul
     * @param timeStampOfMatchTime is a String which cannot have value zero or be a blank string
     * @throws IllegalArgumentException, if the parameter timeStampOfMatchTime is either of null-value or
     * is a blank string
     */
    public void setTimeStampOfMatchTime(String timeStampOfMatchTime) throws
            IllegalArgumentException {
        if(timeStampOfMatchTime == null) throw new IllegalArgumentException("The value of the timestamp of " +
                "substitution was 'null', please try again.");
        if(timeStampOfMatchTime.isBlank()) throw new IllegalArgumentException("The timestamp was inputted " +
                "an empty string, please try again");
        this.timeStamp = timeStampOfMatchTime;
    }

    public abstract int getAction();

    @Override
    public String toString() {
        return "GameEvent{" +
                "player=" + player +
                ", team=" + team +
                '}';
    }
}
