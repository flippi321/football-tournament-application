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
     * Mutator method to alter the timestamp of this event
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

    /**
     *
     * @return GameEvent value
     */
    public abstract GameEvent getEvent();

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

    @Override
    public String toString() {
        return "GameEvent{" +
                "player=" + player +
                ", team=" + team +
                '}';
    }
}
