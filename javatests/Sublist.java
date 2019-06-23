import java.util.ArrayList;
/**
 * An object of type Sublist class creates an object from an ArrayList of Double
 * objects that contains the indicies of the prices of groceries (Part I) and
 * the indices of the time durations of songs (Part II) from text file
 * (groceries.txt) and a JSON file (music_genre_subset.json) respectively.
 */
class Sublist implements Cloneable {
    private double sum = 0.0;
    private ArrayList<Double> originalObjects;
    private ArrayList<Double> indices;

    /**
     * Default constructor that initializes a Sublist object based on an ArrayList
     * of Double object's original values and the indicies of those values
     * 
     * @param orig ArrayList of Double objects
     */
    // constructor creates an empty Sublist (no indices)
    public Sublist(ArrayList<Double> orig) {
        indices = new ArrayList<Double>();
        originalObjects = orig;
        for (int i = 0; i < orig.size(); i++) {
            // need an if statement to check to see if there are null values in the subset
            indices.add((double) i);
        }
    }

    /**
     * ArrayList of Double objects comprised of the original object's indices
     * 
     * @return ArrayList of Double objects
     */
    public ArrayList<Double> getIndices() {
        return this.indices;
    }

    /**
     * value of the total sum of all the objects in a subset based on the masterSet
     * that contains all/some of the elements comprised by the subset
     * 
     * @param masterSet ArrayList of Double objects that holds all the elements in a
     *                  subset
     * @return double of a subset's sum
     */
    public double getSum(ArrayList<Double> masterSet) {
        sum = 0;
        for (int i = 0; i < this.indices.size(); i++) {
            double tempDoubleIndex = this.indices.get(i);
            int tempIntIndex = (int) tempDoubleIndex;
            this.sum += masterSet.get(tempIntIndex);
        }
        return sum;
    }

    /**
     * Object that clones the properties and values of an object into a new object
     * 
     * @return Object that is an identical copy of the old Object
     * @throws CloneNotSupportedException
     */
    // I have done the clone() for you, since you will need clone() inside
    // addItem().
    public Object clone() throws CloneNotSupportedException {
        // shallow copy
        Sublist newObject = (Sublist) super.clone();
        // deep copy
        newObject.indices = (ArrayList<Double>) indices.clone();

        return newObject;
    }

    /**
     * Sublist that is generated from appending a new index to a reference sublist
     * 
     * @param indexOfItemToAdd index that is appended to Sublist
     * @return new Sublist with extra index added to the end of the list
     */
    Sublist addItem(int indexOfItemToAdd) {
        Sublist tempSublist = new Sublist(new ArrayList<Double>());
        try {
            Sublist returnSublist = (Sublist) this.clone();
            double tempIndex = indexOfItemToAdd;
            returnSublist.indices.add(tempIndex);
            tempSublist = returnSublist;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return tempSublist;
    }
}