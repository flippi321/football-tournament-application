package edu.ntnu.idatt1002.k01g08.fta.objects;

import java.util.*;

/**
 * Represents a team of football players
 * @author bendikme
 * @version 2022-03-21
 */
public class Team implements Iterable<Player> {
    final private String name;
    final private Map<Integer, Player> players;

    /**
     * Creates a team instance, with the given name.
     * @param name the name of the team
     */
    public Team(String name) {
        this.name = name;
        this.players = new HashMap<>();
    }

    /**
     * Returns the name of this team.
     * @return the name of this team
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the player with the given squad number. If no player has the number, returns null.
     * @param number the squad number of the player
     * @return the player with the given squad number, or null
     */
    public Player getPlayer(int number) {
        return players.get(number);
    }

    /**
     * Returns the number of players on this team.
     * @return the number of players on this team
     */
    public int size() {
        return players.size();
    }

    /**
     * Adds the given player to this team, if its squad number is not shared by any player already present.
     * Does not permit null.
     * Returns true if the player was added, and false if not.
     * @param player a player to be added to this team
     * @return true if the player was added, false if not
     */
    public boolean addPlayer(Player player) {
        if (player == null) return false;
        return players.putIfAbsent(player.getNumber(), player) == null;
    }

    /**
     * Tries to remove the given player from this team. Returns true if the player was present, and false if not.
     * @param player a player to be removed from this team
     * @return true if a player was removed, false if not
     */
    public boolean removePlayer(Player player) {
        return (player != null) && players.remove(player.getNumber(), player);
    }

    /**
     * Tries to remove the player with the given squad number from this tram.
     * Returns true if a player with that squad number was present, and false if not.
     * @param number the squad number of the player to be removed from this team
     * @return true if a player was removed, false if not
     */
    public boolean removePlayer(int number) {
        return (players.remove(number) != null);
    }

    /**
     * Returns an iterator over the players in this team.
     * @return an iterator over the players in this team.
     */
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
