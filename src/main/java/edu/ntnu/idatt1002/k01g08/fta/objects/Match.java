package edu.ntnu.idatt1002.k01g08.fta.objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Match implements Iterable<GameEvent> {
    private Team homeTeam;
    private Team awayTeam;
    private boolean finished = false;
    final private List<GameEvent> matchHistory;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchHistory = new ArrayList<>();
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Team getWinner() {
        if (finished) return awayTeam;
        return null;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public GameEvent getGameEvent(int i) {
        return matchHistory.get(i);
    }

    @Override
    public Iterator<GameEvent> iterator() {
        return matchHistory.iterator();
    }

    public void addGameEvent(GameEvent gameEvent) {
        matchHistory.add(gameEvent);
    }
}
