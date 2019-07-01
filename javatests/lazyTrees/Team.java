package lazyTrees;

import teamSubsets.Sublist;
import teamSubsets.Player;

public class Team implements Comparable<Team> {
    // the name of the item
    private ArrayList<Sublist>() team;

    // the count of this item
    private int count;

    /**
     * Constructor takes name for item. Instantiates count to 1.
     * 
     * @param name name of the item to add created.
     */
    public Team(Sublist<Player> team) {
        this.name = name;
        this.count = 1;
    }

    /**
     * Increase the count by 1 each call.
     */
    public void incrementCount() {
        count++;
    };

    /**
     * Reduce the count by 1 each call and return false when count is less than 1.
     * 
     * @return Whether the count of the item was successfully decreased by 1.
     */
    public boolean decrementCount() {
        if (count < 1)
            return false;

        count--;
        return true;
    };

    /**
     * Get current number of items
     * 
     * @return int value of current number of items.
     */
    public int getCount() {
        return count;
    }

    /**
     * Use item name for comparing.
     */
    @Override
    public int compareTo(Team other) {
        return name.compareToIgnoreCase(other.name);
    };

    /**
     * Returns a string representation with the item name and count.
     */
    public String toString() {
        return name + ":" + count + " ";
    }
}
