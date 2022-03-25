package edu.ntnu.idatt1002.k01g08.fta.objects;

/**
 * Class representing a specific type of GameEvent called Substitution
 * The Substitution holds information on when during a match that the action has happened
 * in a form of a String timeStampOfMatchTime. It also stores information about the team exercising
 * the substitution, and which players that switch playing in the match
 * The class has two constructors
 * The first takes parameters for all attributes
 *
 * and understands which team that is making a substitution based on the players switched
 *
 * @author bjornjob & magnulal
 * @version 2022-03-21
 *
 */
public class Substitution extends GameEvent {
    private Player playerIn;
    private Player playerOut;

    /**
     * First constructor for a Substitution which requires all parameters under to instantiate a new Substitution
     * @param timeStampOfMatchTime, is a String which cannot have value zero or be a blank string
     * @param teamSwitchingPlayers, is of class Team and is the team making the substitution
     * @param playerIn, is of class Player and is the player being subbed in
     * @param playerOut, is of class Player and is the player being subbed out
     * @throws IllegalArgumentException if the parameter timeStampOfMatchTime is either of null-value or
     * is a blank string
     */
    public Substitution(String timeStampOfMatchTime, Team teamSwitchingPlayers, Player playerIn, Player playerOut)
            throws IllegalArgumentException {
        super(null, teamSwitchingPlayers, timeStampOfMatchTime);
        this.playerIn = playerIn;
        this.playerOut = playerOut;
    }
    /**
     * Accessor method to get the player that is substituted in this Substitution
     * @return Player playerIn
     */
    public Player getPlayerIn() {
        return playerIn;
    }

    /**
     * Mutator method to alter the player that is substituted in this Substitution
     * @param playerIn is the player being subbed on
     */
    public void setPlayerIn(Player playerIn) {
        this.playerIn = playerIn;
    }

    /**
     * Accessor method to get the player that is subbed out in this Substitution
     * @return Player playerOut
     */
    public Player getPlayerOut() {
        return playerOut;
    }

    /**
     * Mutator method to alter the player that is switched out in this Substitution
     * @param playerOut is the player being subbed out
     */
    public void setPlayerOut(Player playerOut) {
        this.playerOut = playerOut;
    }

    /**
     * GetEvent method to get a string description of the event
     * @return String substitution description
     */
    @Override
    public String getEvent() {
        return "Team: " + getTeam() + " substituted player: "+ getPlayerOut() + " for: " + getPlayerIn() +
                " at: " + getTimeStampOfMatchTime() + " of the match.";
    }
    @Override
    public String toString() {
        return "Substitution:\n" +
                "Player in: " + getPlayerIn() +
                "\nPlayer out: " + getPlayerOut();
    }
}

