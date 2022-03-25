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
 *
 * @author bjornjob & magnulal
 * @version 2022-03-21
 */
public class Foul extends GameEvent{
    private String foulTag;
    private int giveCard;
    private int yellowCard = 0;
    private int redCard = 0;

    /**
     * Constructor of the Foul class
     * The constructor takes parameters for all attributes of the class
     * @param foulTag, is a String that is given the value 'null' if the string is blank
     * @param timeStampOfMatchTime, is a String which cannot have value zero or be a blank string
     * @param player, is a Player
     * @param team, is a Team
     * @param giveCard, is an integer deciding if the foul gives a card
     * @throws IllegalArgumentException if the parameter timeStampOfMatchTime is either of null-value or
     * is a blank string
     */
    public Foul(String foulTag, String timeStampOfMatchTime, Player player, Team team, int giveCard) throws
            IllegalArgumentException {
        super(player, team, timeStampOfMatchTime);

        if(foulTag.isBlank()) foulTag = null;
        this.foulTag = foulTag;
        if(giveCard == 1) {
            player.increaseYellowCards();
            if(yellowCard == 2) player.increaseRedCards();
        } else if (giveCard == 2) {
            player.increaseRedCards();
        }
    }

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
     * Accessor method to get the amount of yellow cards given with this foul
     * @return integer yellowCard
     */
    public int getYellowCard() {
        return yellowCard;
    }

    /**
     * Accessor method to get the amount of red cards given with this foul
     * @return integer redCard
     */
    public int getRedCard() {
        return redCard;
    }

    /**
     * GetEvent method to return a string description of this Foul event
     * @return String foul description
     */
    @Override
    public String getEvent() {
        return  "Foul type:" + foulTag +
                ", yellow cards given:" + yellowCard +
                ", red card given:" + redCard +
                ". committed by player: " + getPlayer().getName();
    }

    @Override
    public String toString() {
        return "FoulTag:'" + foulTag +
                ", amount of yellow cards:" + yellowCard +
                ", red card given:" + redCard;
    }
}
