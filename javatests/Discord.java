import java.util.ArrayList;

public class Discord {
   public static void main(String[] args) {
      final String FILEPATH = "resources/playerlist.txt";
      
      ArrayList<Player> playerList;
      PlayerFileReader fileReader = new PlayerFileReader();

      playerList = fileReader.readFile(FILEPATH);

      for (int i = 0; i < playerList.size(); i++) {
         System.out.println(playerList.get(i));
      }
   }
}