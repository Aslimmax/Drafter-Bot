package teamSubsets;

import java.util.ArrayList;
import java.util.Random;

public class SubsetSum {


    /**
     * Public method that initiates the call to generate all possible teams from a given set and chooses two random,
     * unique teams.
     * @param playerList
     * @return
     */
    public static ArrayList<Sublist> generateTeams(ArrayList<Player> playerList) {
        ArrayList<Sublist> subsetCollection = generateSubsetCollection(playerList);

        if (subsetCollection == null) {
            return null;
        } else {
            return pickTeams(optimizeRankValueSpread(generateMasterArrayList(subsetCollection)));
        }
    }

    /**
     * Private helper method that generates all possible subsets in a given set
     * @param playerList        ArrayList of Players
     * @return                  ArrayList of Sublists of all possible subsets
     */
    private static ArrayList<Sublist> generateSubsetCollection(ArrayList<Player> playerList) {
        ArrayList<Sublist> subsetCollection = new ArrayList<Sublist>();
        ArrayList<Sublist> modifiedSubsetCollection = new ArrayList<Sublist>();

        // initialize collections witSh empty set (sum of empty set = 0)
        subsetCollection.add(new Sublist(new ArrayList<Player>()));
        modifiedSubsetCollection.add(new Sublist(new ArrayList<Player>()));

        // i represents index of the value in the list
        for (int i = 0; i < playerList.size(); i++) {
            // k represents the index of the sublist, L, in subsetCollection
            for (int k = 0; k < subsetCollection.size(); k++) {
                modifiedSubsetCollection.add(modifiedSubsetCollection.get(k).addItem(playerList.get(i)));
            }
            subsetCollection.clear();
            subsetCollection.addAll(modifiedSubsetCollection);
            modifiedSubsetCollection.clear();
            modifiedSubsetCollection.addAll(subsetCollection);
        }
        return subsetCollection;
    }

    /**
     * Private helper method that generates the "Master ArrayList", which is all teams of 5 players from
     * generateSubsetCollection()
     * @param playerList        ArrayList of Players
     * @return                  ArrayList of Sublists of all teams of 5
     */
    private static ArrayList<Sublist> generateMasterArrayList(ArrayList<Sublist> playerList) {
        ArrayList<Sublist> optimizedCollection = new ArrayList<Sublist>();
        final int requiredTeamSize = 5;

        for (int i = 0; i < playerList.size(); i++) {
            int teamSize = playerList.get(i).getPlayers().size();

            if (teamSize == requiredTeamSize) {
                optimizedCollection.add(playerList.get(i));
            }
        }
        return optimizedCollection;
    }

    /**
     * Private helper method that categorizes all of the teams based on their rank values. For example, if a team's
     * total rank value = 22; that team would be placed into an ArrayList at position 22. Similar to Hashing.
     * @param playerList        ArrayList of Players
     * @return                  ArrayList of Players with the most number of teams inserted into the ArrayList
     */
    private static ArrayList<Sublist> optimizeRankValueSpread(ArrayList<Sublist> playerList) {
        ArrayList<ArrayList<Sublist>> teamsOfFive = new ArrayList<ArrayList<Sublist>>();
        ArrayList<Sublist> greatestNumberOfTeams = new ArrayList<Sublist>();

        for (int i = 0; i < 40; i++) {
            teamsOfFive.add(new ArrayList<Sublist>());
        }

        for (int j = 0; j < playerList.size(); j++) { // traverse through the entire playerList
            boolean inserted = false;
            int k = 0;

            while (!inserted) {
                if (k == playerList.get(j).getRankValueSum()) {
                    teamsOfFive.get(k).add(playerList.get(j));
                    inserted = true;
                    break;
                }
                k++;
            }
        }

        for (int l = 0; l < teamsOfFive.size(); l++) {
            if (teamsOfFive.get(l).size() == 0) {
                continue;
            }
            if (teamsOfFive.get(l).size() > greatestNumberOfTeams.size()) {
                greatestNumberOfTeams = teamsOfFive.get(l);
            }
        }

        return greatestNumberOfTeams;
    }

    /**
     * Private helper method that picks two random teams from an ArrayList and determines if each team is unique. If
     * the same player is on both teams, two more random teams are picked again. This process continues until both teams
     * are completely unique.
     * @param playerList        Optimized ArrayList of Players
     * @return                  ArrayList containing two unique team of players
     */
    private static ArrayList<Sublist> pickTeams(ArrayList<Sublist> playerList) {
        ArrayList<Sublist> returnTeams = new ArrayList<Sublist>();
        Sublist teamOne = new Sublist(new ArrayList<Player>());
        Sublist teamTwo = new Sublist(new ArrayList<Player>());
        Random randomNum = new Random();
        boolean randomTeam = false;

        while (!randomTeam) {
            // Random num between 0 (included) and playerLIst.size() + 1 (Excluded)
            int randomNumOne = randomNum.nextInt(playerList.size());
            int randomNumTwo = randomNum.nextInt(playerList.size());

            teamOne = playerList.get(randomNumOne);
            teamTwo = playerList.get(randomNumTwo);

            for (int i = 0; i < teamOne.getPlayers().size(); i++) {
                int l = 0;
                for (int k = 0; k < teamTwo.getPlayers().size(); k++) {
                    if (teamOne.getPlayers().get(i).equals(teamTwo.getPlayers().get(k))) {
                        break;
                    }
                    l++;
                }
                if (l != teamTwo.getPlayers().size()) {
                    break;
                }
                if (i == teamOne.getPlayers().size() - 1)
                {
                    randomTeam = true;
                }
            }
        }
        returnTeams.add(teamOne);
        returnTeams.add(teamTwo);
        return returnTeams;
    }
}