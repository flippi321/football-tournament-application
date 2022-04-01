package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.Tournament;
import edu.ntnu.idatt1002.k01g08.fta.registers.TournamentRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TournamentRegisterTest {
    private TournamentRegister testReg;
    private Tournament testTournament;
    private Tournament testTournament2;
    private Tournament testTournament3;

    @BeforeEach
    public void initializeNewRegister(){
        this.testReg = new TournamentRegister();
        this.testTournament = new Tournament("Test") {
            @Override
            public void findUpcomingMatches() {

            }
        };
        this.testTournament2 = new Tournament("Test2") {
            @Override
            public void findUpcomingMatches() {

            }
        };
        this.testTournament3 = new Tournament("Test3") {
            @Override
            public void findUpcomingMatches() {

            }
        };
    }
    @Nested
    @DisplayName("Tests for Constructor")
    public class TestForConstructor {
        @Test
        @DisplayName("Constructor works")
        public void initializingANewRegisterUsingConstructor(){
            TournamentRegister testTournamentRegister = new TournamentRegister();
            assertNotNull(testTournamentRegister.getTournamentList());
        }
    }
    @Nested
    @DisplayName("Tests for Add Method")
    public class TestsForAddMethod {
        @Nested
        @DisplayName("Positive Tests")
        public class PositiveTestsForAddMethod {
            @Test
            @DisplayName("Add method works")
            public void addMethodOnEmptyRegister(){
                testReg.addTournament(testTournament);
                assertEquals(1, testReg.getNumberOfTournaments());
            }
            @Test
            @DisplayName("Add method works with multiple variables")
            public void addMethodOnRegisterWithAnElement(){
                testReg.addTournament(testTournament);
                testReg.addTournament(testTournament2);
                assertEquals(2, testReg.getNumberOfTournaments());
            }
        }
        @Nested
        @DisplayName("Negative Tests")
        public class NegativeTestsForAddMethod {
            @Test
            @DisplayName("It is not possible to add a 'null' tournament to the register")
            public void addingATournamentWithValueNull(){
                try{
                    Tournament testTournament = null; //TODO: must be updated
                    testReg.addTournament(testTournament);
                }catch (IllegalArgumentException e){
                    assertEquals("Tournament was inputted with value 'null', " +
                            "please try again.", e.getMessage());
                }
            }
            @Test
            @DisplayName("It is not possible to add an already existing Tournament")
            public void addingATournamentThatIsAlreadyRegistered(){
                try{
                    testReg.addTournament(testTournament);
                    testReg.addTournament(testTournament);
                }catch (IllegalArgumentException e){
                    assertEquals("Tournament was already registered, please t" +
                            "ry to enter a new tournament again.", e.getMessage());
                }
            }
        }
    }
    @Nested
    @DisplayName("Tests for Remove Method")
    public class TestsForRemoveMethod {
        @Nested
        @DisplayName("Positive Tests")
        public class PositiveTestsForRemoveMethod {
            @Test
            @DisplayName("Removing a Tournament Works")
            public void removeARegisteredTournament(){
                testReg.addTournament(testTournament);
                testReg.removeTournament(testTournament);
                assertEquals(0,testReg.getNumberOfTournaments());
            }
        }
        @Nested
        @DisplayName("Negative Tests")
        public class NegativeTestsForRemoveMethod {
            @Test
            @DisplayName("Exception is thrown when trying to remove a non-existent Tournament")
            public void removeAnUnregisteredTournamentExpectingException(){
                try{
                    testReg.addTournament(testTournament);
                    testReg.removeTournament(testTournament2);
                }catch(IllegalArgumentException e){
                    assertEquals("Tournament is not registered, and cannot be removed " +
                            "please try to enter a new tournament again.", e.getMessage());
                }
            }
        }
    }

    @Nested
    @DisplayName("Tests for addAll method")
    public class TestsForAddAllMethod{
        @Nested
        @DisplayName("Positive Tests")
        public class PositiveTestsForAddAllMethod {
            @Test
            @DisplayName("Adding a list of tournaments is possible")
            public void addAllInAListToTheRegister(){
                ArrayList<Tournament> testList = new ArrayList<>();
                testList.add(testTournament);
                testList.add(testTournament2);
                testList.add(testTournament3);
                testReg.addAllTournaments(testList);
                assertEquals(3, testReg.getNumberOfTournaments());
            }
            @Test
            @DisplayName("Two Identical Tournaments cannot be Saved")
            public void addOnlyTournamentsWhichAreNotAlreadyRegisteredToTheRegister(){ //TODO: must be updated after equals
                ArrayList<Tournament> testList = new ArrayList<>();
                testList.add(testTournament);
                testList.add(testTournament2);
                testList.add(testTournament3);
                testReg.addTournament(testTournament);
                assertEquals(1, testReg.getNumberOfTournaments());
                testReg.addAllTournaments(testList);
                assertEquals(3, testReg.getNumberOfTournaments());
            }
        }

        @Nested
        @DisplayName("Negative Tests")
        public class NegativeTestsForAddAllMethod {
            @Test
            @DisplayName("Exception when adding an empty list")
            public void addingAnEmptyArrayListExpectingException(){
                try{
                    ArrayList<Tournament> testList = new ArrayList<>();
                    testReg.addAllTournaments(testList);
                }catch(IllegalArgumentException e){
                    assertEquals("The list was empty. No new tournaments could be added, " +
                            "please try again", e.getMessage());
                }
            }
        }
    }
    @Nested
    @DisplayName("Test for Clearing Register")
    public class TestForClearRegisterMethod {
        @Test
        @DisplayName("Clearing a Register works")
        public void clearingARegister(){
            testReg.addTournament(testTournament);
            testReg.addTournament(testTournament2);
            testReg.clearRegister();
            assertEquals(0, testReg.getNumberOfTournaments());
        }
    }
}