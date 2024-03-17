/*
 * TCSS 342 - Spring 2023
 *Assignment 5 - Binary Search Tree
 */
public class MyBinarySearchTree <Type extends Comparable<Type>> {
    //Stores the root node of the binary search tree
    protected Node root;


    protected int size;

    //Stores the number of comparisons made in the find method (one per
    //recursive call).
    public long comparisons;

    public MyBinarySearchTree(){
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
    //? no children,
    //? one child,
    //? or two children.
    //iii. If the subTree is does not contain the item then we recursively
    //remove the item from the left or right child as appropriate.
    //? This method returns the new root of the sub-tree after the node has been
    //removed.
    //? This method should run in O(d) time where d is the depth of the item removed.
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
    //Updates the height of the node.
    // This method should run in O(1) time.
    protected void updateHeight(Node node){
        if (node.left == null && node.right == null) {
            node.height = 0;
        } else if (node.left == null) {
            node.height = node.right.height + 1;
        } else if (node.right == null) {
            node.height = node.left.height + 1;
        } else {
            node.height = Math.max(node.left.height, node.right.height) + 1;
        }
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
            return item + ":H" + height;
        }
    }


}
