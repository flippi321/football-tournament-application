package edu.ntnu.idatt1002.k01g08.fta.objects;

import java.util.*;

public class Team implements Iterable<Player> {
    final private String name;
    final private Map<Integer, Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Player getPlayer(int number) {
        return players.get(number);
    }

    public boolean addPlayer(Player player) {
        if (player == null || players.containsKey(player.getNumber())) {
            return false;
        }
        players.put(player.getNumber(), player);
        return true;
    }

    public boolean removePlayer(Player player) {
        return players.remove(player.getNumber(), player);
    }

    public boolean removePlayer(int number) {
        return (players.remove(number) != null);
    }

    @Override
    public Iterator<Player> iterator() {
        return new Iterator<>() {
            final Iterator<Integer> keyIt = players.keySet().iterator();

            @Override
            public boolean hasNext() {
                return keyIt.hasNext();
            }

            @Override
            public Player next() {
                return getPlayer(keyIt.next());
            }
        };
    }
}
