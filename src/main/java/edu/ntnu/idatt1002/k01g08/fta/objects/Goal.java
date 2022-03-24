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
     * @param player is a Player
     * @param team is a Team
     * @param timeStamp is a String
     * @param assistingPlayer is another Player. The one that assisted the goal
     * @throws IllegalArgumentException specified in the GameEvent class
     */
    public Goal(Player player, Team team, String timeStamp, Player assistingPlayer) throws IllegalArgumentException {
        super(player, team, timeStamp);
        this.assistingPlayer = assistingPlayer;
    }

    /**
     * Second constructor to instantiate a Goal without an assistingPlayer
     * @param player is a Player
     * @param team is a Team
     * @param timeStamp is String
     * @throws IllegalArgumentException specified in the GameEvent class
     */
    public Goal(Player player, Team team, String timeStamp) throws IllegalArgumentException {
        super(player, team, timeStamp);
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
    @Override
    public String getEvent() {
        if(assistingPlayer != null){
            return "The player: " + getPlayer().getName() + " scored a goal for team: " + getTeam() +
                    " with assist from player: " + assistingPlayer + "at time: " + getTimeStampOfMatchTime();
        }else{
            return "The player: " + getPlayer().getName() +
                    " scored a goal for team: " + getTeam() + "at time: " + getTimeStampOfMatchTime();
        }
    }

    @Override
    public String toString() {
        return "Goal{" +
                "assistingPlayer=" + assistingPlayer +
                '}';
    }
}
