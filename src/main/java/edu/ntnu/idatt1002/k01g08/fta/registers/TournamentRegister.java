package edu.ntnu.idatt1002.k01g08.fta.registers;

import edu.ntnu.idatt1002.k01g08.fta.objects.Tournament;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for a tournament register storing previous tournamentList
 * This class has methods to:
 * add a new tournament to the register
 * remove an existing tournament from the register
 * get the register
 * get the amount of registered tournamentList
 */
public class TournamentRegister {
    private final List<Tournament> tournamentList;

    /**
     * Constructor for a tournament register which assigns a new arraylist
     * to the 'tournamentList' that can store elements of Tournament-class
     */
    public TournamentRegister(){
        this.tournamentList = new ArrayList<>();
    }
    /**
     * Add-method to register a tournament
     * uses .add
     * @param tournament
     * @throws IllegalArgumentException
     */
    public void addTournament(Tournament tournament) throws IllegalArgumentException{
        if(tournament == null) throw new IllegalArgumentException("Tournament was inputted with" +
                "value 'null', please try again.");
        if(hasTournament(tournament)) throw new IllegalArgumentException("Tournament was already registered, " +
                "please try to enter a new tournament again.");
        tournamentList.add(tournament);
    }
    public void removeTournament(Tournament tournament) throws IllegalArgumentException{
        if(!hasTournament(tournament)) throw new IllegalArgumentException("Tournament is not registered, " +
                "and cannot be removed please try to enter a new tournament again.");
        tournamentList.remove(tournament);
    }
    /**
     * Help-method to check whether the parameter tournament is in the register
     * @param tournament, is the tournament that is checked for presence in the register
     * @return true if the tournament is already registered, false otherwise
     */
    private boolean hasTournament(Tournament tournament){
        return this.tournamentList.contains(tournament);
    }

    public List<Tournament> getTournamentList() {
        return tournamentList;
    }

    public int getNumberOfTournaments(){
        return tournamentList.size();
    }
}
