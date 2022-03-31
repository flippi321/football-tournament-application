package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Team test")
public class TeamTest {
    @Test
    @DisplayName("Team name is correct")
    public void teamNameIsCorrect() {
        Team team = new Team("Odd");
        assertEquals("Odd", team.getName());
    }

    @Test
    @DisplayName("Player is added")
    public void playerIsAdded() {
        Team team = new Team("Odd");
        Player player = new Player("Odd Nordstoga", 1);

        assertNull(team.getPlayer(1));

        team.addPlayer(player);

        assertEquals(player, team.getPlayer(1));
    }

    @Test
    @DisplayName("Get returns correct player")
    public void getReturnsCorrectPlayer() {
        Team team = new Team("Odd");
        Player player1 = new Player("Sondre Rossbach", 1);
        Player player2 = new Player("Espen Ruud", 2);
        Player player3 = new Player("Josef Brian Baccay", 3);
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);

        assertEquals(player3, team.getPlayer(3));
        assertEquals(player2, team.getPlayer(2));
        assertEquals(player1, team.getPlayer(1));
    }

    @Test
    @DisplayName("Duplicate player is not added")
    public void duplicatePlayerIsNotAdded() {
        Team team = new Team("Odd");
        Player player1 = new Player("Odd Nordstoga", 1);
        Player player2 = new Player("Hvem veit", 1);
        team.addPlayer(player1);
        team.addPlayer(player2);

        assertEquals(player1, team.getPlayer(1));
        assertEquals(1, team.size());
    }

    @Test
    @DisplayName("Add gives correct feedback")
    public void addGivesCorrectFeedback() {
        Team team = new Team("Odd");
        Player player1 = new Player("Odd Nordstoga", 2);
        Player player2 = new Player("Hvem veit", 2);
        assertTrue(team.addPlayer(player1));
        assertFalse(team.addPlayer(player2));
    }

    @Test
    @DisplayName("Null player is not added")
    public void nullPlayerIsNotAdded() {
        Team team = new Team("Odd");
        assertThrows(NullPointerException.class, () -> team.addPlayer(null));
    }

    @Test
    @DisplayName("Player is removed")
    public void playerIsRemoved() {
        Team team = new Team("Odd");
        Player player1 = new Player("Odd Nerdrum", 1);
        Player player2 = new Player("Odd Nordstoga", 2);
        team.addPlayer(player1);
        team.addPlayer(player2);

        assertEquals(player1, team.getPlayer(1));
        assertEquals(player2, team.getPlayer(2));

        assertTrue(team.removePlayer(1));
        assertTrue(team.removePlayer(player2));

        assertNull(team.getPlayer(1));
        assertNull(team.getPlayer(2));
        assertEquals(0, team.size());
    }

    @Test
    @DisplayName("Nonexistent player is not removed")
    public void nonexistentPlayerIsNotRemoved() {
        Team team = new Team("Odd");
        Player player1 = new Player("Odd Nerdrum", 1);
        Player player2 = new Player("Odd Nordstoga", 2);

        assertTrue(team.addPlayer(player1));
        assertFalse(team.removePlayer(3));
        assertFalse(team.removePlayer(player2));
        assertFalse(team.removePlayer(null));
        assertEquals(1, team.size());
    }

    @Test
    @DisplayName("Iterator returns all players")
    public void iteratorReturnsAllPlayers() {
        Team team = new Team("Odd");
        Set<Player> set = new HashSet<>();
        Player player1 = new Player("Sondre Rossbach", 1);
        Player player2 = new Player("Espen Ruud", 2);
        Player player3 = new Player("Josef Brian Baccay", 3);

        team.addPlayer(player1);
        set.add(player1);
        team.addPlayer(player2);
        set.add(player2);
        team.addPlayer(player3);
        set.add(player3);

        for (Player player : team) {
            assertTrue(set.remove(player));
        }
    }
}
