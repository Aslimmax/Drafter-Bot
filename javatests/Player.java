class Player { 
    
    // class variables 
    private String name;
    private String rank;

    // overloaded constructor
    public Player(String name, String rank) { 
        this.name = name;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return (name + ", " + rank);
    }
}