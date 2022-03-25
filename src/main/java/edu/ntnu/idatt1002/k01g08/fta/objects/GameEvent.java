package edu.ntnu.idatt1002.k01g08.fta.objects;

/**
 * Represents a game event.
 * @author bjornjob & magnulal
 * @version 2022-03-21
 */
public abstract class GameEvent {
    private Player player;
    private Team team;
    private String timeStamp;

    public GameEvent(Player player, Team team, String timeStamp) throws IllegalArgumentException {
        this.player = player;
        this.team = team;
        this.setTimeStampOfMatchTime(timeStamp);
    }

    public String getTimeStampOfMatchTime() {
        return timeStamp;
    }

    /**
     * Mutator method to alter the timestamp of this event
     * @param timeStampOfMatchTime is a String which cannot have value zero or be a blank string
     * @throws IllegalArgumentException, if the parameter timeStampOfMatchTime is either of null-value or
     * is a blank string
     */
    public void setTimeStampOfMatchTime(String timeStampOfMatchTime) throws
            IllegalArgumentException {
        if(timeStampOfMatchTime == null) throw new IllegalArgumentException("The value of the timestamp of " +
                "substitution was 'null', please try again.");
        if(timeStampOfMatchTime.isBlank()) throw new IllegalArgumentException("The timestamp was inputted as " +
                "an empty string, please try again.");
        this.timeStamp = timeStampOfMatchTime;
    }

    /**
     *
     * @return GameEvent value
     */
    public abstract String getEvent();

    /**
     * Accessor method to get the player who is associated with this game event
     * @return Player player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Mutator method to alter the player who is associated with this game event
     * @param player is a Player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Accessor method to get the team that is associated with this game event
     * @return Team team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Mutator method to alter which team that is associated with this game event
     * @param team is a team
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Get string version of a GameEvent object.
     * @return a string representing an object of the class GameEvent.
     */
    @Override
    public String toString() {
        return "\nGameEvent" +
                "\nplayer" + player +
                "\nteam" + team;
    }

    //TODO
    // Make exception messages more concise
    // Reword JavaDoc
    // Add NullPointerException
    // Create tests (possibly abstract)
}
