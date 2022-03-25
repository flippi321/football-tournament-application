package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.KnockOut;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import edu.ntnu.idatt1002.k01g08.fta.objects.Tournament;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class KnockOutTest {
    @Nested
    public class TestingConstructor{
        @Test
        @DisplayName("Testing Constructor with correct parameters")
        public void initializingNewTournamentUsingConstructor(){
            ArrayList<Team> teams = new ArrayList<>();
            for(int i = 0; i<8; i++){
                teams.add(new Team("Rosenborg " + i));
            }
            Tournament testTournament = new KnockOut("Tippeligaen2022", teams);
            assertEquals(8, testTournament.getNumberOfTeams());
            assertEquals("Tippeligaen2022", testTournament.getTournamentName());
            assertNotNull(testTournament);
        }

        @Test
        @DisplayName("Testing with wrong Number of Teams")
        public void testingKnockoutThrowingIllegalException(){
            ArrayList<Team> teams = new ArrayList<>();
            for(int i = 0; i<5; i++){
                teams.add(new Team("Rosenborg " + i));
            }
            try {
                Tournament testTournament = new KnockOut("Tippeligaen2022", teams);
                fail("Test 'testingKnockoutThrowingIllegalException' did not throw an exception to when expected to.");
            } catch (Exception e) {
                assertEquals(e.getMessage(), "The number of teams is invalid.");
            }
        }

        @Test
        @DisplayName("Testing with right Number of Teams")
        public void testingKnockoutNotThrowingIllegalException(){
            ArrayList<Team> teams = new ArrayList<>();
            for(int i = 0; i<4; i++){
                teams.add(new Team("Rosenborg " + i));
            }
            try {
                new KnockOut("Tippeligaen2022", teams);
            } catch (Exception e) {
                fail("Illegal argument Exception Thrown");
            }
        }

        @Test
        @DisplayName("Testing setUpcomingMatch function")
        public void testSetUpcomingMatch(){
            ArrayList<Team> teams = new ArrayList<>();
            for(int i = 0; i<8; i++){
                teams.add(new Team("Rosenborg " + i));
            }
            Tournament testTournament = new KnockOut("Tippeligaen2022", teams);
            assertEquals(4, testTournament.getUpcomingMatches().size());
        }

        //TODO: test equals method
    }
}
