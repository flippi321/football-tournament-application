package edu.ntnu.idatt1002.k01g08.fta.registers;

import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeamRegisterTest {

    @Test
    @DisplayName("Throw Exception when trying to add already existing team")
    public void addTeamAlreadyInTheRegisterShouldThrowIllegalArgumentException() {
        TeamRegister register = new TeamRegister();
        Team team = new Team("NTNUI");
        register.addTeam(team);
        assertThrows(IllegalArgumentException.class, () -> register.addTeam(team));
    }

    @Test
    @DisplayName("Number of teams should be 0")
    public void numberOfTeamsShouldBeZero() {
        TeamRegister register = new TeamRegister();
        assertEquals(0, register.getNumberOfTeams());
    }

    @Test
    @DisplayName("Number of teams in register should be 1")
    public void numberOfTeamsShouldBeOne() {
        TeamRegister register = new TeamRegister();
        register.addTeam(new Team("NTNUI"));
        assertEquals(1, register.getNumberOfTeams());
    }

    @Test
    @DisplayName("Register should be empty after removing a team")
    public void registerShouldBeEmptyAfterRemovingATeam() {
        TeamRegister register = new TeamRegister();
        Team team = new Team("NTNUI");
        register.addTeam(team);
        register.removeTeam(team);
        assertEquals(0, register.getNumberOfTeams());
    }

    @Test
    @DisplayName("Removing non existent team returns false")
    public void removeWrongTeamTest(){
        TeamRegister register = new TeamRegister();
        Team team = new Team("NTNUI");
        assertFalse(register.removeTeam(team));
    }

    @Test
    @DisplayName("Register should be empty after clearing")
    public void registerShouldBeEmptyAfterClearing() {
        TeamRegister register = new TeamRegister();
        register.addTeam(new Team("NTNUI"));
        register.addTeam(new Team("Tihlde Pythons"));
        register.clearRegister();
        assertEquals(0, register.getNumberOfTeams());

    }

    @Test
    @DisplayName("getTeam returns right team")
    public void getTeamTest(){
        TeamRegister register = new TeamRegister();
        register.addTeam(new Team("NTNUI"));
        register.addTeam(new Team("Tihlde Pythons"));
        assertEquals("NTNUI", register.getTeam("NTNUI").getName());
    }

    @Test
    @DisplayName("Throws Exception when trying to aquire non existent team")
    public void getTeamNotInRegisterShouldThrowIllegalArgumenteException(){
        TeamRegister register = new TeamRegister();
        register.addTeam(new Team("NTNUI"));
        register.addTeam(new Team("Tihlde Pythons"));
        assertThrows(IllegalArgumentException.class, () -> register.getTeam("test"));
    }
}