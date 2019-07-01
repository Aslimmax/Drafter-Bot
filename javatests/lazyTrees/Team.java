package lazyTrees;

import java.util.ArrayList;
import teamSubsets.Sublist;

public class Team {
    private ArrayList<Sublist> team;
    // the number of teams with the same rank value
    private int numTeams;
    private int rankValue;

    public Team(Sublist players) {
        team.add(players);
        this.numTeams = 1;
        rankValue = (int) team.get(0).getRankValueSum();
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

    public boolean compareTo(Team other) {
        return this.rankValue == other.rankValue;
    };

    public String toString() {
        return "Number of teams with Rank Value "
                + team.get(0).getRankValueSum() + ": " + numTeams;
    }
}
