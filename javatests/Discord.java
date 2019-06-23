public class Discord {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        final String FILEPATH = "resources/playerlist.txt";

        PlayerFileReader listOfPlayers = new PlayerFileReader();

        Player playerOne = new Player("Relegate", "Diamond");
        System.out.println(playerOne.toString());

    }
}