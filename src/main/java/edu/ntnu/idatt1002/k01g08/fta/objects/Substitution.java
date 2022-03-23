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
 */
public class Substitution extends GameEvent {
    private Team teamSwitchingPlayers;
    private Player playerIn;
    private Player playerOut;

    /**
     * First constructor for a Substitution which requires all parameters under to instantiate a new Substitution
     * @param timeStampOfMatchTime, is a String which cannot have value zero or be a blank string
     * @param teamSwitchingPlayers, is of class Team
     * @param playerIn, is of class Player
     * @param playerOut, is of class Player
     * @throws IllegalArgumentException if the parameter timeStampOfMatchTime is either of null-value or
     * is a blank string
     */
    public Substitution(String timeStampOfMatchTime, Team teamSwitchingPlayers, Player playerIn, Player playerOut)
            throws IllegalArgumentException {
        super(null, teamSwitchingPlayers, timeStampOfMatchTime);
        this.teamSwitchingPlayers = teamSwitchingPlayers;
        this.playerIn = playerIn;
        this.playerOut = playerOut;
    }
    //TODO: another constructor with just playerIn and playerOut that gets the timestamp from Match and teamSwitching

    /**
     * Accessor method to get the player that is substituted in this Substitution
     * @return Player playerIn
     */
    public Player getPlayerIn() {
        return playerIn;
    }

    /**
     * Mutator method to alter the player that is substituted in this Substitution
     */
    public void setPlayerIn(Player playerIn) {
        this.playerIn = playerIn;
    }

    /**
     * Accessor method to get the player that is switched out in this Substitution
     * @return Player playerOut
     */
    public Player getPlayerOut() {
        return playerOut;
    }

    /**
     * Mutator method to alter the player that is switched out in this Substitution
     */
    public void setPlayerOut(Player playerOut) {
        this.playerOut = playerOut;
    }

    /**
     *
     */
    @Override
    public String getEvent() {
        return "Substitution";
    } // TODO: Update to explain event
}

