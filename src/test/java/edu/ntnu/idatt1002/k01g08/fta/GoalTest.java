package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Goal Tests")
public class GoalTest {
    @Nested
    @DisplayName("Positive Tests")
    public class PositiveTests {
        @Test
        @DisplayName("Constructor Works")
        public void initializeAGoalWithAllCorrectParameters() {
            try {
                Player testScorer = new Player("Johan Footman", 10);
                Player testAssistPlayer = new Player("Jo Assi", 11);
                Team testTeam = new Team("TestTeam");
                Goal testGoal = new Goal(testScorer, testTeam, "12:30", testAssistPlayer);
                assertEquals("Jo Assi", testGoal.getAssistingPlayer().getName());
            } catch (IllegalArgumentException e) {
                fail("'initializeAGoalWithAllCorrectParameters' failed");
            }
        }
        @Test
        @DisplayName("Constructor without player works")
        public void initializeAGoalWithParametersExceptAssistingPlayer() {
            try {
                Player testScorer = new Player("Johan Footman", 10);
                Team testTeam = new Team("TestTeam");
                Goal testGoal = new Goal(testScorer, testTeam, "12:30");
                assertNull(testGoal.getAssistingPlayer());
            } catch (IllegalArgumentException e) {
                fail("'initializeAGoalWithParametersExceptAssistingPlayer' failed");
            }
        }
    }
    @Nested
    @DisplayName("Negative Tests")
    public class NegativeTests {
        @Test
        @DisplayName("Exception thrown when timestamp is 'null'")
        public void initializeAGoalWithNullValueForTimestamp() {
            try {
                Player testScorer = new Player("Johan Footman", 10);
                Team testTeam = new Team("TestTeam");
                new Goal(testScorer, testTeam, null);
            } catch (IllegalArgumentException e) {
                assertEquals("The value of the timestamp of " +
                        "substitution was 'null', please try again.", e.getMessage());
            }
        }
        @Test
        @DisplayName("Exception thrown when timestamp is an empty String")
        public void initializeAGoalWithBlankStringForTimestamp() {
            try {
                Player testScorer = new Player("Johan Footman", 10);
                Team testTeam = new Team("TestTeam");
                new Goal(testScorer, testTeam, "");
            } catch (IllegalArgumentException e) {
                assertEquals("The timestamp was inputted " +
                        "as an empty string, please try again.", e.getMessage());
            }
        }
    }
}


