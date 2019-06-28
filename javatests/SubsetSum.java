import java.util.ArrayList;

public class SubsetSum {

	// public static ArrayList<Double> findSubset(ArrayList<Double> playerList) {
	// 	ArrayList<Sublist> subsetCollection = generateSubsetCollection(playerList);

	// 	if (subsetCollection == null) {
	// 		return userShoppingList;
	// 	} else {
	// 		return generateMasterArrayList(userShoppingList, subsetCollection);
	// 	}
	// }

	public ArrayList<Sublist> generateSubsetCollection (ArrayList<Player> playerList) {
		ArrayList<Sublist> subsetCollection = new ArrayList<Sublist>();
		ArrayList<Sublist> modifiedSubsetCollection = new ArrayList<Sublist>();
		double newRankValueSum = 0.0;

		// initialize collections with empty set (sum of empty set = 0)
		subsetCollection.add(new Sublist(new ArrayList<Player>()));
        modifiedSubsetCollection.add(new Sublist(new ArrayList<Player>()));
		
		// i represents index of the value in teh list
		for (int i = 0; i < playerList.size(); i++) {
			// k represents the index of the sublist, L, in subsetCollection
			for (int k = 0; k < subsetCollection.size(); k++) {
				newRankValueSum =
                        modifiedSubsetCollection.get(k).getRankValueSum(playerList) + playerList.get(i).getRankValue();
				modifiedSubsetCollection.add(modifiedSubsetCollection.get(k).addItem(playerList.get(i)));
			}
			subsetCollection.clear();
			subsetCollection.addAll(modifiedSubsetCollection);
			modifiedSubsetCollection.clear();
			modifiedSubsetCollection.addAll(subsetCollection);
		}
		return subsetCollection;
	}
}