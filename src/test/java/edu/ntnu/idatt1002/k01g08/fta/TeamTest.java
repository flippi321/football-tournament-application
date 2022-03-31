package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

//TODO: add DISPLAY NAME for tests
public class TeamTest {
    @Test
    public void teamNameIsCorrect() {
        Team team = new Team("Odd");
        assertEquals("Odd", team.getName());
    }

    @Test
    public void playerIsAdded() {
        Team team = new Team("Odd");
        Player player = new Player("Odd Nordstoga", 1);

        assertNull(team.getPlayer(1));

        team.addPlayer(player);

        assertEquals(player, team.getPlayer(1));
    }

    @Test
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
    public void duplicatePlayerIsNotAdded() {
        Team team = new Team("Odd");
        Player player1 = new Player("Odd Nordstoga", 1);
        Player player2 = new Player("Hvem veit", 1);
        team.addPlayer(player1);
        team.addPlayer(player2);

        assertEquals(player1, team.getPlayer(1));

        //TODO: add line beneath to actually check that the size of the team is 1
        // assertEquals(1, team.getTeamSize()); - - that uses the iterator
    }

    @Test
    public void addGivesCorrectFeedback() {
        Team team = new Team("Odd");
        Player player1 = new Player("Odd Nordstoga", 2);
        Player player2 = new Player("Hvem veit", 2);
        assertTrue(team.addPlayer(player1));
        assertFalse(team.addPlayer(player2));
    }

    @Test
    public void nullPlayerIsNotAdded() {
        Team team = new Team("Odd");
        assertThrows(NullPointerException.class, () -> team.addPlayer(null));
    }

    @Test
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
        //TODO: add size check for the final team-array
    }

    @Test
    public void nonexistentPlayerIsNotRemoved() {
        Team team = new Team("Odd");
        Player player1 = new Player("Odd Nerdrum", 1);
        Player player2 = new Player("Odd Nordstoga", 2);

        assertTrue(team.addPlayer(player1));
        assertFalse(team.removePlayer(3));
        assertFalse(team.removePlayer(player2));
        assertFalse(team.removePlayer(null));
        //TODO: add size check for the final team-array
    }

    @Test
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

    //TODO
    // Add @Displayname
}
