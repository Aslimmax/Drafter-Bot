package main;

import java.util.ArrayList;
import teamSubsets.*;

/**
 * Include program description HERE...
 *
 * @author Andrew Lim
 */
public class Discord {
    public static void main(String[] args) {
        final String FILEPATH = "resources/playerlist.txt";

        ArrayList<Player> playerList;
        PlayerFileReader fileReader = new PlayerFileReader();

        playerList = fileReader.readFile(FILEPATH);

        ArrayList<Sublist> teams = SubsetSum.generateTeams(playerList);
        for (int i = 0; i < teams.size(); i++) {
            System.out.println(teams.get(i).displayTeams(i + 1));
        }
    }
}
