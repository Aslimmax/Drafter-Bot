package lazyTrees;

import java.util.ArrayList;

import teamSubsets.Sublist;

public class Team implements Comparable<Team> {
    // the name of the item
    private ArrayList<Sublist> team;

    // the count of this item
    private int numTeams;

    public Team(Sublist players) {
        team.add(players);
        this.numTeams = 1;
    }

    public void incrementCount() {
        numTeams++;
    };

    public boolean decrementCount() {
        if (numTeams < 1)
            return false;

        numTeams--;
        return true;
    };

    public int getNumTeams() {
        return numTeams;
    }

    @Override
    public int compareTo(Team other) {
        return 0;
    };

    public String toString() {
        return "Number of teams with Rank Value "
                + team.get(0).getRankValueSum() + ": " + numTeams;
    }
}
