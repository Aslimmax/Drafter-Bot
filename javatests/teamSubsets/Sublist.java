package teamSubsets;

import java.util.ArrayList;

/**
 * An object of Sublist holds an ArrayList of Players and will be used in
 * SubsetSum to determine all the possible subsets of teams.
 */
public class Sublist implements Cloneable {
    private double rankSum = 0.0;
    private ArrayList<Player> players;

    /**
     * Overloaded constructor that initializes a Sublist with the passed in
     * players
     * @param orig ArrayList of Player objects
     */
    public Sublist(ArrayList<Player> orig) {
        players = new ArrayList<Player>();
        players.addAll(orig);
    }

    /**
     * Accessor method that gets the ArrayList of Players
     * @return          ArrayList of Players
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

//    public double getRankValueSum(ArrayList<Player> masterSet) {
//        this.rankSum = 0;
//        for (int i = 0; i < this.players.size(); i++) {
//            this.rankSum += masterSet.get(i).getRankValue();
//        }
//        return this.rankSum;
//    }

    /**
     * Sums the rank value of all the Player's in a Sublist
     * @return          Sum of rank values in a Player ArrayList
     */
    public double getRankValueSum() {
        this.rankSum = 0;
        for (int i = 0; i < this.players.size(); i++) {
            this.rankSum += players.get(i).getRankValue();
        }
        return this.rankSum;
    }

    /**
     * Object that clones the properties and values of an object into a new
     * object
     * @return Object that is an identical copy of the old Object
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        // shallow copy
        Sublist newObject = (Sublist) super.clone();
        // deep copy
        newObject.players = (ArrayList<Player>) players.clone();

        return newObject;
    }

    /**
     * Adds a player to a Sublist by cloning the original Sublist and
     * returning a temporary Sublist
     * @param playerToAdd       Player
     * @return                  New modified Sublist
     */
    public Sublist addItem(Player playerToAdd) {
        Sublist tempSublist = new Sublist(new ArrayList<Player>());
        try {
            Sublist returnSublist = (Sublist) this.clone();
            returnSublist.players.add(playerToAdd);
            tempSublist = returnSublist;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return tempSublist;
    }

    /**
     * Creates a String that formats the players from the Sublist's ArrayList
     * of Players.
     * @param teamNum       Team number
     * @return              Formatted team with players
     */
    public String displayTeams(int teamNum) {
        String returnString = "";

        returnString += ("Team " + teamNum + "\n");
        for (int i = 0; i < players.size(); i++) {
            returnString += players.get(i).toString() + "\n";
        }

        return returnString;
    }
}