package edu.ntnu.idatt1002.k01g08.fta.objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Match implements Iterable<GameEvent> {
    final private Team homeTeam;
    final private Team awayTeam;
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
