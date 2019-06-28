package lazyTrees;

/**
 * An interface that enables the ability to use function objects
 * @param <E>        Generic data type
 */
public interface Traverser<E>
{
   /**
    * Abstract method that essentially visits the item it is traversing
    * @param x       Generic data item
    */
   public void visit(E x);
}
