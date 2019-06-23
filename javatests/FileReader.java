import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * An object of FileReader class reads the data of a text file passed
 * to the object and returns an ArrayList of Player objects
 */
public class FileReader {

    /**
     * Default constructor with no properties
     */
    GroceriesFileReader() {

    }

    /**
     * ArrayList of Player objects that stores the player's name and information
     * into ths list
     * 
     * @param filePathInput name of the file that is parsed
     * @return ArrayList of Player objects
     */
    public ArrayList<Player> readFile(String filePathInput) {
        ArrayList<Player> returnPlayerList = new ArrayList<Player>();
        String playerName;
        String playerRank;
        String[] tempStringArray;

        try {
            Scanner sc = new Scanner(new FileReader(filePathInput));
            while (sc.hasNext()) {
                playerName = sc.nextLine();
                tempStringArray = playerName.split(",");
                playerRank = arrayOfGrocery[1];
                returnPlayerList.add(new Player(playerName, playerRank));
            }
        }
        // check requirements for catch
        catch (FileNotFoundException e) {
            e.printStackTrace();
            returnPlayerList.clear();
        }
        return returnPlayerList;
    }
}
