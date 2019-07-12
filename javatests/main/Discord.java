package main;

import java.util.ArrayList;

import teamSubsets.*;

public class Discord {
    public static void main(String[] args) {
//        final String FILEPATH = "resources/playerlist.txt";
//
//        ArrayList<Player> playerList = new ArrayList<Player>();
//        ArrayList<Player> playerListTwo = new ArrayList<Player>();
//        ArrayList<Sublist> testList = new ArrayList<Sublist>();
//
//
//        PlayerFileReader fileReader = new PlayerFileReader();
//
////        playerList = fileReader.readFile(FILEPATH);
//
//        playerList.add(new Player("Relegate", "Diamond"));
//        playerList.add(new Player("Bigboianus", "Diamond"));
//        playerList.add(new Player("Juvenes", "Diamond"));
//        playerList.add(new Player("Cluckinchuck", "Diamond"));
//        playerList.add(new Player("Emily", "Diamond"));
//        playerListTwo.add(new Player("Optimum", "Diamond"));
//        playerListTwo.add(new Player("Calvin", "Diamond"));
//        playerListTwo.add(new Player("Neptic", "Diamond"));
//        playerListTwo.add(new Player("Wilson", "Diamond"));
//        playerListTwo.add(new Player("Poptart", "Diamond"));
//
//        Sublist testOne = new Sublist(playerList);
//        Sublist testTwo = new Sublist(playerListTwo);
//
//        testList.add(testOne);
//        testList.add(testTwo);
//
//        ArrayList<Sublist> teams = SubsetSum.pickTeams(testList);
//
//        for (int i = 0; i < teams.size(); i++) {
//            System.out.println(teams.get(i).getPlayers().toString());
//        }
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
