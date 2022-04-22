package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.Foul;
import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//TODO
// Change nested names to fit regex
// Better coverage

public class FoulTest {

    @Nested
    @DisplayName("Tests for Constructor")
    public class TestsForConstructorOfFoul {

        @Nested
        @DisplayName("Positive Tests")
        public class PositiveTestsForConstructor {

            @Test
            @DisplayName("Foul Constructor Works")
            public void initializeAFoulWithAllCorrectParameters(){
                try {
                    Player testPlayer = new Player("Morgan Freeman", 10);
                    Team testTeam = new Team("Team");
                    Foul testFoul = new Foul("Hands",
                            "40:20", testPlayer, testTeam, 1);
                } catch(IllegalArgumentException e) {
                    fail("The test 'initializeAFoulWithAllCorrectParameters' failed");
                }
            }

            @Test
            @DisplayName("Turns empty string for foulTag into a 'null'")
            public void initializeAFoulWithoutTagMeaningAnEmptyStringAsParameter(){
                try {
                    Player testPlayer = new Player("Morgan Freeman", 10);
                    Team testTeam = new Team("Team");
                    Foul testFoul = new Foul("", "40:20", testPlayer, testTeam, 1);
                    assertNull(testFoul.getFoulTag());
                } catch(IllegalArgumentException e) {
                    fail("The test 'initializeAFoulWithoutTagMeaningAnEmptyStringAsParameter' failed");
                }
            }
        }

        @Nested
        @DisplayName("Negative Tests")
        public class NegativeTestsForFoulConstructor {
            @Test
            @DisplayName("Throws Exception when timeStamp is null")
            public void instantiatingAFoulWithParameterTimeStampHavingNullValue(){
                try {
                    Player testPlayer = new Player("Morgan Freeman", 10);
                    Team testTeam = new Team("Team");
                    Foul testFoul = new Foul("Hands", null, testPlayer, testTeam, 1);
                } catch(NullPointerException n) {
                    assertEquals("The value of the timestamp of substitution was 'null', which is not valid."
                            , n.getMessage());
                }
            }

            @Test
            @DisplayName("Throws Exception when timeStamp is an empty String")
            public void instantiatingAFoulWithParameterTimeStampAsABlankString(){
                try {
                    Player testPlayer = new Player("Morgan Freeman", 10);
                    Team testTeam = new Team("Team");
                    Foul testFoul = new Foul("Hands", "", testPlayer, testTeam, 1);
                    fail("'instantiatingAFoulWithParameterTimeStampAsABlankString()' did not throw an Exception " +
                            "when expected to.");
                } catch(IllegalArgumentException e) {
                    assertEquals("The timestamp was inputted as an empty string.", e.getMessage());
                }
            }
        }
    }
}
