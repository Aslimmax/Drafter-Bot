package lazyTrees;

import java.util.*;

/**
 * Implemented version of a Binary Search Tree that utilizes Lazy Deletion to
 * decrease the amount of time it takes to delete an item by simply marking a
 * Node in the tree as "deleted", but not removing it from the tree until later.
 *
 * @param <E> Generic parameter that only accepts data items that implement
 *            Comparable<E>
 */
public class LazySearchTree<E extends Comparable<? super E>> implements Cloneable {
    // tracks the number of undeleted nodes
    protected int mSize;
    // tracks the number of deleted and undeleted nodes
    protected int mSizeHard;
    protected LazySTNode mRoot;

    /**
     * Default constructor that clears the entire tree upon initializing
     */
    public LazySearchTree() {
        clear();
    }

    /**
     * Determines if tree is empty
     * 
     * @return Boolean value whether size of tree is equal to 0
     */
    public boolean empty() {
        return (mSize == 0);
    }

    /**
     * Accessor method for current size of undeleted nodes in the tree
     * 
     * @return Integer value of soft size of tree
     */
    public int size() {
        return mSize;
    }

    /**
     * Accessor method for the current size of deleted and undeleted nodes in the
     * tree
     * 
     * @return Integer value of hard size of tree
     */
    public int sizeHard() {
        return mSizeHard;
    }

    /**
     * Clears the number of items in the tree and resets mSize to 0
     */
    public void clear() {
        mSize = 0;
        mRoot = null;
    }

    /**
     * Accessor method for the height of the tree
     * 
     * @return Integer value of height of the tree
     */
    public int showHeight() {
        return findHeight(mRoot, -1);
    }

    /**
     * Public method that finds the minimum value in the tree by parsing through the
     * left-most children from the top of the tree.
     * 
     * @return Generic data of Node that contains the minimum value
     */
    public E findMin() {
        if (mRoot == null)
            throw new NoSuchElementException();
        if (findMin(mRoot) == null)
            throw new NoSuchElementException();
        return findMin(mRoot).data;
    }

    /**
     * Public method that finds the maximum value in the tree by parsing through the
     * right-most children from the top of the tree
     * 
     * @return Generic data of Node that contains the maximum value
     */
    public E findMax() {
        if (mRoot == null)
            throw new NoSuchElementException();
        if (findMin(mRoot) == null)
            throw new NoSuchElementException();
        return findMax(mRoot).data;
    }

    /**
     * Public method that finds a Generic data contained in a Node in the tree
     * 
     * @param x Generic data item
     * @return Generic data of Node that contains the requested data
     */
    public E find(E x) {
        LazySTNode resultNode;
        resultNode = find(mRoot, x);
        if (resultNode == null)
            throw new NoSuchElementException();
        return resultNode.data;
    }

    /**
     * Public method that determines if the tree contains a user requested Generic
     * data item
     * 
     * @param x Generic data item
     * @return Boolean value of whether the data item is in the tree or not
     */
    public boolean contains(E x) {
        return find(mRoot, x) != null;
    }

    /**
     * Public method that inserts a Generic data item into the tree
     * 
     * @param x Generic data item
     * @return Boolean value of whether the data item was successfully inserted into
     *         the tree
     */
    public boolean insert(E x) {
        int oldSize = mSize;
        mRoot = insert(mRoot, x);
        return (mSize != oldSize);
    }

    /**
     * Public method that removes a Generic data item from the tree
     * 
     * @param x Generic data item
     * @return Boolean value of whether the data item was successfully removed from
     *         the tree
     */
    public boolean remove(E x) {
        int oldSize = mSize;

        LazySTNode node = find(mRoot, x);
        if (node != null) {
            node.deleted = true;
            mSize--;
            return true;
        } else {
            remove(mRoot, x);
        }
        return (mSize != oldSize);
    }

    /**
     * Visits every node that has been and has not been deleted from the tree
     * 
     * @param func Generic function object
     * @param <F>  Type parameter for an implementer of Traverser<E>
     */
    public <F extends Traverser<? super E>> void traverseHard(F func) {
        traverseHard(func, mRoot);
    }

