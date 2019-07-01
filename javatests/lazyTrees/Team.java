package lazyTrees;

import java.util.ArrayList;

import teamSubsets.Sublist;
import teamSubsets.Player;

public class Team implements Comparable<Team> {
    // the name of the item
    private ArrayList<Sublist> team;

    // the count of this item
    private int numTeams;

    /**
     * Constructor takes name for item. Instantiates count to 1.
     *
     * @param name name of the item to add created.
     */
    public Team(Sublist players) {
        team.add(players);
        this.numTeams = 1;
    }

    /**
     * Increase the count by 1 each call.
     */
    public void incrementCount() {
        numTeams++;
    };

    /**
     * Reduce the count by 1 each call and return false when count is less than 1.
     *
     * @return Whether the count of the item was successfully decreased by 1.
     */
    public boolean decrementCount() {
        if (numTeams < 1)
            return false;

        numTeams--;
        return true;
    };

    /**
     * Get current number of items
     *
     * @return int value of current number of items.
     */
    public int getNumTeams() {
        return numTeams;
    }

    /**
     * Use item name for comparing.
     */
    @Override
    public int compareTo(Team other) {
        return 0;
    };

    /**
     * Returns a string representation with the item name and count.
     */
    public String toString() {
        return "Number of teams with Rank Value " 
        + team.get(0).getRankValueSum() + ": " + numTeams;
    }
}
