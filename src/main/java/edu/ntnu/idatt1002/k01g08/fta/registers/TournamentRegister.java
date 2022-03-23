package edu.ntnu.idatt1002.k01g08.fta.registers;

import edu.ntnu.idatt1002.k01g08.fta.objects.Tournament;

import java.util.ArrayList;
import java.util.List;
/**
 * Class for a tournament register storing tournaments of class 'Tournament'
 * This is stored in a list holding Tournaments
 *
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
     * uses .add-method of arraylist to add an object of class
     * Tournament to the register
     * @param tournament is the parameter which must be of class Tournament
     * @throws IllegalArgumentException if the tournament has value 'null' or already
     * is registered
     */
    public void addTournament(Tournament tournament) throws IllegalArgumentException{
        if(tournament == null) throw new IllegalArgumentException("Tournament was inputted with " +
                "value 'null', please try again.");
        if(hasTournament(tournament)) throw new IllegalArgumentException("Tournament was already registered, " +
                "please try to enter a new tournament again.");
        tournamentList.add(tournament);
    }
    /**
     * Remove-method that removes a tournament from the register
     * @param tournament is the parameter which must be of the class Tournament
     * @throws IllegalArgumentException if the tournament which is tried for removal from the
     * register is not registered
     */
    public void removeTournament(Tournament tournament) throws IllegalArgumentException{
        if(!hasTournament(tournament)) throw new IllegalArgumentException("Tournament is not registered, " +
                "and cannot be removed please try to enter a new tournament again.");
        tournamentList.remove(tournament);
    }
    /**
     * Method to add an array of tournaments to the register
     * @param addList is the list that of which all elements that are not
     *                already registered are added to the tournamentList
     * @throws IllegalArgumentException if the parameter-list 'addlist' is an empty list, or if the
     */
    public void addAllTournaments(List<Tournament> addList) throws IllegalArgumentException{
        if (addList.isEmpty()) throw new IllegalArgumentException("The list was empty. No new tournaments could " +
                "be added, please try again");
        for(Tournament element: addList){
            if (!tournamentList.contains(element)) tournamentList.addAll(addList);
            //TODO: add an equals-method in Tournament Class
        }
    }
    /**
     * Clear-method for removing all elements of the tournamentList
     */
    public void clearRegister(){
        this.tournamentList.clear();
    }
    /**
     * Help-method to check whether the parameter tournament is in the register
     * @param tournament, is the tournament that is checked for presence in the register
     * @return true if the tournament is already registered, false otherwise
     */
    private boolean hasTournament(Tournament tournament){
        return this.tournamentList.contains(tournament);
    }
    /**
     * Accessor method to get the tournamentList
     * @return List with Tournaments 'tournamentList'
     */
    public List<Tournament> getTournamentList() {
        return tournamentList;
    }
    /**
     * Accessor method that to get the number of tournaments
     * in the register
     * @return int tournamentList.size()
     */
    public int getNumberOfTournaments(){
        return tournamentList.size();
    }
    @Override
    public String
    toString() {
        return "TournamentRegister{" + tournamentList +
                '}';
    }
}
