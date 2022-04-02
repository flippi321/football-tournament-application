package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class MatchTest {
    Team team1;
    Team team2;
    Team team3;
    Team team4;
    Match match;

    @BeforeEach
    public void setUp() {
        team1 = new Team("Odd");
        team2 = new Team("München");
        team3 = new Team("Bayern");
        team4 = new Team("Pors");
        for (int i = 1; i < 12; i++) {
            team1.addPlayer(new Player("Oddær nr. " + i, i));
            team2.addPlayer(new Player("Baron von " + i, i));
            team3.addPlayer(new Player("Spiller " + i, i));
            team4.addPlayer(new Player("\"Person\" " + i, i));
        }
        match = new Match(team1, team2);
    }

    @Nested
    public class ConstructorTests {
        @Test
        public void emptyTeamsThrowsException() {
            Team team1 = new Team("Odd");
            Team team2 = new Team("München");
            assertThrows(IllegalArgumentException.class, () -> new Match(team1, team2));
        }

        @Test
        public void differentTeamsDoesNotThrowException() {
            assertDoesNotThrow(() -> new Match(team1, team2));
        }

        @Test
        public void equalTeamsThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> new Match(team1, team1));
        }

        @Test
        public void nullTeamThrowsException() {
            assertThrows(NullPointerException.class, () -> new Match(null, team1));
            assertThrows(NullPointerException.class, () -> new Match(team1, null));
        }

        @Test
        public void tooSmallTeamThrowsException() {
            team1.removePlayer(1);
            assertThrows(IllegalArgumentException.class, () -> new Match(team1, team2));
            assertThrows(IllegalArgumentException.class, () -> new Match(team2, team1));
        }

        @Test
        public void emptyConstructorDoesNotThrowException() {
            assertDoesNotThrow(()->new Match());
        }
    }

    @Nested
    public class AccessorTests {
        @Test
        public void matchIsStartedTest() {
            assertFalse(match.isStarted());
            match.start();
            assertTrue(match.isStarted());
            match.end();
            assertTrue(match.isStarted());
        }

        @Test
        public void matchIsFinishedTest() {
            assertFalse(match.isFinished());
            match.start();
            assertFalse(match.isFinished());
            match.end();
            assertTrue(match.isFinished());
        }

        @Test
        public void getHomeTeamReturnsCorrectTeam() {
            assertEquals(team1, match.getHomeTeam());
        }

        @Test
        public void getAwayTeamReturnsCorrectTeam() {
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
            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            match.start();
            GameEvent event1 = new Goal(player1, team1, "20", player2);
            GameEvent event2 = new Goal(player2, team2, "25", player1);
            match.addGameEvent(event1);
            match.addGameEvent(event2);

            assertEquals(event1, match.getGameEvent(0));
            assertEquals(event2, match.getGameEvent(1));
        }

        @Test
        public void getLastGameEventGetsRightEvent() {

            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            match.start();
            GameEvent event1 = new Goal(player1, team1, "20", player2);
            GameEvent event2 = new Goal(player2, team2, "25", player1);
            match.addGameEvent(event1);
            match.addGameEvent(event2);

            assertEquals(event2, match.getLastGameEvent(0));
            assertEquals(event1, match.getLastGameEvent(1));
        }
    }

    @Nested
    public class MutatorTests {
        @Test
        public void homeTeamIsSet() {
            Match match = new Match();
            match.setHomeTeam(team1);
            assertEquals(team1, match.getHomeTeam());
        }

        @Test
        public void awayTeamIsSet() {
            Match match = new Match();
            match.setAwayTeam(team1);
            assertEquals(team1, match.getAwayTeam());
        }

        @Test
        public void settingTeamAfterStartThrowsException() {
            match.start();
            assertThrows(IllegalStateException.class, ()->match.setHomeTeam(team3));
            assertThrows(IllegalStateException.class, ()->match.setAwayTeam(team4));
        }

        @Test
        public void setterOverridesExisting() {
            match.setHomeTeam(team3);
            match.setAwayTeam(team4);

            assertEquals(team3, match.getHomeTeam());
            assertEquals(team4, match.getAwayTeam());
        }

        @Test
        public void settingEqualTeamsThrowsException() {
            assertThrows(IllegalArgumentException.class, ()->match.setAwayTeam(team1));
            assertThrows(IllegalArgumentException.class, ()->match.setHomeTeam(team2));
        }

        @Test
        public void settingNullTeamThrowsException() {
            assertThrows(NullPointerException.class, () -> new Match(null, team1));
            assertThrows(NullPointerException.class, () -> new Match(team1, null));
        }
    }

    @Nested
    public class MatchResultTests {
        @Test
        public void getsRightHomeTeamScore() {

            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            match.start();
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

            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            match.start();
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
            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            match.start();
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

            match = new Match(team2, team1);
            match.start();
            match.addGameEvent(event1);
            match.addGameEvent(event2);
            match.addGameEvent(event3);
            match.addGameEvent(event4);
            assertEquals(team1, match.end());
        }
    }

    @Nested
    public class MatchControlTests {
        @Test
        public void emptyMatchDoesNotStart() {
            Match match = new Match();
            assertFalse(match.start());
            assertFalse(match.isStarted());

            match.setHomeTeam(team1);
            assertFalse(match.start());
            assertFalse(match.isStarted());

            match = new Match();
            match.setAwayTeam(team1);
            assertFalse(match.start());
            assertFalse(match.isStarted());
        }

        @Test
        public void filledMatchStarts() {
            assertTrue(match.start());
        }

        @Test
        public void matchDoesNotEndBeforeStart() {
            Match match = new Match();
            match.start();
            assertThrows(IllegalStateException.class, match::end);
        }

        @Test
        public void pauseGivesCorrectFeedback() {
            assertFalse(match.pause());
            match.start();
            assertTrue(match.pause());
            assertFalse(match.pause());
        }
    }

    @Nested
    public class MatchHistoryTests {

        @Test
        public void addingEventBeforeStartThrowsException() {
            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            GameEvent event = new Goal(player1, team1, "20", player2);
            assertThrows(IllegalStateException.class, ()->match.addGameEvent(event));
        }

        @Test
        public void addingEventAfterStartDoesNotThrowException() {
            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            match.start();
            GameEvent event = new Goal(player1, team1, "20", player2);
            assertDoesNotThrow(()->match.addGameEvent(event));
        }

        @Test
        public void removesRightEvent() {
            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            match.start();
            GameEvent event1 = new Goal(player1, team1, "20", player2);
            GameEvent event2 = new Goal(player2, team2, "25", player1);
            match.addGameEvent(event1);
            match.addGameEvent(event2);

            match.removeGameEvent(0);
            assertEquals(event2, match.getGameEvent(0));

            match.addGameEvent(event1);
            match.removeLastGameEvent(1);
            assertEquals(event1, match.getGameEvent(0));
        }

        @Test
        public void noArgumentRemoveLastRemovesVeryLast() {
            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            match.start();
            GameEvent event1 = new Goal(player1, team1, "20", player2);
            GameEvent event2 = new Goal(player2, team2, "25", player1);
            match.addGameEvent(event1);
            match.addGameEvent(event2);

            assertEquals(event2, match.removeLastGameEvent());
            assertEquals(event1, match.getGameEvent(0));
        }

        @Test
        public void removeReturnsRightEvent() {
            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            match.start();
            GameEvent event1 = new Goal(player1, team1, "20", player2);
            GameEvent event2 = new Goal(player2, team2, "25", player1);
            match.addGameEvent(event1);
            match.addGameEvent(event2);

            assertEquals(event1, match.removeGameEvent(0));

            match.addGameEvent(event1);
            assertEquals(event1, match.removeLastGameEvent(0));
        }

        @Nested
        public class GoalTests {
            @Test
            public void goalIsAdded () {
                match.start();
                match.addGoal(true, 2, 3, null);
                Goal goal = (Goal) match.getGameEvent(0);
                assertEquals(team1.getPlayer(2), goal.getPlayer());
                assertEquals(team1.getPlayer(3), goal.getAssistingPlayer());
                assertEquals(team1, goal.getTeam());
            }

            @Test
            public void nonexistentScorerGivesException () {
                match.start();
                assertThrows(NullPointerException.class,
                        () -> match.addGoal(false, 12, 3, null));
            }

            @Test
            public void assistingPlayerCanBeNull () {
                match.start();
                assertDoesNotThrow(() -> match.addGoal(false, 2, -1, null));
            }

            @Test
            public void selfGoalIsAdded () {
                match.start();
                match.addSelfGoal(true, 4, null);
                Goal goal = (Goal) match.getGameEvent(0);
                assertEquals(team1.getPlayer(4), goal.getPlayer());
                assertEquals(team2, goal.getTeam());
            }

            @Test
            public void nullTeamGivesException() {
                match.start();
                assertThrows(NullPointerException.class, () -> match.addGoal(null, team1.getPlayer(2), team1.getPlayer(3), null));
            }

            @Test
            public void timeStampIsAdded() {
                match.start();
                match.addSelfGoal(true, 4, "24");
                assertEquals("24", match.getLastGameEvent(0).getTimeStampOfMatchTime());
            }

            @Test
            public void blankTimeStampUsesCurrent() {
                match.start();
                match.addSelfGoal(true, 4, null);
                match.addSelfGoal(true, 4, "");
                assertEquals("1", match.getLastGameEvent(0).getTimeStampOfMatchTime());
                assertEquals("1", match.getLastGameEvent(1).getTimeStampOfMatchTime());
            }
        }

        @Nested
        public class SubstitutionTests {
            @Test
            public void substitutionIsAdded() {
                match.start();
                match.addSubstitution(false, 4, 5, null);
                Substitution sub = (Substitution) match.getGameEvent(0);
                assertEquals(team2.getPlayer(4), sub.getPlayerIn());
                assertEquals(team2.getPlayer(5), sub.getPlayerOut());
            }

            @Test
            public void timeStampIsAdded() {
                match.start();
                match.addSubstitution(false, 4, 5, "37");
                assertEquals("37", match.getLastGameEvent(0).getTimeStampOfMatchTime());
            }

            @Test
            public void blankTimeStampUsesCurrent() {
                match.start();
                match.addSubstitution(false, 4, 5, null);
                match.addSubstitution(false, 4, 5, "");
                assertEquals("1", match.getLastGameEvent(0).getTimeStampOfMatchTime());
                assertEquals("1", match.getLastGameEvent(1).getTimeStampOfMatchTime());
            }
        }

        @Nested
        public class FoulTests {
            @Test
            public void foulIsAdded() {
                match.start();
                match.addFoul(true, 7, "", 2, null);
                Foul foul = (Foul) match.getGameEvent(0);
                assertEquals(team1.getPlayer(7), foul.getPlayer());
            }

            @Test
            public void timeStampIsAdded() {
                match.start();
                match.addFoul(true, 7, "", 2, "30");
                assertEquals("30", match.getLastGameEvent(0).getTimeStampOfMatchTime());
            }

            @Test
            public void blankTimeStampUsesCurrent() {
                match.start();
                match.addFoul(true, 7, "", 2, null);
                match.addFoul(true, 7, "", 2, "");
                assertEquals("1", match.getLastGameEvent(0).getTimeStampOfMatchTime());
                assertEquals("1", match.getLastGameEvent(1).getTimeStampOfMatchTime());
            }
        }
    }

    @Test
    public void iteratorReturnsRightEvents() {
        Player player1 = new Player("Gunnar", 30);
        Player player2 = new Player("Nordstoga", 32);
        match.start();
        GameEvent event1 = new Goal(player1, team1, "20", player2);
        GameEvent event2 = new Goal(player2, team2, "25", player1);
        GameEvent event3 = new Substitution("26", team2, player1, player2);
        GameEvent event4 = new Goal(player1, team1, "30", player2);
        match.addGameEvent(event1);
        match.addGameEvent(event2);
        match.addGameEvent(event3);
        match.addGameEvent(event4);

        Iterator<GameEvent> it = match.iterator();
        assertTrue(it.hasNext());
        assertEquals(event1, it.next());
        assertEquals(event2, it.next());
        assertEquals(event3, it.next());
        assertEquals(event4, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void streamIsNotNull() {
        Player player1 = new Player("Gunnar", 30);
        Player player2 = new Player("Nordstoga", 32);
        match.start();
        GameEvent event1 = new Goal(player1, team1, "20", player2);
        GameEvent event2 = new Goal(player2, team2, "25", player1);
        GameEvent event3 = new Substitution("26", team2, player1, player2);
        GameEvent event4 = new Goal(player1, team1, "30", player2);
        match.addGameEvent(event1);
        match.addGameEvent(event2);
        match.addGameEvent(event3);
        match.addGameEvent(event4);

        assertNotNull(match.eventStream());
    }
}