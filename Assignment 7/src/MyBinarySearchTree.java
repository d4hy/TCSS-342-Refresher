/*
 * TCSS 342 - Spring 2023
 *Assignment 6 - Binary Search Tree
 */
public class MyBinarySearchTree <Type extends Comparable<Type>> {
   // This value is used to count the number of rotations made.
    //It should be incremented once in rotateRight and once in rotateLeft.
    public Integer rotations;
    //Stores the root node of the binary search tree

    //This flag determines if the tree should operate as an AVL or as a naive
    //BST.
    // If the flag is true then the tree will rebalance itself after an add or a remove
    //following the operations for an AVL tree.
    //If the flag is false then the tree will not rebalance itself.
    // This flag is set in the constructor and never changed after.
    protected boolean balancing;
    protected Node root;


    protected int size;

    //Stores the number of comparisons made in the find method (one per
    //recursive call).
    public long comparisons;

    public MyBinarySearchTree(){
        this(false);
    }
    // - Add a constructor that takes a boolean as an argument
    //and sets the balancing flag.
    // For this assignment hardcode the argument to true.
    // Also leave a default constructor that sets balancing to false so your prior code will
    //still work without change (i.e. backwards compatibility)
    public MyBinarySearchTree(boolean b){
        rotations = 0;
        this.balancing =b;
        root = null;
        size =0;
        comparisons=0;
    }

    //Adds the item into the binary search tree where it belongs.
    //The public method should call the private recursive method on the root
    public void add(Type item){
       root = add(item, root);
    }
    //The private method attempts to add the item to the subTree:
    //i. If the subTree is null the new node belongs here.
    //ii. If the subTree is not null then we recursively add the item to the left
    //or right child as appropriate.
    // This method returns the new root of the sub-tree after the node has been added.
    // This method should run in O(d) time where d is the depth the item added.
    //rebalance the tree if necessary after the add.
    // It should do this only if balancing is true.
    // This method should run in O(d) time where d is the depth the item added.
    protected Node  add (Type item, Node subTree){
        // base case
        if(subTree ==null){
            size++;
            return new Node(item);

        }
        //If item is less than the subTree.item, then it recursively calls add with item and the left subtree of subTree.
        if(item.compareTo(subTree.item) < 0){
            subTree.left = add(item, subTree.left);

        }
        //If item is greater than the subTree.item, then it recursively calls add with item and the right subtree of subTree.
        else if (item.compareTo(subTree.item) > 0) {
            subTree.right = add(item, subTree.right);
        }

        // Update height of the current node after it is added
        updateHeight(subTree);
        if(balancing == true){
            subTree= rebalance(subTree);
        }

        //correctly update the parent's reference to the newly added node.
        return subTree;

    }
    // Removes the item from the binary search tree
    //The public method should call the private recursive method on the root.
    public void remove(Type item){
        root =remove(item,root);
    }
    //The private method removes the item from the subTree:
    //i. If the subTree is null the item does not exist in the tree.
    //ii. If the subTree contains the item we remove it according to the three
    //cases:
    //no children,
    //one child,
    //or two children.
    //iii. If the subTree is does not contain the item then we recursively
    //remove the item from the left or right child as appropriate.
    // This method returns the new root of the sub-tree after the node has been
    //removed.
    // This method should run in O(d) time where d is the depth of the item removed.
    //This method should be upgraded to rebalance the tree if necessary after the
    //remove.
    // It should do this only if balancing is true.
    //This method should run in O(d) time where d is the depth the item added
    protected Node remove(Type item, Node subTree ){
        if (subTree == null) {
            // item not found in tree
            return null;
        }

        if (item.compareTo(subTree.item) < 0) {
            // item is smaller than current node's item,
            // so remove it from the left subtree
            subTree.left = remove(item, subTree.left);
        } else if (item.compareTo(subTree.item) > 0) {
            // item is larger than current node's item,
            // so remove it from the right subtree
            subTree.right = remove(item, subTree.right);
        } else {
            // item is found in current node

            // Case 1: No children
            if (subTree.left == null && subTree.right == null) {
                size--;
                return null;
            }
            // Case 2: One child
            if (subTree.left == null) {
                size--;
                return subTree.right;
            } else if (subTree.right == null) {
                size--;
                return subTree.left;
            }
            // Case 3: Two children
            Node temp = findMin(subTree.right);
            subTree.item = temp.item;
            subTree.right = remove(temp.item, subTree.right);
        }

        // Update height of the current node after it is removed
        updateHeight(subTree);
        if(balancing == true){
            subTree= rebalance(subTree);
        }

        return subTree;
    }

