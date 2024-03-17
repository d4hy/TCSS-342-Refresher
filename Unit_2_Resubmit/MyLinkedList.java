/*
 * TCSS 342 - Spring 2023
 * Assignment 2 - Unique Words
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
    protected Node first;
    //A reference to the current node in the list.

    protected Node current;
    //A reference to the node before the current node in the list.
    protected Node previous;

    //The number of elements stored in the list.
    protected int size;

    public long comparisons;

    //Constructor
    public MyLinkedList(){
    }

    //- Adds the item before the current node.
    public void addBefore(Type item) {
        if (first == null || first.equals(current)) {
            Node newNode = new Node(item, first);
            previous = newNode;
            first = newNode;
        } else {
            Node newNode = new Node(item, current); // pointer to current
            previous.next = newNode;
            previous = newNode;
        }
        size++;
    }

    // Adds the item after the current node.
    public void addAfter(Type item){
        if(current!= null ){
            Node newNode = new Node(item, current.next); // pointer to node after current.
            current.next =newNode;
            size++;
        }

    }
    //Removes the current node and returns the element.
    public Type remove(){

        Type removedElement = null;
        if(current!= null && first !=null) {
            if (current == first) {
                removedElement= current.item;
                first = current.next;
                current = first;
                size--;
            } else {
                removedElement= current.item;
                previous.next = current.next;
                current = current.next;

                size--;
            }

        }
        return removedElement;
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
    public void moveToFront(Type item) {
        remove();
        first();
        addBefore(item);


    }
    //Helper method
    // swapped with its neighbor towards the front of the list.
    public void swapWithPrevious (Type item){
        if(current()!= null && current!= first){
            Type temp= previous.item;
            previous.item =current.item;
            current.item= temp;
        }
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
        Type currentItem =null;
        if(first != null) {
            current = first;
            previous = null;
            currentItem = current.item;
        }
            return currentItem;

    }

    //Sets the current node to be the next node in the list.
    public Type next(){
        Type currentItem =null;
            if(current!=null && current.next!= null){

            previous = current;
            current = current.next;
            currentItem = current.item;
        }


        return currentItem;
    }

    //Searches the nodes for the item and returns true if found (and false
    //otherwise).
    public boolean contains(Type item){
        boolean found = false;
        Node iterator;
        for (iterator = first; iterator != null; iterator = iterator.next) {
            comparisons++;
            if(iterator.item.compareTo(item) == 0) {
                found =true;
                break;
            }
        }
        return found;
    }
    //Sorts the list in ascending order. You may select any sorting procedure you like
    //from this list:
    public void sort() { // Via Bubble sort
        boolean inversion = false;
        for(int i = 0; i < size()-1; i++) {
            for (Node iterator = first; iterator != null; iterator = iterator.next) {
                if (iterator.next != null && iterator.item.compareTo(iterator.next.item) > 0) {
                    final Type temp = iterator.item;
                    iterator.item = iterator.next.item;
                    iterator.next.item = temp;
                    inversion = true;
                }
            }

            if (!inversion) {
                break;
            }
        }
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

