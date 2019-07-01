package teamSubsets;

import java.util.ArrayList;
/**
 * 
 */
public class Sublist implements Cloneable {
    private double rankSum = 0.0;
    private ArrayList<Player> players;

    /**
     * 
     * @param orig ArrayList of Player objects
     */
    public Sublist(ArrayList<Player> orig) {
        players = new ArrayList<Player>();
        players.addAll(orig);
    }


    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public double getRankValueSum(ArrayList<Player> masterSet) {
        this.rankSum = 0;
        for (int i = 0; i < this.players.size(); i++) {
            this.rankSum += masterSet.get(i).getRankValue();
        }
        return this.rankSum;
    }

    public double getRankValueSum() {
        this.rankSum = 0;
        for (int i = 0; i < this.players.size(); i++) {
            this.rankSum += players.get(i).getRankValue();
        }
        return this.rankSum;
    }

    /**
     * Object that clones the properties and values of an object into a new object
     * 
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
}