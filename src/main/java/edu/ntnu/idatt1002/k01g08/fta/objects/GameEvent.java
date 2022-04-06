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
        checkStringInputForNullAndBlank(timeStamp);
        checkTimeStampFormat(timeStamp);
        this.player = player;
        this.team = team;
        this.setTimeStampOfMatchTime(timeStamp);
    }

    /**
     * Returns the string of a timestamp referring to when the game event occurred during a match
     * @return string timeStamp which
     */
    public String getTimeStampOfMatchTime() {
        return timeStamp;
    }

    /**
     * Setter to alter the timestamp associated with this event
     * @param timeStampOfMatchTime is a string representation of the time of which
     * @throws IllegalArgumentException if the timeStamp string .isBlank()
     * @throws NullPointerException if the timeStamp string has the value 'null'
     */
    public void setTimeStampOfMatchTime(String timeStampOfMatchTime) throws IllegalArgumentException,
            NullPointerException {
        checkStringInputForNullAndBlank(timeStampOfMatchTime);
        checkTimeStampFormat(timeStampOfMatchTime);
        this.timeStamp = timeStampOfMatchTime;
    }

    /**
     * Forces subclasses to define an implementation of a string describing the
     * event
     * @return GameEvent value
     */
    public abstract String getEvent();

    /**
     * Returns the player who is associated with this game event
     * @return Player player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Setter to alter the player who is associated with this game event
     * @param player is a Player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Returns the team that is associated with this game event
     * @return an object of the Team class
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Check if the parameter string is of illegal input. Is used in the constructor and the setter method for
     * timeStamp
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
     * Check if the parameters player or team have a value of 'null'. Is a help method used by the constructor
     * @param player is a player of class 'Player'
     * @param team is a team of class 'Team'
     * @throws NullPointerException if either of the parameters are 'null'
     */
    private void checkInputsForNull(Player player, Team team) throws NullPointerException{
        if (player == null) throw new NullPointerException("A player was inputted with a 'null'- value.");
        if (team == null) throw new NullPointerException("A team was inputted with a 'null'- value.");
    }

    /**
     * Throws an IllegalArgumentException if the time stamp is not on the valid format. Is a help method
     * used in the constructor and the setter method for timestamp
     * @param timeStamp is the time of which the game event occurred during match time
     */
    private void checkTimeStampFormat(String timeStamp) throws IllegalArgumentException {
        if(timeStamp.contains(":") && (timeStamp.length() == 5 || timeStamp.length() == 6)) {
            String[] timeStampParts = timeStamp.split(":");
            int[] timeStampValues = {-1,-1};
            try {
                timeStampValues[0] = Integer.parseInt(timeStampParts[0]);
                timeStampValues[1] = Integer.parseInt(timeStampParts[1]);
            } catch (Exception e) {
                throw new IllegalArgumentException("The time stamp was not able to be parsed to integers, " +
                        "only time stamps on the format: mm:ss (ex 09:45, 65:55 or 102:05) are allowed.");
            }
            if(timeStampValues[0] < 0) throw new
                    IllegalArgumentException("The first number of the time stamp was outside the valid integer" +
                    " range [0,999].");
            if(timeStampValues[1] < 0 || timeStampValues[1] > 60) throw new
                    IllegalArgumentException("The second number of the time stamp was outside the valid integer" +
                    " range [0,60].");
        }else{
            throw new IllegalArgumentException("The time stamp is using the wrong format. " +
                    "The time must be on the format mm:ss (ex 09:45, 65:55 or 102:05)");
        }
    }

    /**
     * Setter to alter which team that is associated with this game event
     * @param team is a team
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Returns a string representation of a GameEvent object
     * @return a string representing an object of the class GameEvent.
     */
    @Override
    public String toString() {
        return "\nGameEvent" +
                "\nplayer" + player +
                "\nteam" + team;
    }
}
