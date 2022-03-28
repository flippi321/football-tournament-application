package edu.ntnu.idatt1002.k01g08.fta.registers;

import edu.ntnu.idatt1002.k01g08.fta.objects.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a register of football teams
 * @author teodorbi
 * @version 2022-03-21
 */

public class TeamRegister {
    private final List<Team> teams;

    /**
     * Creates a team register
     */
    public TeamRegister() {
        this.teams = new ArrayList<>();
    }

    /**
     * Add a team to the register
     * @param team is the team getting added to the register
     * @throws IllegalArgumentException if the team is the team is already in the register
     */
    public void addTeam(Team team) throws IllegalArgumentException{
        if (this.teams.contains(team)) throw new IllegalArgumentException("This team is already in the register");
        this.teams.add(team);
    }

    /**
     * Remove a team from the register
     * @param team is the team getting removed from the list
     * @return true or false
     */
    public boolean removeTeam(Team team){
        return this.teams.remove(team);
    }

    /**
     * Method for clearing the register
     */
    public void clearRegister(){
        this.teams.clear();
    }

    /**
     * Method for retrieving the teams in the register
     * @return a copy of the register as an ArrayList
     */
    public List<Team> getTeams(){
        return new ArrayList<>(this.teams);
    }

    /**
     * Method for retrieving a specific team from the register
     * @param teamName is the name of the team
     * @return the Team
     * @throws IllegalArgumentException if the team isn't in the register
     */
    public Team getTeam(String teamName) throws IllegalArgumentException{
        for (Team t : teams){
            if (t.getName().equals(teamName)) return t;
        }
        throw new IllegalArgumentException("This team isn't in the register");
    }

    /**
     * Find the number of teams in the register
     * @return the number of teams in the register as an int
     */
    public int getNumberOfTeams(){
        return this.teams.size();
    }

    /**
     * toString
     * @return information about the teams in the register as a String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Teams\n");
        for(Team team : this.teams){
            sb.append(team.toString()).append("\n");
        }
        return sb.toString();
    }
}
