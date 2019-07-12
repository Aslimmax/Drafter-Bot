package teamSubsets;

/**
 * An object of class Player will create an object with properties username,
 * rank, and rankValue that will be used to identify a Player.
 */
public class Player {
    // class variables
    private int rankValue;
    private String username;
    private String rank;

    // Constant values
    public static final int DEFAULT_RANK_VALUE = 0;
    public static final String DEFAULT_USERNAME = "Null";
    public static final String DEFAULT_RANK = "Iron IV";
    public static final int MIN_USERNAME_LENGTH = 3;
    public static final int MAX_USERNAME_LENGTH = 16;
    public static final int MIN_RANK_VALUE = 1;
    public static final int MAX_RANK_VALUE = 7;

    /**
     * 2-parameter overloaded constructor that sets the Player's username, rank,
     * and rank value
     * @param username          Player's username
     * @param rank              Player's rank
     */
    public Player(String username, String rank) {
        if (!setUsername(username)) {
            this.username = DEFAULT_USERNAME;
        }
        if (!setRank(rank)) {
            this.rank = DEFAULT_RANK;
        }
        if (!setRankValue(rank)) {
            rankValue = DEFAULT_RANK_VALUE;
        }
    }

    /**
     * Default constructor that sets the Player's username and rank to default
     * values defined in constants
     */
    public Player() {
        this(DEFAULT_USERNAME, DEFAULT_RANK);
    }

    /**
     * Accessor for the Player's rank value
     * @return          Integer rank value (criteria defined in
     *                  determineRankValue method)
     */
    public int getRankValue() { return rankValue; }

    /**
     * Accessor for the Player's username
     * @return          Player's username
     */
    public String getName() { return username; }

    /**
     * Accessor for the Player's rank
     * @return                  Player's rank
     */
    public String getRank() { return rank; }

    /**
     * Mutator that sets the value of the Player's username
     * @param name      Player's username
     * @return          Boolean value
     */
    public boolean setUsername(String name) {
        if (!validUsername(name)) {
            return false;
        }
        username = name;
        return true;
    }

    /**
     * Mutator that sets the value of the Player's rank
     * @param rank          Player's rank
     * @return              Boolen value
     */
    public boolean setRank(String rank) {
        if (!validRank(rank)) {
            return false;
        }
        this.rank = determineRank(rank);
        return true;
    }

    /**
     * Mutator that sets the value of the Player's rank value based on the 
     * Player's rank
     * @param rank          Player's rank
     * @return              Boolean value
     */
    public boolean setRankValue(String rank) {
        if (!validRank(rank)) {
            return false;
        }

        rankValue = determineRankValue(rank);
        if (rankValue == 0) {
            return false;
        }
        return true;
    }

    /**
     * Override method of toString that provides a formatted form of the 
     * Player's username, rank, and rank value
     * @return          Formatted return String
     */
    @Override
    public String toString() {
        return (username + ", " + rank + ", " + rankValue);
    }

    /**
     * Private helper function that determines whether username is valid
     * @param name      Player's username
     * @return          Boolean value
     */
    private boolean validUsername(String name) {
        if (name.length() >= MIN_USERNAME_LENGTH
                && name.length() <= MAX_USERNAME_LENGTH) {
            return true;
        }
        return false;
    }

    /**
     * Private helper function that determines whether the Player's rank is 
     * valid
     * @param rank      Player's rank
     * @return          Boolean value
     */
    private boolean validRank(String rank) {
        if (determineRankValue(rank) >= MIN_RANK_VALUE &&
                determineRankValue(rank) <= MAX_RANK_VALUE) {
            return true;
        }
        return false;
    }

    /**
     * Private helper function that determines the rank of the Player and
     * formats the rank.
     * @param rank      Player's rank
     * @return          Formatted rank String
     */
    private String determineRank(String rank) {
        char rankLetter = rank.toUpperCase().charAt(0);

        switch(rankLetter) {
            case 'I':
                return "Iron";
            case 'B':
                return "Bronze";
            case 'S':
                return "Silver";
            case 'G':
                return "Gold";
            case 'P':
                return "Platinum";
            case 'D':
                return "Diamond";
            case 'M':
                return "Master";
            default:
                return "Null";
        }
    }
    /**
     * Private helper function that determines whether rank is valid 
     * and assign respective rank value depending on rank.
     * I = Iron = 1
     * B = Bronze = 2
     * S = Silver = 3
     * G = Gold = 4
     * P = Platinum = 5
     * D = Diamond = 6
     * M = Master = 7
     * @param rank      Player's rank
     * @return          Rank value depending on Player's rank
     */
    private int determineRankValue(String rank) {
        char rankLetter = rank.toUpperCase().charAt(0);

        switch(rankLetter) {
            case 'I':
                return 1;
            case 'B':
                return 2;
            case 'S':
                return 3;
            case 'G':
                return 4;
            case 'P':
                return 5;
            case 'D':
                return 6;
            case 'M':
                return 7;
            default:
                return 0;
        }
    }
}