    /**
     * Visits every node that has not been deleted from the tree
     * 
     * @param func Generic function object
     * @param <F>  Type parameter for an implementer of Traverser<E>
     */
    public <F extends Traverser<? super E>> void traverseSoft(F func) {
        traverseSoft(func, mRoot);
    }

    /**
     * Cloning function that performs a clone of the original tree and copies it
     * into a new sub tree
     * 
     * @return Returns the cloned binary tree
     * @throws CloneNotSupportedException Exception thrown when clone is not
     *                                    possible
     */
    public Object clone() throws CloneNotSupportedException {
        LazySearchTree<E> newObject = (LazySearchTree<E>) super.clone();
        newObject.clear(); // can't point to other's data

        newObject.mRoot = cloneSubtree(mRoot);
        newObject.mSize = mSize;

        return newObject;
    }

    // private helper methods ----------------------------------------

    /**
     * Logic of public findMin() that determines the minimum value of the tree.
     * Passes over nodes that were soft removed from the tree
     * 
     * @param root Passed in LazySTNode node
     * @return Recursive call to itself
     */
    protected LazySTNode findMin(LazySTNode root) {
        if (root == null)
            return root;

        LazySTNode currentRoot = findMin(root.lftChild);

        if (currentRoot != null)
            return currentRoot;

        if (!root.deleted)
            return root;

        return findMin(root.lftChild);
    }

    /**
     * Logic of public findMax() that determines the maximum value in the tree.
     * Passes over nodes that were soft removed from the tree
     * 
     * @param root Passed in LazySTNode node
     * @return Recursive call to itself
     */
    protected LazySTNode findMax(LazySTNode root) {
        if (root == null)
            return root;

        LazySTNode currentRoot = findMax(root.rtChild);

        if (currentRoot != null)
            return currentRoot;

        if (!root.deleted)
            return root;

        return findMax(root.rtChild);
    }

    /**
     * Logic of public insert() that inserts a LazySTNode with a Generic data item.
     * Determines if a Node has been soft removed and sets the deleted attribute
     * back to false if reinserting value.
     * 
     * @param root Passed in LazySTNode node
     * @param x    Generic data item
     * @return LazySTNode with Generic data item
     */
    protected LazySTNode insert(LazySTNode root, E x) {
        int compareResult; // avoid multiple calls to compareTo()

        if (root == null) {
            mSize++;
            mSizeHard++;
            return new LazySTNode(x, null, null, false);
        }

        if (root.deleted && reInserted(root, x)) {
            root.deleted = false;
            mSize++;
            return root;
        }

        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            root.lftChild = insert(root.lftChild, x);
        else if (compareResult > 0)
            root.rtChild = insert(root.rtChild, x);

        return root;
    }

    /**
     * Private helper class for insert() that determines if the first keyword of the
     * passed in root's data and the generic data are the same
     * 
     * @param root Passed in LazySTNode node
     * @param x    Generic data item
     * @return Boolean value of whether they're both the same
     */
    private boolean reInserted(LazySTNode root, E x) {
        String rootData = root.data.toString().substring(0, root.data.toString().indexOf(":"));
        String genericData = x.toString().substring(0, root.data.toString().indexOf(":"));
        return rootData.equals(genericData);
    }

    /**
     * Logic of public method remove(). This is a soft remove, so it doesn't
     * physically remove the node from the tree, but simply changes the node's
     * deleted attribute to true
     * 
     * @param root Passed in LazySTNode node
     * @param x    Generic data item
     */
    protected void remove(LazySTNode root, E x) {
        int compareResult; // avoid multiple calls to compareTo()

        if (root == null)
            return;

        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            remove(root.lftChild, x);
        else if (compareResult > 0)
            remove(root.rtChild, x);

        // found the node
        else if (root.lftChild != null && root.rtChild != null) {
            root.data = findMin(root.rtChild).data;
            remove(root.rtChild, root.data);
        } else {
            root.deleted = true;
            root = (root.lftChild != null) ? root.lftChild : root.rtChild;
            mSize--;
        }
    }

