import java.util.ArrayList;
/**
 * 
 */
class Sublist implements Cloneable {
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
        for (int i = 0; i < this.indices.size(); i++) {
            double tempDoubleIndex = this.indices.get(i);
            int tempIntIndex = (int) tempDoubleIndex;
            this.rankSum += masterSet.get(tempIntIndex).getRankValue();
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

    /**
     * Sublist that is generated from appending a new index to a reference sublist
     * 
     * @param indexOfItemToAdd index that is appended to Sublist
     * @return new Sublist with extra index added to the end of the list
     */
    Sublist addItem(Player playerToAdd) {
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