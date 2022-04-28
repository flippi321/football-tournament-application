package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.tournaments.KnockOut;
import edu.ntnu.idatt1002.k01g08.fta.objects.team.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.team.Team;
import edu.ntnu.idatt1002.k01g08.fta.objects.tournaments.Tournament;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class KnockOutTest {
    @Nested
    @DisplayName("Tests for Constructor")
    public class TestingConstructor{
        @Test
        @DisplayName("Testing Constructor with correct parameters")
        public void initializingNewTournamentUsingConstructor(){
            ArrayList<Team> teams = new ArrayList<>();
            for(int i = 0; i<8; i++){
                Team newTeam = new Team("Rosenborg " + i);
                newTeam.addPlayer(new Player("Spiller", i));
                teams.add(newTeam);
            }
            Tournament testTournament = new KnockOut("Tippeligaen2022", teams);
            assertEquals(8, testTournament.getNumberOfTeams());
            assertEquals("Tippeligaen2022", testTournament.getTournamentName());
            assertNotNull(testTournament);
        }

        @Test
        @DisplayName("Making a tournament with wrong Number of Teams")
        public void testingKnockoutThrowingIllegalException(){
            ArrayList<Team> teams = new ArrayList<>();
            for(int i = 0; i<5; i++){
                Team newTeam = new Team("Rosenborg " + i);
                newTeam.addPlayer(new Player("Spiller", i));
                teams.add(newTeam);
            }
            try {
                Tournament testTournament = new KnockOut("Tippeligaen2022", teams);
                fail("Test 'testingKnockoutThrowingIllegalException' did not throw an exception to when expected to.");
            } catch (Exception e) {
                assertEquals( "The number of teams is invalid.", e.getMessage());
            }
        }

        @Test
        @DisplayName("Making a tournament without teams")
        public void testTournamentWithEmptyTeamsList(){
            ArrayList<Team> teams = new ArrayList<>();
            try {
                Tournament testTournament = new KnockOut("Tippeligaen2022", teams);
                fail("Test 'testTournamentWithEmptyTeamsList' did not throw an exception to when expected to.");
            } catch (Exception e) {
                assertEquals( "The number of teams is invalid.", e.getMessage());
            }
        }

        @Test
        @DisplayName("Making a tournament without a name")
        public void testTournamentWithoutName(){
            ArrayList<Team> teams = new ArrayList<>();
            Team rosenborg = new Team("Rosenborg");
            Team lsk = new Team("Lillestrøm");
            rosenborg.addPlayer(new Player("Spiller2",2));
            lsk.addPlayer(new Player("Spiller1",1));
            teams.add(rosenborg);
            teams.add(lsk);
            try {
                Tournament testTournament = new KnockOut("", teams);
                fail("Test 'testTournamentWithoutName' did not throw an exception to when expected to.");
            } catch (Exception e) {
                assertEquals( "The tournament must have a name, you gave none", e.getMessage());
            }
        }

        @Test
        @DisplayName("Testing with right Number of Teams")
        public void testingKnockoutNotThrowingIllegalException(){
            ArrayList<Team> teams = new ArrayList<>();
            for(int i = 0; i<4; i++){
                Team newTeam = new Team("Rosenborg " + i);
                newTeam.addPlayer(new Player("Spiller", i));
                teams.add(newTeam);
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
                Team newTeam = new Team("Rosenborg " + i);
                newTeam.addPlayer(new Player("Spiller", i));
                teams.add(newTeam);
            }
            Tournament testTournament = new KnockOut("Tippeligaen2022", teams);
            assertEquals(4, testTournament.getUpcomingMatches().size());
        }

        @Test
        @DisplayName("Testing that equals method works")
        public void testTournamentEqualsMethod(){
            KnockOut tournament1 = new KnockOut("Tippeligaen2022", 3);
            KnockOut tournament2 = new KnockOut("Tippeligaen2022", 5);
            assertEquals(tournament1, tournament2);
        }
    }
}