    /**
     * Logic of public method traverseHard() that recursively traverses the nodes
     * that have been deleted and not deleted
     * 
     * @param func     Generic function object
     * @param treeNode Passed in LazySTNode node
     * @param <F>      Type parameter for an implementer of Traverser<E>
     */
    protected <F extends Traverser<? super E>> void traverseHard(F func, LazySTNode treeNode) {
        if (treeNode == null)
            return;

        traverseHard(func, treeNode.lftChild);
        func.visit(treeNode.data);
        traverseHard(func, treeNode.rtChild);
    }

    /**
     * Logic of public method traverseSoft() that recursively traverses the nodes
     * that have not been deleted
     * 
     * @param func     Generic function object
     * @param treeNode Passed in LazySTNode node
     * @param <F>      Type parameter for an implementer of Traverser<E>
     */
    protected <F extends Traverser<? super E>> void traverseSoft(F func, LazySTNode treeNode) {
        if (treeNode == null)
            return;

        traverseSoft(func, treeNode.lftChild);
        if (!treeNode.deleted)
            func.visit(treeNode.data);
        traverseSoft(func, treeNode.rtChild);
    }

    /**
     * Logic of public method find(). Passes over any nodes that have been soft
     * removed from the tree
     * 
     * @param root Passed in LazySTNode node
     * @param x    Generic data item
     * @return LazySTNode node that contains the requested generic data item
     */
    protected LazySTNode find(LazySTNode root, E x) {
        int compareResult; // avoid multiple calls to compareTo()

        if (root == null)
            return null;

        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            return find(root.lftChild, x);
        if (compareResult > 0)
            return find(root.rtChild, x);
        if (root.deleted)
            return null;
        return root; // found
    }

    /**
     * Logic of public method clone() that mimics a deep-copy clone()
     * 
     * @param root Passed in LazySTNode node
     * @return New LazySTNode that is a copy of the original at location
     */
    protected LazySTNode cloneSubtree(LazySTNode root) {
        LazySTNode newNode;
        if (root == null)
            return null;

        // does not set myRoot which must be done by caller
        newNode = new LazySTNode(root.data, cloneSubtree(root.lftChild), cloneSubtree(root.rtChild), false);
        return newNode;
    }

    /**
     * Logic of public method findHeight() that determines the height of the tree
     * 
     * @param treeNode Passed in LazySTNode node
     * @param height   Height of the tree
     * @return Height from the bottom most node of the tree
     */
    protected int findHeight(LazySTNode treeNode, int height) {
        int leftHeight, rightHeight;
        if (treeNode == null)
            return height;
        height++;
        leftHeight = findHeight(treeNode.lftChild, height);
        rightHeight = findHeight(treeNode.rtChild, height);
        return (leftHeight > rightHeight) ? leftHeight : rightHeight;
    }

    /**
     * An object of LazySTNode is similar to a Node in an ADT, except it includes
     * data attributes for the left child, right child, data type of itself, whether
     * it has been deleted or not, and the root of the node. Properties of a Binary
     * Search Tree (BST) node
     */
    private class LazySTNode {
        // use public access so the tree or other classes can access members
        public LazySTNode lftChild, rtChild;
        public E data;
        public LazySTNode myRoot; // needed to test for certain error
        public boolean deleted;

        /**
         * Overloaded constructor
         * 
         * @param d         Generic data item
         * @param lft       Left child node
         * @param rt        Right child node
         * @param isDeleted Whether teh tree has been soft removed or not
         */
        public LazySTNode(E d, LazySTNode lft, LazySTNode rt, boolean isDeleted) {
            lftChild = lft;
            rtChild = rt;
            data = d;
            deleted = isDeleted;
        }

        /**
         * Default constructor
         */
        public LazySTNode() {
            this(null, null, null, false);
        }
    }

}