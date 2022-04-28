package edu.ntnu.idatt1002.k01g08.fta.objects.events;

import edu.ntnu.idatt1002.k01g08.fta.objects.team.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.team.Team;
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
                System.out.println(e.getMessage());
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
                fail("The test 'initializeAGoalWithNullValueForTimestamp' should have thrown an exception.");
            } catch (NullPointerException n) {
                assertEquals("The value of the timestamp of substitution was 'null', which is not valid.",
                        n.getMessage());
            }
        }
        @Test
        @DisplayName("Exception thrown when timestamp is an empty String")
        public void initializeAGoalWithBlankStringForTimestamp() {
            try {
                Player testScorer = new Player("Johan Footman", 10);
                Team testTeam = new Team("TestTeam");
                new Goal(testScorer, testTeam, "");
                fail("The test 'initializeAGoalWithBlankStringForTimestamp' should have thrown an exception.");
            } catch (IllegalArgumentException e) {
                assertEquals("The timestamp was inputted as an empty string.", e.getMessage());
            }
        }
        @Test
        @DisplayName("Exception thrown when the first number of a time stamp is less than 0")
        public void initializeAGoalWithTimeStampLessThanReqRange() {
            try {
                Player testScorer = new Player("Johan Footman", 10);
                Team testTeam = new Team("TestTeam");
                new Goal(testScorer, testTeam, "-1:20");
                fail("The test 'initializeAGoalWithTimeStampLessThanReqRange' should have thrown an exception.");
            } catch (IllegalArgumentException e) {
                assertEquals("The first number of the time stamp was outside the " +
                        "valid integer range [0,999].", e.getMessage());
            }
        }
        @Test
        @DisplayName("Exception thrown when the first number of a time stamp is more than 999")
        public void initializeAGoalWithTimeStampMoreThanReqRange() {
            try {
                Player testScorer = new Player("Johan Footman", 10);
                Team testTeam = new Team("TestTeam");
                new Goal(testScorer, testTeam, "1000:20");
                fail("The test 'initializeAGoalWithTimeStampMoreThanReqRange' should have thrown an exception.");
            } catch (IllegalArgumentException e) {
                assertEquals("The time stamp is using the wrong format. The time must be on the format" +
                        " mm:ss (ex 09:45, 65:55 or 102:05)", e.getMessage());
            }
        }
        @Test
        @DisplayName("Exception thrown when the second number of a time stamp less than 0")
        public void initializeAGoalWithTimeStampsSecondNumLessThanReqRange() {
            try {
                Player testScorer = new Player("Johan Footman", 10);
                Team testTeam = new Team("TestTeam");
                new Goal(testScorer, testTeam, "10:-1");
                fail("The test 'initializeAGoalWithTimeStampsSecondNumLessThanReqRange' should have thrown an exception.");
            } catch (IllegalArgumentException e) {
                assertEquals("The second number of the time stamp was outside the valid integer " +
                        "range [0,60].", e.getMessage());
            }
        }
        @Test
        @DisplayName("Exception thrown when the time stamp contains letters in place of numbers")
        public void initializeAGoalWithTimeStampUnparsableToInteger() {
            try {
                Player testScorer = new Player("Johan Footman", 10);
                Team testTeam = new Team("TestTeam");
                new Goal(testScorer, testTeam, "10:M2");
                fail("The test 'initializeAGoalWithTimeStampUnparsableToInteger' should have thrown an exception.");
            } catch (IllegalArgumentException e) {
                assertEquals("The time stamp was not able to be parsed to integers, " +
                        "only time stamps on the format: mm:ss (ex 09:45, 65:55 or 102:05) are " +
                        "allowed.", e.getMessage());
            }
        }
        @Test
        @DisplayName("Exception thrown when the time stamp contains special character in place of numbers")
        public void initializeAGoalWithTimeStampContainingSC() {
            try {
                Player testScorer = new Player("Johan Footman", 10);
                Team testTeam = new Team("TestTeam");
                new Goal(testScorer, testTeam, "1?:10");
                fail("The test 'initializeAGoalWithTimeStampContainingSC' should have thrown an exception.");
            } catch (IllegalArgumentException e) {
                assertEquals("The time stamp was not able to be parsed to integers, only time " +
                        "stamps on the format: mm:ss (ex 09:45, 65:55 or 102:05) are allowed.", e.getMessage());
            }
        }


        @Test
        @DisplayName("Exception thrown when player is 'null'")
        public void initializeAGoalWithNullValueForPlayer() {
            try {
                Team testTeam = new Team("TestTeam");
                new Goal(null, testTeam, "20:20");
            } catch (NullPointerException n) {
                assertEquals("A player was inputted with a 'null'- value.", n.getMessage());
            }
        }
        @Test
        @DisplayName("Exception thrown when team is 'null'")
        public void initializeAGoalWithNullValueForTeam() {
            try {
                Player testScorer = new Player("Johan Footman", 10);
                new Goal(testScorer, null, "20:22");
            } catch (NullPointerException n) {
                assertEquals("A team was inputted with a 'null'- value.", n.getMessage());
            }
        }

        /*

            private void checkTimeStampFormat(String timeStamp) throws IllegalArgumentException {
        if(timeStamp.contains(":") && timeStamp.length() == 5 || timeStamp.length() == 6) {
            try {
                String[] timeStampParts = timeStamp.split(":");
                Integer.parseInt(timeStampParts[0]);
                if(Integer.parseInt(timeStampParts[0]) < 0 || Integer.parseInt(timeStampParts[0]) > 999) throw new
                        IllegalArgumentException("The first number of the time stamp was outside the valid integer" +
                        " range [0,999].");
                Integer.parseInt(timeStampParts[1]);
                if(Integer.parseInt(timeStampParts[0]) < 0 || Integer.parseInt(timeStampParts[0]) > 60) throw new
                        IllegalArgumentException("The second number of the time stamp was outside the valid integer" +
                        " range [0,60].");
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        throw new IllegalArgumentException("The time stamp is empty or is using the wrong format. " +
                "The date must be on the format mm:ss (ex 09:45 or 65:55)");
    }
         */
    }
}


