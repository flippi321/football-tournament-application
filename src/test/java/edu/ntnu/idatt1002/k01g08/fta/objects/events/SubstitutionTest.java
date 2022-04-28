package edu.ntnu.idatt1002.k01g08.fta.objects.events;

import edu.ntnu.idatt1002.k01g08.fta.objects.team.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Substitution tests")
public class SubstitutionTest {

    @Nested
    @DisplayName("Positive Tests")
    public class PositiveTests {
        @Test
        @DisplayName("Constructor Works")
        public void initializeASubstitutionWithAllCorrectParameters() {
            Player testPlayerOne = new Player("Bat Man", 10);
            Player testPlayerTwo = new Player("Cat Man", 11);
            Team team = new Team("Team1");

            try {
                Substitution substitution = new Substitution("10:20", team, testPlayerOne,
                        testPlayerTwo);
            } catch (IllegalArgumentException e) {
                fail("The test 'initializeASubstitutionWithAllCorrectParameters' failed");
            }
        }
    }

    @Nested
    @DisplayName("Negative Tests")
    public class NegativeTests {

        @Test
        @DisplayName("Exception is thrown when 'null' is used as parameter")
        public void initializeASubstitutionWithNullInputForTimeStamp() {
            Player testPlayerOne = new Player("Bat Man", 10);
            Player testPlayerTwo = new Player("Cat Man", 11);
            Team team = new Team("Team1");

            try {
                Substitution substitution = new Substitution("02:30", team, testPlayerOne,
                        testPlayerTwo);
            } catch (NullPointerException n) {
                assertEquals("A player was inputted with a 'null'- value.",
                            n.getMessage());
            }
        }

        @Test
        @DisplayName("Exception is thrown when an empty string is used as parameter")
        public void initializeASubstitutionWithBlankInputForTimeStamp() {
            Player testPlayerOne = new Player("Bat Man", 10);
            Player testPlayerTwo = new Player("Cat Man", 11);
            Team team = new Team("Team1");

            try {
                Substitution substitution = new Substitution("02:30", team, testPlayerOne,
                        testPlayerTwo);
            } catch (IllegalArgumentException e) {
                assertEquals("The timestamp was inputted as an empty string, please try again.",
                            e.getMessage());
            }
        }

        @Test
        @DisplayName("toString Works")
        public void checkingThatToStringIsCorrect() {
            Player testPlayerOne = new Player("Bat Man", 10);
            Player testPlayerTwo = new Player("Cat Man", 11);
            Team team = new Team("Team1");
            Substitution substitution = new Substitution("02:30", team, testPlayerOne, testPlayerTwo);
            assertEquals("Substitution:\nPlayer in: Bat Man\nPlayer out: Cat Man", substitution.toString());
        }
    }
}
