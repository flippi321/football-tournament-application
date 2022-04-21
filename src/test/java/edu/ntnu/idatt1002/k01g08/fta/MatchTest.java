package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.*;
import org.junit.jupiter.api.*;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Match test")
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
    @DisplayName("Constructor tests")
    public class ConstructorTests {
        @Test
        @DisplayName("Empty team throws exception")
        public void emptyTeamsThrowsException() {
            Team team1 = new Team("Odd");
            Team team2 = new Team("München");
            assertThrows(IllegalArgumentException.class, () -> new Match(team1, team2));
        }

        @Test
        @DisplayName("Different teams does not throw exception")
        public void differentTeamsDoesNotThrowException() {
            assertDoesNotThrow(() -> new Match(team1, team2));
        }

        @Test
        @DisplayName("Equal teams throws exception")
        public void equalTeamsThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> new Match(team1, team1));
        }

        @Test
        @DisplayName("Null team throws exception")
        public void nullTeamThrowsException() {
            assertThrows(NullPointerException.class, () -> new Match(null, team1));
            assertThrows(NullPointerException.class, () -> new Match(team1, null));
        }
/*
        @Disabled
        @Test
        @DisplayName("Too small team throws exception")
        public void tooSmallTeamThrowsException() {
            team1.removePlayer(1);
            assertThrows(IllegalArgumentException.class, () -> new Match(team1, team2));
            assertThrows(IllegalArgumentException.class, () -> new Match(team2, team1));
        }*/

        @Test
        @DisplayName("Empty constructor does not throw exception")
        public void emptyConstructorDoesNotThrowException() {
            assertDoesNotThrow(()->new Match());
        }
    }

    @Nested
    @DisplayName("Accessor tests")
    public class AccessorTests {
        @Test
        @DisplayName("Match.isStarted() test")
        public void matchIsStartedTest() {
            assertFalse(match.isStarted());
            match.start();
            assertTrue(match.isStarted());
            match.end();
            assertTrue(match.isStarted());
        }

        @Test
        @DisplayName("Match.isFinished() test")
        public void matchIsFinishedTest() {
            assertFalse(match.isFinished());
            match.start();
            assertFalse(match.isFinished());
            match.end();
            assertTrue(match.isFinished());
        }

        @Test
        @DisplayName("getHomeTeam() returns correct team")
        public void getHomeTeamReturnsCorrectTeam() {
            assertEquals(team1, match.getHomeTeam());
        }

        @Test
        @DisplayName("getAwayTeam() returns correct team")
        public void getAwayTeamReturnsCorrectTeam() {
            assertEquals(team2, match.getAwayTeam());
        }

        @Test
        @DisplayName("Getting empty team returns null")
        public void emptyTeamReturnsNull() {
            Match match = new Match();
            assertNull(match.getHomeTeam());
            assertNull(match.getAwayTeam());
        }

        @Test
        @DisplayName("getGameEvent gets right event")
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
        @DisplayName("getLastGameEvent gets right event")
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
    @DisplayName("Mutator tests")
    public class MutatorTests {
        @Test
        @DisplayName("Home team is set")
        public void homeTeamIsSet() {
            Match match = new Match();
            match.setHomeTeam(team1);
            assertEquals(team1, match.getHomeTeam());
        }

        @Test
        @DisplayName("Away team is set")
        public void awayTeamIsSet() {
            Match match = new Match();
            match.setAwayTeam(team1);
            assertEquals(team1, match.getAwayTeam());
        }

        @Test
        @DisplayName("Setting team after match start throws exception")
        public void settingTeamAfterStartThrowsException() {
            match.start();
            assertThrows(IllegalStateException.class, ()->match.setHomeTeam(team3));
            assertThrows(IllegalStateException.class, ()->match.setAwayTeam(team4));
        }

        @Test
        @DisplayName("Setter overrides existing team")
        public void setterOverridesExisting() {
            match.setHomeTeam(team3);
            match.setAwayTeam(team4);

            assertEquals(team3, match.getHomeTeam());
            assertEquals(team4, match.getAwayTeam());
        }

        @Test
        @DisplayName("Setting equal teams throws exception")
        public void settingEqualTeamsThrowsException() {
            assertThrows(IllegalArgumentException.class, ()->match.setAwayTeam(team1));
            assertThrows(IllegalArgumentException.class, ()->match.setHomeTeam(team2));
        }

        @Test
        @DisplayName("Setting null team throws exception")
        public void settingNullTeamThrowsException() {
            assertThrows(NullPointerException.class, () -> new Match(null, team1));
            assertThrows(NullPointerException.class, () -> new Match(team1, null));
        }
    }

    @Nested
    @DisplayName("Match result tests")
    public class MatchResultTests {
        @Test
        @DisplayName("Home team score is correct")
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
        @DisplayName("Away team score is correct")
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
        @DisplayName("Match winner is correct")
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
    @DisplayName("Match control tests")
    public class MatchControlTests {
        @Test
        @DisplayName("Match without teams does not start")
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
        @DisplayName("Match with teams starts")
        public void filledMatchStarts() {
            assertTrue(match.start());
        }

        @Test
        @DisplayName("pause gives correct feedback")
        public void pauseGivesCorrectFeedback() {
            assertFalse(match.pause());
            match.start();
            assertTrue(match.pause());
            assertFalse(match.pause());
        }
    }

    @Nested
    @DisplayName("Match history tests")
    public class MatchHistoryTests {

        @Test
        @DisplayName("Adding event before start throws exception")
        public void addingEventBeforeStartThrowsException() {
            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            GameEvent event = new Goal(player1, team1, "20", player2);
            assertThrows(IllegalStateException.class, ()->match.addGameEvent(event));
        }

        @Test
        @DisplayName("Adding event after start does not throw exception")
        public void addingEventAfterStartDoesNotThrowException() {
            Player player1 = new Player("Gunnar", 30);
            Player player2 = new Player("Nordstoga", 32);
            match.start();
            GameEvent event = new Goal(player1, team1, "20", player2);
            assertDoesNotThrow(()->match.addGameEvent(event));
        }

        @Test
        @DisplayName("Right event is removed")
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
        @DisplayName("No argument removeLastEvent removes very last")
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
        @DisplayName("remove returns right event")
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
        @DisplayName("Goal tests")
        public class GoalTests {
            @Test
            @DisplayName("Goal is added")
            public void goalIsAdded () {
                match.start();
                match.addGoal(true, 2, 3, null);
                Goal goal = (Goal) match.getGameEvent(0);
                assertEquals(team1.getPlayer(2), goal.getPlayer());
                assertEquals(team1.getPlayer(3), goal.getAssistingPlayer());
                assertEquals(team1, goal.getTeam());
            }

            @Test
            @DisplayName("Nonexistent scorer gives exception")
            public void nonexistentScorerGivesException () {
                match.start();
                assertThrows(NullPointerException.class,
                        () -> match.addGoal(false, 12, 3, null));
            }

            @Test
            @DisplayName("Assisting player can be null")
            public void assistingPlayerCanBeNull () {
                match.start();
                assertDoesNotThrow(() -> match.addGoal(false, 2, -1, null));
            }

            @Test
            @DisplayName("Self goal is added")
            public void selfGoalIsAdded () {
                match.start();
                match.addSelfGoal(true, 4, null);
                Goal goal = (Goal) match.getGameEvent(0);
                assertEquals(team1.getPlayer(4), goal.getPlayer());
                assertEquals(team2, goal.getTeam());
            }

            @Test
            @DisplayName("Null team throws exception")
            public void nullTeamGivesException() {
                match.start();
                assertThrows(NullPointerException.class, () -> match.addGoal(null, team1.getPlayer(2), team1.getPlayer(3), null));
            }

            @Test
            @DisplayName("Timestamp is added")
            public void timeStampIsAdded() {
                match.start();
                match.addSelfGoal(true, 4, "24");
                assertEquals("24", match.getLastGameEvent(0).getTimeStampOfMatchTime());
            }

            @Test
            @DisplayName("Blank timestamp uses current match time")
            public void blankTimeStampUsesCurrent() {
                match.start();
                match.addSelfGoal(true, 4, null);
                match.addSelfGoal(true, 4, "");
                assertEquals("01", match.getLastGameEvent(0).getTimeStampOfMatchTime());
                assertEquals("01", match.getLastGameEvent(1).getTimeStampOfMatchTime());
            }
        }

        @Nested
        @DisplayName("Substitution tests")
        public class SubstitutionTests {
            @Test
            @DisplayName("Substitution is added")
            public void substitutionIsAdded() {
                match.start();
                match.addSubstitution(false, 4, 5, null);
                Substitution sub = (Substitution) match.getGameEvent(0);
                assertEquals(team2.getPlayer(4), sub.getPlayerIn());
                assertEquals(team2.getPlayer(5), sub.getPlayerOut());
            }

            @Test
            @DisplayName("Timestamp is added")
            public void timeStampIsAdded() {
                match.start();
                match.addSubstitution(false, 4, 5, "37");
                assertEquals("37", match.getLastGameEvent(0).getTimeStampOfMatchTime());
            }

            @Test
            @DisplayName("Blank timestamp uses current match time")
            public void blankTimeStampUsesCurrent() {
                match.start();
                match.addSubstitution(false, 4, 5, null);
                match.addSubstitution(false, 4, 5, "");
                assertEquals("01", match.getLastGameEvent(0).getTimeStampOfMatchTime());
                assertEquals("01", match.getLastGameEvent(1).getTimeStampOfMatchTime());
            }
        }

        @Nested
        @DisplayName("Foul tests")
        public class FoulTests {
            @Test
            @DisplayName("Foul is added")
            public void foulIsAdded() {
                match.start();
                match.addFoul(true, 7, "", 2, null);
                Foul foul = (Foul) match.getGameEvent(0);
                assertEquals(team1.getPlayer(7), foul.getPlayer());
            }

            @Test
            @DisplayName("Time stamp is added")
            public void timeStampIsAdded() {
                match.start();
                match.addFoul(true, 7, "", 2, "30");
                assertEquals("30", match.getLastGameEvent(0).getTimeStampOfMatchTime());
            }

            @Test
            @DisplayName("Blank timestamp uses current match time")
            public void blankTimeStampUsesCurrent() {
                match.start();
                match.addFoul(true, 7, "", 2, null);
                match.addFoul(true, 7, "", 2, "");
                assertEquals("01", match.getLastGameEvent(0).getTimeStampOfMatchTime());
                assertEquals("01", match.getLastGameEvent(1).getTimeStampOfMatchTime());
            }
        }
    }

    @Test
    @DisplayName("Iterator returns right events")
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
    @DisplayName("Stream is not null")
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