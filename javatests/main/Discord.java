package main;

import java.util.ArrayList;

import teamSubsets.*;

public class Discord {
    public static void main(String[] args) {
        final String FILEPATH = "resources/playerlist.txt";

        ArrayList<Player> playerList;
        PlayerFileReader fileReader = new PlayerFileReader();

        playerList = fileReader.readFile(FILEPATH);
        SubsetSum testing = new SubsetSum();

        ArrayList<Sublist> teams = testing.generateTeams(playerList);
        for (int i = 0; i < teams.size(); i++) {
           System.out.println("{");
           System.out.println(teams.get(i).getPlayers().toString());
           System.out.println("}");
        }
        System.out.println(teams.size());
    }
}
