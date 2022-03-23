package edu.ntnu.idatt1002.k01g08.fta.objects;
/**
 * Class representing a specific type of GameEvent called Foul
 * The Foul has a String for a foulTag, this can be a brief description, for example:
 * 'Hands'
 * The Foul contains information about at what point in match time occurred, this information
 * is stored as a String timeStampOfMatchTime. It also stores information about which player committed the
 * foul. This attribute is of class Player. A foul is also done by one of the two teams, and therefore
 * the foul has information about Team teamCommittingFoul.
 * This class has three constructors
 * The first takes parameters for all attributes
 * The second leaves foulTag at null-value
 * The third only takes the player who committed the foul as a parameter, and
 * sets values for all the other attributes
 */
public class Foul extends GameEvent{
    private String foulTag;

    /**
     * First constructor of the Foul class
     * The constructor takes parameters for all attributes of the class
     * @param foulTag, is a String that is given the value 'null' if the string is blank
     * @param timeStampOfMatchTime, is a String which cannot have value zero or be a blank string
     * @param committingPlayer, is a Player
     * @param teamCommittingFoul, is a Team
     * @throws IllegalArgumentException if the parameter timeStampOfMatchTime is either of null-value or
     * is a blank string
     */
    public Foul(String foulTag,String timeStampOfMatchTime, Player committingPlayer, Team teamCommittingFoul) throws
            IllegalArgumentException {
        if(timeStampOfMatchTime == null) throw new IllegalArgumentException("The value of the timestamp of " +
                "substitution was 'null', please try again.");
        if(timeStampOfMatchTime.isBlank()) throw new IllegalArgumentException("The timestamp was inputted " +
                "an empty string, please try again");
        if(foulTag.isBlank()) foulTag = null;
        this.foulTag = foulTag;
        this.timeStampOfMatchTime = timeStampOfMatchTime;
        this.committingPlayer = committingPlayer;
        this.teamCommittingFoul = teamCommittingFoul;
    }
    /**
     * Second constructor of the class Foul
     * The constructor takes all parameters for all attributes except foulTag which is left with
     * 'null' as value
     * @param timeStampOfMatchTime, is a String which cannot have value zero or be a blank string
     * @param committingPlayer, is a Player
     * @param teamCommittingFoul, is a Team
     * @throws IllegalArgumentException if the parameter timeStampOfMatchTime is either of null-value or
     * is a blank string
     */
    public Foul(String timeStampOfMatchTime, Player committingPlayer, Team teamCommittingFoul) throws
            IllegalArgumentException {
        if(timeStampOfMatchTime == null) throw new IllegalArgumentException("The value of the timestamp of " +
                "substitution was 'null', please try again.");
        if(timeStampOfMatchTime.isBlank()) throw new IllegalArgumentException("The timestamp was inputted " +
                "an empty string, please try again");
        this.timeStampOfMatchTime = timeStampOfMatchTime;
        this.committingPlayer = committingPlayer;
        this.teamCommittingFoul = teamCommittingFoul;
    }
    //TODO: add a third constructor which just needs the committingPlayer
    /**
     * Accessor method to get the tag of this foul
     * @return String foulTag
     */
    public String getFoulTag() {
        return foulTag;
    }
    /**
     * Mutator method to alter the tag of this foul
     * @param foulTag is a String
     */
    public void setFoulTag(String foulTag) {
        this.foulTag = foulTag;
    }
    /**
     * Accessor method to get the timestamp string of this foul
     * @return String timeStampOfMatchTime
     */

    /**
     * Accessor method to get the player who committed this foul
     * @return Player committingPlayer
     */
    public Player getCommittingPlayer() {
        return committingPlayer;
    }
    /**
     * Mutator method to alter the player who has committed this foul
     * @param committingPlayer is a Player
     */
    public void setCommittingPlayer(Player committingPlayer) {
        this.committingPlayer = committingPlayer;
    }
    /**
     * Accessor method to get the team that has this foul registered to it
     * @return Team teamCommittingFoul
     */
    public Team getTeamCommittingFoul() {
        return teamCommittingFoul;
    }
    /**
     * Mutator method to alter which team that committed this foul
     * @param teamCommittingFoul is a team
     */
    public void setTeamCommittingFoul(Team teamCommittingFoul) {
        this.teamCommittingFoul = teamCommittingFoul;
    }
}
