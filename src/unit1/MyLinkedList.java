package unit1;

/*
 * TCSS 342 - Spring 2023
 * Assignment 1 - Lists
 */
public class MyLinkedList <Type extends Comparable<Type>> {
    protected class Node {
        Type item;
        Node next;
        Node(Type it, Node nd) {
            item = it;
            next = nd;
        }
        public String toString() {
            return item.toString();
        }
    }
    //A reference to the first node in the list.
   //○ Is null if the list is empty.
    //○ This reference should be updated whenever the first node is changed
    protected Node first;

    //A reference to the current node in the list.
    //○ The current node of the list is used to traverse.
    //○ The current node should only be changed by the methods first, next and
    //remove.
    //○ Initialized to be the null.
    //○ When this node is null the current node has fallen off the end of the list
    protected Node current;
    //A reference to the node before the current node in the list.
    //○ Whenever the current node is updated you should update this node.
    //○ This node is only null if current is equal to first.
    //○ If current is null then this node should be the last node in the list
    protected Node previous;

    //The number of elements stored in the list.
    protected int size;

    public MyLinkedList() {
    }

    //Adds the item before the current node.
    //○ This method adds the item between the previous node and the current
    //node.
    //○ If the current node is null the new element is added in the last position.
    //○ If the current node is the first node then the new element becomes the
    //new first node.
    //○ This method should run in O(1) time.
    public void addBefore(Type item) {
        // Create a new node that will point to 'current'
        Node newNode = new Node(item, current);

        if (current == null) {  // Case: Add at the end of the list
            if (size == 0) {  // If the list is empty, new node becomes the first node
                first = newNode;
            } else {  // Find the last node and add newNode there
                Node last = first;
                while (last.next != null) {
                    last = last.next;
                }
                last.next = newNode;
            }
        } else if (current == first) {  // Case: Add before the first node
            first = newNode;  // Update 'first' to point to newNode
        } else {  // Case: Add between 'previous' and 'current'
            previous.next = newNode;  // Link previous node to newNode
        }

        // Update 'previous' to point to the newly added node
        previous = newNode;
        size++;  // Increment the size of the list
    }

    // Adds the item after the current node.
    public void addAfter(Type item){
        if(current!= null ){
            current.next = new Node(item, current.next);
            size++;
        }

    }
    //Removes the current node and returns the element.
    public Type remove(){

        if (current == null) {
            return null;
        }
        Type removedItem = current.item;
        size--;

        current = current.next;
        if (previous != null) {
            previous.next = current;
        } else {
            first = current;
        }
        return removedItem;
    }


    // Helper method
    // Adds the item to the front of the list.
    public void addToFront (Type item){
        if((first == null || first.equals(current))){
            Node newNode = new Node(item, first);
            previous = newNode;
            first = newNode;
            current= first;
            size++;
        } else  {
            Node newNode = new Node(item, current);
            first = newNode;
            size++;
        }


    }
    //Helper method
    //adds the current node to the front of the list{
    public void moveToFront() {
        Type tempItem = current.item;
        if(previous != null &&previous.item.compareTo(current.item)> 0  ) {
            current.item= previous.item;
            previous.item= tempItem;

        } else if (previous != null &&previous.item.compareTo(current.item)< 0 ) {

            current.item =first.item;

        }
        first.item= tempItem;

    }






    //Returns the item stored in the current node.
    public Type current (){
        Type currentItem =null;
        if(current!= null){
            currentItem = current.item;
        }

        return currentItem;
    }

    //Sets the current node to be the first node.
    public Type first (){
        current = first;
        previous = null;
        return current.item;

    }
    // helper method to set the current node to be the first node.
    // no return type
    public void setCurrent (){
        current = first;
        previous = null;


    }
    //Sets the current node to be the next node in the list.
    public Type next(){


        previous = current;
        current = current.next;


        return current.item;
    }

    //Searches the nodes for the item and returns true if found (and false
    //otherwise).
    public boolean contains(Type item){
        boolean found = false;
        Node iterator;
        for (iterator = first; iterator != null; iterator = iterator.next) {
            if(iterator.item==item) {
                found =true;
            }
        }
        return found;
    }
    //Returns the field size.
    public int size(){
        return size;
    }


    //Returns true if the size is 0 and false otherwise.
    public boolean isEmpty(){
        return size == 0;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node iterator;
        String prefix = "";
        sb.append("[");
        for (iterator = first; iterator != null; iterator = iterator.next) {
            sb.append(prefix + iterator.toString());
            prefix = ", ";
        }
        sb.append("]");
        return sb.toString();
    }


}

