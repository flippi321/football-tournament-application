package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.team.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Player test")
public class PlayerTest {
    @Test
    @DisplayName("Statistics start at zero")
    public void statisticsStartAtZero() {
        Player player = new Player("Ole Gunnar Solskjær", 40);

        assertEquals(0, player.getAssists());
        assertEquals(0, player.getGoals());
        assertEquals(0, player.getRedCards());
        assertEquals(0, player.getYellowCards());
    }

    @Test
    @DisplayName("Player Statistics works")
    public void statisticsIncrement() {
        Player player = new Player("Ole Gunnar Solskjær", 40);

        player.increaseYellowCards();
        player.increaseYellowCards();
        player.increaseYellowCards();
        player.increaseYellowCards();
        player.increaseRedCards();
        player.increaseRedCards();
        player.increaseRedCards();
        player.increaseGoals();
        player.increaseGoals();
        player.increaseAssists();

        assertEquals(1, player.getAssists());
        assertEquals(2, player.getGoals());
        assertEquals(3, player.getRedCards());
        assertEquals(4, player.getYellowCards());
    }

    @Test
    @DisplayName("Statistics increase correctly")
    public void statisticsIncreaseCorrectly() {
        Player player = new Player("Ole Gunnar Solskjær", 40);

        player.increaseYellowCards(1);
        player.increaseRedCards(2);
        player.increaseGoals(3);
        player.increaseAssists(4);

        assertEquals(4, player.getAssists());
        assertEquals(3, player.getGoals());
        assertEquals(2, player.getRedCards());
        assertEquals(1, player.getYellowCards());
    }

    @Test
    @DisplayName("Get methods for name and number works")
    public void nameAndNumberAreCorrect() {
        Player player = new Player("Ole Gunnar Solskjær", 40);

        assertEquals("Ole Gunnar Solskjær", player.getName());
        assertEquals(40, player.getNumber());
    }

    @Test
    @DisplayName("toString works as intended")
    public void toStringTest() {
        Player player = new Player("Ole Gunnar Solskjær", 40);
        player.increaseRedCards(2);
        player.increaseYellowCards(1);
        player.increaseGoals(3);
        player.increaseAssists(4);
        assertEquals("(Ole Gunnar Solskjær 40: 2 red, 1 yellow, 3 goals, 4 assists)", player.toString());
    }
}