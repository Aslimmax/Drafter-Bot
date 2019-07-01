package teamSubsets;

import java.util.ArrayList;
/**
 * 
 */
public class Sublist implements Cloneable {
    private double rankSum = 0.0;
    private ArrayList<Double> indices;
    private ArrayList<Player> players;

    /**
     * 
     * @param orig ArrayList of Player objects
     */
    public Sublist(ArrayList<Player> orig) {
        indices = new ArrayList<Double>();
        players = new ArrayList<Player>();
        for (int i = 0; i < orig.size(); i++) {
            // need an if statement to check to see if there are null values in the subset
            indices.add((double) i);
            players.add(orig.get(i));
        }
    }

    /**
     * ArrayList of Double objects comprised of the original object's indices
     * 
     * @return ArrayList of Double objects
     */
    public ArrayList<Double> getIndices() {
        return this.indices;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * @param masterSet 
     * @return
     */
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
    // I have done the clone() for you, since you will need clone() inside
    // addItem().
    public Object clone() throws CloneNotSupportedException {
        // shallow copy
        Sublist newObject = (Sublist) super.clone();
        // deep copy
        newObject.indices = (ArrayList<Double>) indices.clone();
        newObject.players = (ArrayList<Player>) players.clone();

        return newObject;
    }

    public Sublist addItem(Player playerToAdd) {
        Sublist tempSublist = new Sublist(new ArrayList<Player>());
        try {
            Sublist returnSublist = (Sublist) this.clone();
            Player tempIndex = playerToAdd;
            returnSublist.players.add(tempIndex);
            tempSublist = returnSublist;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return tempSublist;
    }
}