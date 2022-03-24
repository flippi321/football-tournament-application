package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatchTest {
    @Nested
    class ConstructorTests {
        @Test
        public void differentTeamsDoesNotThrowException() {
            Team team1 = new Team("Odd");
            Team team2 = new Team("München");
            assertDoesNotThrow(() -> new Match(team1, team2));
        }

        @Test
        public void equalTeamsThrowsException() {
            Team team = new Team("Arsenal");
            assertThrows(IllegalArgumentException.class, () -> new Match(team, team));
        }

        @Test
        public void emptyConstructorDoesNotThrowException() {
            assertDoesNotThrow(()->new Match());
        }
    }

    @Nested
    class GetterTests {
        @Test
        public void getHomeTeamReturnsCorrectTeam() {
            Team team1 = new Team("Odd");
            Team team2 = new Team("München");
            Match match = new Match(team1, team2);
            assertEquals(team1, match.getHomeTeam());
        }

        @Test
        public void getAwayTeamReturnsCorrectTeam() {
            Team team1 = new Team("Odd");
            Team team2 = new Team("München");
            Match match = new Match(team1, team2);
            assertEquals(team2, match.getAwayTeam());
        }

        @Test
        public void emptyTeamReturnsNull() {
            Match match = new Match();
            assertNull(match.getHomeTeam());
            assertNull(match.getAwayTeam());
        }

        @Test
        public void getGameEventGetsRightEvent() {
            Team team1 = new Team("Odd");
            Team team2 = new Team("München");
            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            Match match = new Match(team1, team2);
            GameEvent event1 = new Goal(player1, team1, "20", player2);
            GameEvent event2 = new Goal(player2, team2, "25", player1);
            match.addGameEvent(event1);
            match.addGameEvent(event2);

            assertEquals(event1, match.getGameEvent(0));
            assertEquals(event2, match.getGameEvent(1));
        }

        @Test
        public void getLastGameEventGetsRightEvent() {

            Team team1 = new Team("Odd");
            Team team2 = new Team("München");
            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            Match match = new Match(team1, team2);
            GameEvent event1 = new Goal(player1, team1, "20", player2);
            GameEvent event2 = new Goal(player2, team2, "25", player1);
            match.addGameEvent(event1);
            match.addGameEvent(event2);

            assertEquals(event2, match.getLastGameEvent(0));
            assertEquals(event1, match.getLastGameEvent(1));
        }
    }

    @Nested
    class MutatorTests {
        @Test
        public void homeTeamIsSet() {
            Team team = new Team("Odd");
            Match match = new Match();
            match.setHomeTeam(team);
            assertEquals(team, match.getHomeTeam());
        }

        @Test
        public void awayTeamIsSet() {
            Team team = new Team("Odd");
            Match match = new Match();
            match.setAwayTeam(team);
            assertEquals(team, match.getAwayTeam());
        }

        @Test
        public void setterOverridesExisting() {
            Team team1 = new Team("Bayern");
            Team team2 = new Team("München");
            Team team3 = new Team("Odd");
            Team team4 = new Team("Pors");
            Match match = new Match(team1, team2);
            match.setHomeTeam(team3);
            match.setAwayTeam(team4);

            assertEquals(team3, match.getHomeTeam());
            assertEquals(team4, match.getAwayTeam());
        }

        @Test
        public void settingEqualTeamsThrowsException() {
            Team team1 = new Team("Odd");
            Team team2 = new Team("München");
            Match match = new Match(team1, team2);
            assertThrows(IllegalArgumentException.class, ()->match.setAwayTeam(team1));
            assertThrows(IllegalArgumentException.class, ()->match.setHomeTeam(team2));
        }

        @Test
        public void addingEventDoesNotThrowException() {
            Team team1 = new Team("Odd");
            Team team2 = new Team("München");
            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            Match match = new Match(team1, team2);
            GameEvent event = new Goal(player1, team1, "20", player2);
            assertDoesNotThrow(()->match.addGameEvent(event));
        }
    }

    @Nested
    class MatchResultTests {
        @Test
        public void getsRightHomeTeamScore() {

            Team team1 = new Team("Odd");
            Team team2 = new Team("München");
            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            Match match = new Match(team1, team2);
            GameEvent event1 = new Goal(player1, team1, "20", player2);
            GameEvent event2 = new Goal(player2, team2, "25", player1);
            GameEvent event3 = new Substitution("26", team2, player1, player2);
            GameEvent event4 = new Goal(player1, team1, "30", player2);
            match.addGameEvent(event1);
            match.addGameEvent(event2);
            match.addGameEvent(event3);
            match.addGameEvent(event4);

            assertEquals(2, match.getHomeTeamScore());
        }

        @Test
        public void getsRightAwayTeamScore() {

            Team team1 = new Team("Odd");
            Team team2 = new Team("München");
            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            Match match = new Match(team1, team2);
            GameEvent event1 = new Goal(player1, team1, "20", player2);
            GameEvent event2 = new Goal(player2, team2, "25", player1);
            GameEvent event3 = new Substitution("26", team2, player1, player2);
            GameEvent event4 = new Goal(player1, team1, "30", player2);
            match.addGameEvent(event1);
            match.addGameEvent(event2);
            match.addGameEvent(event3);
            match.addGameEvent(event4);

            assertEquals(1, match.getAwayTeamScore());
        }

        @Test
        public void getsRightWinner() {
            Team team1 = new Team("Odd");
            Team team2 = new Team("München");
            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            Match match = new Match(team1, team2);
            GameEvent event1 = new Goal(player1, team1, "20", player2);
            GameEvent event2 = new Goal(player2, team2, "25", player1);
            GameEvent event3 = new Substitution("26", team2, player1, player2);
            GameEvent event4 = new Goal(player1, team1, "30", player2);
            match.addGameEvent(event1);
            match.addGameEvent(event2);
            match.addGameEvent(event3);
            match.addGameEvent(event4);

            assertNull(match.getWinner());
            assertEquals(team1, match.end());
            assertEquals(team1, match.getWinner());
        }
    }
}
