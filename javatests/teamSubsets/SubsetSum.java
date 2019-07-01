package teamSubsets;

import java.util.ArrayList;

public class SubsetSum {

	public static ArrayList<Sublist> generateTeams(ArrayList<Player> playerList) {
		ArrayList<Sublist> subsetCollection = generateSubsetCollection(playerList);

		if (subsetCollection == null) {
			return null;
		} else {
			return generateMasterArrayList(subsetCollection);
		}
	}

	public static ArrayList<Sublist> generateSubsetCollection (ArrayList<Player> playerList) {
		ArrayList<Sublist> subsetCollection = new ArrayList<Sublist>();
		ArrayList<Sublist> modifiedSubsetCollection = new ArrayList<Sublist>();

		// initialize collections with empty set (sum of empty set = 0)
		subsetCollection.add(new Sublist(new ArrayList<Player>()));
        modifiedSubsetCollection.add(new Sublist(new ArrayList<Player>()));
		
		// i represents index of the value in teh list
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

	private static ArrayList<Sublist> generateMasterArrayList(ArrayList<Sublist> playerList) {
		ArrayList<Sublist> optimizedCollection = new ArrayList<Sublist>();
		ArrayList<Sublist> returnArrayList = new ArrayList<Sublist>();
		final int requiredTeamSize = 5;

		for (int i = 0; i < playerList.size(); i++) {
			int teamSize = playerList.get(i).getPlayers().size();
			
			if (teamSize == requiredTeamSize) {
				optimizedCollection.add(playerList.get(i));
			}
		}

		return optimizedCollection;
	}
}