    //Returns the item found if the item is in the binary search tree and null
    //otherwise.
    //The public method should call the private recursive method on the root.
    public Type find (Type item){

        return find(item, root);
    }
    //The private method searches the appropriate sub-tree recursively for the item.
    //This method should run in O(d) time where d is the depth of the item found.
    protected Type find(Type item, Node subTree){
        comparisons++;
        if (subTree == null|| item ==null) {

            // item not found in tree
            return null;
        }
        if(item.compareTo(subTree.item) < 0 ){

            return find(item, subTree.left);
        }  else if (item.compareTo(subTree.item) > 0) {

            // item is larger than current node's item,
            return find(item, subTree.right);
        }

        return subTree.item;
    }

    // Helper method to find the minimum node in a subtree
    private Node findMin(Node subTree) {
        while (subTree.left != null) {
            subTree = subTree.left;
        }
        return subTree;
    }
    //Updates the height and the balance factor of the node.
    // This method should run in O(1) time.
    protected void updateHeight(Node node){
        if (node.left == null && node.right == null) {
            node.height = 0;
            node.balanceFactor = 0;
        } else if (node.left == null) {
            node.height = node.right.height + 1;
            node.balanceFactor = -1 - node.right.height;
        } else if (node.right == null) {
            node.height = node.left.height + 1;
            node.balanceFactor = node.left.height - (-1);
        } else {
            node.height = Math.max(node.left.height, node.right.height) + 1;
            node.balanceFactor = node.left.height - node.right.height;
        }
    }

    // Performs a right rotation on node.
    //This method should run in O(1) time.
    protected Node rotateRight(Node node){
        rotations++;
        Node pivot = node.left;
        node.left = pivot.right;
        pivot.right = node;
        updateHeight(node);
        updateHeight(pivot);
        return pivot;
    }
    //Performs a left rotation on node.
    //This method should run in O(1) time
    protected Node rotateLeft(Node node){
        rotations++;
        // Store the right child of the given node in a variable.
        // This is done because the right child will become the new root of the subtree after the rotation.
        Node newRoot = node.right;
        // This is done because the left subtree of the old right child  will become the right subtree
        // of the current left child of the left root of the new tree

        node.right = newRoot.left;
        // is done because the old root becomes the left child of the new root.
        newRoot.left = node;
        // Update heights of the rotated nodes
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    //Checks node for imbalance and if found performs the appropriate
    //rotations to correct it.
    //This method should run in O(1) time
    protected Node rebalance(Node node){
        // Check if the current node is null or has no children
        if (node == null || (node.left == null && node.right == null)) {
            return node;
        }


        if (node.balanceFactor< -1) {
            if (node.right.balanceFactor > 0) {
                // right-left case: rotate right, then left
                node.right = rotateRight(node.right);
            }

            // right-right case: rotate left
            node = rotateLeft(node);
        }

        else if (node.balanceFactor>1) {
            if (node.left.balanceFactor < 0) {
                // left-right case: rotate left, then right
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        }
        return node;
    }


    public int size(){
        return size;
    }
    // Returns true if the trie is empty and false otherwise.
    // This method should run in O(1) time.
    public boolean isEmpty(){
        return size==0;
    }

    public int height(){
        if(root!=null) {
            return root.height;
        }
       return -1;
    }

    @Override
    public String toString(){
        return "[" + inOrder(root) + "]";
    }

    protected String inOrder(Node node){
        if(node ==null){
            return "";
        } else {

            String left = inOrder(node.left);
            String right = inOrder(node.right);
            String currentNode = node.toString();
            if (left.isEmpty() && right.isEmpty()) {
                return currentNode;
            } else if (left.isEmpty()) {
                return currentNode + ", " + right;
            } else if (right.isEmpty()) {
                return left + ", " + currentNode;
            } else {
                return left + ", " + currentNode + ", " + right;
            }
        }

    }


    protected class Node {
        //The balance factor of the node.
        //Defined in AVL trees to the height of the left subtree minus the height of the right
        //subtree
        public int balanceFactor;

        //The item stored in the node.
        public Type item;

        //The left subtree.
        public Node left;

        //The right subtree
        public Node right;

        //The height of the node (the distance to the leaf nodes). We will count edges
        //so leaves have height 0.
        public int height;

        Node(Type it) {
            this.item= it;
            left = null;
            right =null;
            height= 0;

        }
        @Override
        public String toString() {
            return item + ":H" + height+":B"+balanceFactor;
        }
    }


}
