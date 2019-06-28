package lazyTrees;

/**
 * Implements the Traverser interface in order to visit objects in a tree
 * 
 * @param <E> Generic data type
 */
public class PrintObject<E> implements Traverser<E> {
    /**
     * Implemented version of the abstract method in Interface Traverser visit ()
     * that prints out the object's data
     * 
     * @param x Generic data item
     */
    public void visit(E x) {
        System.out.print(x + " ");
    }
}