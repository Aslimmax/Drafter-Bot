class Player {
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

    // overloaded constructor
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

    // Default constructor
    public Player() {
        this(DEFAULT_USERNAME, DEFAULT_RANK);
    }

    // Accessors
    public int getRankValue() { return rankValue; }
    public String getName() { return username; }
    public String getRank() { return rank; }

    // Mutators
    public boolean setUsername(String name) {
        if (!validUsername(name)) {
            return false;
        }
        username = name;
        return true;
    }

    public boolean setRank(String rank) {
        if (!validRank(rank)) {
            return false;
        }
        this.rank = rank;
        return true;
    }

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

    @Override
    public String toString() {
        return (username + ", " + rank + ", " + rankValue);
    }

    // Helper methods
    private boolean validUsername(String name) {
        if (name.length() >= MIN_USERNAME_LENGTH
                && name.length() <= MAX_USERNAME_LENGTH) {
            return true;
        }
        return false;
    }

    private boolean validRank(String rank) {
        if (determineRankValue(rank) >= MIN_RANK_VALUE &&
                determineRankValue(rank) <= MAX_RANK_VALUE) {
            return true;
        }
        return false;
    }

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