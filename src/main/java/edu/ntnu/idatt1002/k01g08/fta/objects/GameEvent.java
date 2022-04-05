package edu.ntnu.idatt1002.k01g08.fta.objects;

/**
 * Represents a game event which occurs during match-time
 * Is an abstract superclass describing the commonalities of all
 * subclasses.
 * @author bjornjob & magnulal
 * @version 2022-03-21
 */
public abstract class GameEvent {
    private Player player;
    private Team team;
    private String timeStamp;

    /**
     * Constructor to instantiate an object of a subclass of the 'GameEvent'
     * @param player is an object of the class 'Player'
     * @param team is an object of the class 'Team'
     * @param timeStamp is a string representation of the time of which
     * @throws IllegalArgumentException if the timeStamp string .isBlank()
     * @throws NullPointerException if the timeStamp, player or team has the value 'null'
     */
    public GameEvent(Player player, Team team, String timeStamp) throws IllegalArgumentException,
            NullPointerException {
        checkInputsForNull(player, team);
        this.player = player;
        this.team = team;
        this.setTimeStampOfMatchTime(timeStamp);
    }

    /**
     * Method to get the string of the timestamp when the game event occurred in the match
     * @return string timeStamp
     */
    public String getTimeStampOfMatchTime() {
        return timeStamp;
    }

    /**
     * Mutator method to alter the timestamp of this event
     * @param timeStampOfMatchTime is a string representation of the time of which
     * @throws IllegalArgumentException if the timeStamp string .isBlank()
     * @throws NullPointerException if the timeStamp string has the value 'null'
     */
    public void setTimeStampOfMatchTime(String timeStampOfMatchTime) throws IllegalArgumentException,
            NullPointerException {
        checkStringInputForNullAndBlank(timeStampOfMatchTime);
        this.timeStamp = timeStampOfMatchTime;
    }

    /**
     * Abstract method to force subclasses to define an implementation of a string describing the
     * event
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
     * @return an object of the Team class
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Help method to check if the parameter string is of illegal input
     * @param string is an input string which is checked for being null or blank
     * @throws IllegalArgumentException if the parameter string is either 'null' or .isBlank. Then the parameter
     * is considered an illegal argument
     */
    private void checkStringInputForNullAndBlank(String string) throws IllegalArgumentException, NullPointerException{
        if (string == null) {
            throw new NullPointerException("The value of the timestamp of substitution was 'null', " +
                    "which is not valid.");
        } else if (string.isBlank()) {
            throw new IllegalArgumentException("The timestamp was inputted as an empty string.");
        }
    }

    /**
     * Help method to check if the parameters player or team have a value of 'null'
     * @param player is a player of class 'Player'
     * @param team is a team of class 'Team'
     * @throws NullPointerException if either of the parameters are 'null'
     */
    private void checkInputsForNull(Player player, Team team) throws NullPointerException{
        if (player == null) throw new NullPointerException("A player was inputted with a 'null'- value.");
        if (team == null) throw new NullPointerException("A team was inputted with a 'null'- value.");
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
}
