package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.Tournament;
import edu.ntnu.idatt1002.k01g08.fta.registers.TournamentRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TournamentRegisterTest {
    private TournamentRegister testReg;
    @BeforeEach
    public void initializeNewRegister(){
        this.testReg = new TournamentRegister();
    }
    @Nested
    public class TestForConstuctor {
        @Test
        public void initializingANewRegisterUsingConstructor(){
            TournamentRegister testTournamentRegister = new TournamentRegister();
            assertNotNull(testTournamentRegister.getTournamentList());
        }
    }
    @Nested
    public class TestsForAddMethod {
        @Nested
        public class PositiveTestsForAddMethod {
            @Test
            public void addMethodOnEmptyRegister(){
                Tournament testTournament = new Tournament(); //TODO: must be updated
                testReg.addTournament(testTournament);
                assertEquals(1, testReg.getNumberOfTournaments());
            }
            @Test
            public void addMethodOnRegisterWithAnElement(){
                Tournament testTournament = new Tournament(); //TODO: must be updated
                Tournament testTournament2 = new Tournament(); //TODO: must be updated
                testReg.addTournament(testTournament);
                testReg.addTournament(testTournament2);
                assertEquals(2, testReg.getNumberOfTournaments());
            }
        }
        @Nested
        public class NegativeTestsForAddMethod {
            @Test
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
            public void addingATournamentThatIsAlreadyRegistered(){
                try{
                    Tournament testTournament = new Tournament(); //TODO: must be updated
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
    public class TestsForRemoveMethod {
        @Nested
        public class PositiveTestsForRemoveMethod {
            @Test
            public void removeARegisteredTournament(){
                Tournament testTournament = new Tournament(); //TODO: must be updated
                testReg.addTournament(testTournament);
                testReg.removeTournament(testTournament);
                assertEquals(0,testReg.getNumberOfTournaments());
            }
        }
        @Nested
        public class NegativeTestsForRemoveMethod {
            @Test
            public void removeAnUnregisteredTournamentExpectingException(){
                try{
                    Tournament testTournament = new Tournament(); //TODO: must be updated
                    Tournament testTournament2 = new Tournament(); //TODO: must be updated
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
    public class TestsForAddAllMethod{
        @Nested
        public class PositiveTestsForAddAllMethod {
            @Test
            public void addAllInAListToTheRegister(){
                Tournament testTournament = new Tournament(); //TODO: must be updated
                Tournament testTournament2 = new Tournament(); //TODO: must be updated
                Tournament testTournament3 = new Tournament(); //TODO: must be updated
                ArrayList<Tournament> testList = new ArrayList<>();
                testList.add(testTournament);
                testList.add(testTournament2);
                testList.add(testTournament3);
                testReg.addAllTournaments(testList);
                assertEquals(3, testReg.getNumberOfTournaments());
            }
            @Test
            public void addOnlyTournamentsWhichAreNotAlreadyRegisteredToTheRegister(){ //TODO: must be updated after equals
                Tournament testTournament = new Tournament(); //TODO: must be updated
                Tournament testTournament2 = new Tournament(); //TODO: must be updated
                Tournament testTournament3 = new Tournament(); //TODO: must be updated
                ArrayList<Tournament> testList = new ArrayList<>();
                testList.add(testTournament);
                testList.add(testTournament2);
                testList.add(testTournament3);
                //testReg.addTournament(testTournament);
                assertEquals(1, testReg.getNumberOfTournaments());
                testReg.addAllTournaments(testList);
                assertEquals(3, testReg.getNumberOfTournaments());
            }
        }
        @Nested
        public class NegativeTestsForAddAllMethod {
            @Test
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
    public class TestForClearRegisterMethod {
        @Test
        public void clearingARegister(){
            Tournament testTournament = new Tournament(); //TODO: must be updated
            Tournament testTournament2 = new Tournament(); //TODO: must be updated
            testReg.addTournament(testTournament);
            testReg.addTournament(testTournament2);
            testReg.clearRegister();
            assertEquals(0, testReg.getNumberOfTournaments());
        }
    }
}
/**
 *TODO: COPY/PASTE
 @Nested
 public class TestsForRemoveMethod {
 @Nested
 public class PositiveTestsForRemoveMethod {
 @Test
 public void removeARegisteredTournament(){
 }
 }
 @Nested
 public class NegativeTestsForRemoveMethod {
 @Test
 public void removeAnUnregisteredTournamentExpectingException(){
 try{
 }catch(IllegalArgumentException e){
 }
 }
 }
 }
 *
 *
 */
