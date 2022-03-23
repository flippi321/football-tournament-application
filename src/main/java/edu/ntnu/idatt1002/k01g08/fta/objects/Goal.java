package edu.ntnu.idatt1002.k01g08.fta.objects;

public class Goal extends GameEvent {
    private Player assistingPlayer;

    public Goal(Player player, Team team, String timeStamp, Player assistingPlayer) throws IllegalArgumentException {
        super(player, team, timeStamp);
        this.assistingPlayer = assistingPlayer;
    }

    public Goal(Player player, Team team, String timeStamp) throws IllegalArgumentException {
        super(player, team, timeStamp);
    }

    @Override
    public String getEvent() {
        return "Goal{" +
                "assistingPlayer=" + assistingPlayer +
                '}';
    }
}
