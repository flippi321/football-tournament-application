package edu.ntnu.idatt1002.k01g08.fta.objects;

public abstract class GameEvent {
    private Player player;
    private Team team;

    public GameEvent(Player player, Team team) {
        this.player = player;
        this.team = team;
    }

    public abstract int getAction();

    @Override
    public String toString() {
        return "GameEvent{" +
                "player=" + player +
                ", team=" + team +
                '}';
    }
}
