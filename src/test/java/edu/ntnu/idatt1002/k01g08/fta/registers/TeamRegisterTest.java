package edu.ntnu.idatt1002.k01g08.fta.registers;

import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamRegisterTest {

    @Test
    @DisplayName("Adding team already in the register should throw IllegalArgumentException")
    void addTeamAlreadyInTheRegisterShouldThrowIllegalArgumentException() {
        TeamRegister register = new TeamRegister();
        Team team = new Team("NTNUI");
        register.addTeam(team);
        assertThrows(IllegalArgumentException.class, () -> register.addTeam(team));
    }

    @Test
    @DisplayName("Number of teams should be 0")
    void numberOfTeamsShouldBeZero() {
        TeamRegister register = new TeamRegister();
        assertEquals(0, register.getNumberOfTeams());
    }

    @Test
    @DisplayName("Number of teams in register should be 1")
    void numberOfTeamsShouldBeOne() {
        TeamRegister register = new TeamRegister();
        register.addTeam(new Team("NTNUI"));
        assertEquals(1, register.getNumberOfTeams());
    }

    @Test
    @DisplayName("Register should be empty after removing a team")
    void registerShouldBeEmptyAfterRemovingATeam() {
        TeamRegister register = new TeamRegister();
        Team team = new Team("NTNUI");
        register.addTeam(team);
        register.removeTeam(team);
        assertEquals(0, register.getNumberOfTeams());
    }

    @Test
    @DisplayName("Register should be empty after clearing")
    void registerShouldBeEmptyAfterClearing() {
        TeamRegister register = new TeamRegister();
        register.addTeam(new Team("NTNUI"));
        register.addTeam(new Team("Tihlde Pythons"));
        register.clearRegister();
        assertEquals(0, register.getNumberOfTeams());

    }
}