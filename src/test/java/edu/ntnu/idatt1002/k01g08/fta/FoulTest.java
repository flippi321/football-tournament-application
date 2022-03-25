package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.Foul;
import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//TODO
// Change nested names to fit regex
// Better coverage

public class FoulTest {

    @Nested
    public class TestsForConstructorOfFoul {

        @Nested
        public class PositiveTestsForConstructor {

            @Test
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
        public class NegativeTestsForFoulConstructor {

            @Test
            public void instantiatingAFoulWithParameterTimeStampHavingNullValue(){
                try {
                    Player testPlayer = new Player("Morgan Freeman", 10);
                    Team testTeam = new Team("Team");
                    String nullVal = null;
                    Foul testFoul = new Foul("Hands", nullVal, testPlayer, testTeam, 1);
                } catch(IllegalArgumentException e) {
                    assertEquals("The value of the timestamp of substitution" +
                            " was 'null', please try again.", e.getMessage());
                }
            }

            @Test
            public void instantiatingAFoulWithParameterTimeStampAsABlankString(){
                try {
                    Player testPlayer = new Player("Morgan Freeman", 10);
                    Team testTeam = new Team("Team");
                    Foul testFoul = new Foul("Hands", "", testPlayer, testTeam, 1);
                } catch(IllegalArgumentException e) {
                    assertEquals("The timestamp was inputted as an empty string, " +
                            "please try again.", e.getMessage());
                }
            }
        }
    }
}
