package edu.ntnu.idatt1002.k01g08.fta.objects;

/**
 * Class representing a goal scored in a match. This is a specific game event and
 * is therefore represented as being a subclass of the abstract class 'GameEvent'
 * Additional information associated with a Goal is another player, which is the player
 * that assisted the goal
 * This player does not have to be specified as some Goals happen without any given
 * player assisting the player who scores
 * To circumvent this, the class has two constructors, with and without a parameter for
 * assistingPlayer
 */
public class Goal extends GameEvent {
    private Player assistingPlayer;

    /**
     * First constructor to instantiate specific Goals
     * @param goalScorer is the player scoring the goal
     * @param team is the team scoring the goal
     * @param timeStamp is a String with the time of the goal
     * @param assistingPlayer is the player assisting the goal
     * @throws IllegalArgumentException specified in the GameEvent class
     */
    public Goal(Player goalScorer, Team team, String timeStamp, Player assistingPlayer) throws IllegalArgumentException {
        super(goalScorer, team, timeStamp);
        this.assistingPlayer = assistingPlayer;
    }

    /**
     * Second constructor to instantiate a Goal without an assistingPlayer
     * @param goalScorer is the player scoring the goal
     * @param team is a Team
     * @param timeStamp is String
     * @throws IllegalArgumentException specified in the GameEvent class
     */
    public Goal(Player goalScorer, Team team, String timeStamp) throws IllegalArgumentException {
        super(goalScorer, team, timeStamp);
    }

    /**
     * Accessor method to get the assisting player of this goal
     * @return Player assistingPlayer
     */
    public Player getAssistingPlayer() {
        return assistingPlayer;
    }

    /**
     * GetEvent method to get a full description of the Goal event
     * @return String goal description
     */

    //TODO: Add test
    @Override
    public String getEvent() {
        if(assistingPlayer != null){
            return "The player: " + getPlayer().getName() + " scored a goal for team: " + getTeam() +
                    " with assist from player: " + getAssistingPlayer() + "at time: " + getTimeStampOfMatchTime();
        }else{
            return "The player: " + getPlayer().getName() +
                    " scored a goal for team: " + getTeam() + "at time: " + getTimeStampOfMatchTime();
        }
    }

    @Override
    public String toString() {
        return "Goal by: "+getPlayer().getName()+""  +
                "\nAssisted by: " + getAssistingPlayer();
    }
}
