package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.Substitution;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Substitution tests")
public class SubstitutionTest {

    @Nested
    @DisplayName("Positive tests for the subclass ´Substitution´")
    public class PositiveTests {

        @Nested
        @DisplayName("Tests for valid input in the constructor")
        public class constructorTests {

            @Test
            @DisplayName("Valid parameter-input in constructor")
            public void initializeASubstitutionWithAllCorrectParameters() {
                Player testPlayerOne = new Player("Bat Man", 10);
                Player testPlayerTwo = new Player("Cat Man", 11);
                Team team = new Team();

                try {
                    Substitution substitution = new Substitution("10:20", team, testPlayerOne,
                            testPlayerTwo);
                } catch (IllegalArgumentException e) {
                    fail("The test 'initializeASubstitutionWithAllCorrectParameters' failed");
                }
            }
        }
    }

    @Nested
    @DisplayName("Negative tests for the subclass ´Substitution´")
    public class NegativeTests {

        @Test
        @DisplayName("Invalid (null) input for parameter in constructor")
        public void initializeASubstitutionWithNullInputForTimeStamp() {
            Player testPlayerOne = new Player("Bat Man", 10);
            Player testPlayerTwo = new Player("Cat Man", 11);
            Team team = new Team();

            try {
                Substitution substitution = new Substitution(null, team, testPlayerOne,
                        testPlayerTwo);
            } catch (IllegalArgumentException e) {
                assertEquals("The value of the timestamp of substitution was 'null', please try again.",
                            e.getMessage());
            }
        }

        @Test
        @DisplayName("Invalid (empty string) input for parameter in constructor")
        public void initializeASubstitutionWithBlankInputForTimeStamp() {
            Player testPlayerOne = new Player("Bat Man", 10);
            Player testPlayerTwo = new Player("Cat Man", 11);
            Team team = new Team();

            try {
                Substitution substitution = new Substitution("", team, testPlayerOne,
                        testPlayerTwo);
            } catch (IllegalArgumentException e) {
                assertEquals("The timestamp was inputted as an empty string, please try again.",
                            e.getMessage());
            }
        }
    }
}
