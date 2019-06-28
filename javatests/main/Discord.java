package main;

import java.util.ArrayList;

import teamSubsets.*;
import lazyTrees.*;

public class Discord {
    public static void main(String[] args) {
        final String FILEPATH = "resources/playerlist.txt";

        ArrayList<Player> playerList, newPlayer;
        PlayerFileReader fileReader = new PlayerFileReader();

        playerList = fileReader.readFile(FILEPATH);
        newPlayer = fileReader.readFile("resources/newplayer.txt");

        for (int i = 0; i < playerList.size(); i++) {
            System.out.println(playerList.get(i));
        }

        Sublist testSublist = new Sublist(playerList);
        System.out.println(testSublist.getRankValueSum(playerList));

        testSublist = testSublist.addItem(newPlayer.get(0));
        System.out.println(testSublist.getRankValueSum(testSublist.getPlayers()));

        ArrayList<Sublist> teams = SubsetSum.generateTeams(playerList);
        for (int i = 0; i < teams.size(); i++) {
           System.out.println("{");
           System.out.println(teams.get(i).getPlayers().toString());
           System.out.println("}");
        }
        System.out.println(teams.size());
    }
}
