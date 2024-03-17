/*
 * TCSS 342 - Spring 2023
 * Assignment 3 - Priority Queue
 */
public class MyPriorityQueue <Type extends Comparable<Type>>{
    //The values of the heap will be stored in this underlying MyArrayList.
    protected MyArrayList<Type> heap;

    public MyPriorityQueue(){
        heap = new MyArrayList<>();
    }
    //Inserts the item at the end of the array list.
   // Calls bubbleUp to move the item to the correct location.
    public void insert(Type item){

        heap.insert(item, heap.size());
        bubbleUp();

    }
    //Shifts the last element up to a position where it belongs to correct the
    //heap invariant.

    protected void bubbleUp(){
        //last element that was just inserted.
        int newAdd= heap.size()-1;
        boolean childLessThanParent =false;
        while (!childLessThanParent){
            Type currentItem = heap.get( newAdd);
            Type parentItem = heap.get(parent( newAdd));
            if(newAdd > 0 && currentItem.compareTo(parentItem) < 0){
                heap.set(parent(newAdd), currentItem);
                heap.set(newAdd, parentItem);
                newAdd= parent(newAdd);


            } else{
                childLessThanParent = true;
            }
        }


    }

    //Removes the first element and corrects the invariant.
    public Type removeMin() {
        if (heap.isEmpty()) {
            return null;
        }

        final Type firstItem = min();

        heap.set(0, heap.get(size() - 1));
        heap.set(size() - 1, firstItem);
        heap.remove(size() - 1);

        sinkDown();
        return firstItem;
    }



     //Shifts the element at the root of the heap down to a position where it belongs
    // to correct the heap invariant. Compares the parent node with its smaller child
     //nodes and swaps them if necessary until the heap invariant is restored.
    protected void sinkDown() {
        int iterator = 0;

// Iterate until the end of the heap
        while (iterator < heap.size() - 1) {


            // Get the parent node and its left and right child nodes
            Type parentItem = heap.get(iterator);
            Type childItemLeft = heap.get(left(iterator));
            Type childItemRight = heap.get(right(iterator));

            // If the left child node is null, exit the loop
            if (childItemLeft == null) {
                break;
            }

            // Determine the smaller child node and its index
            Type smallerItem;
            int smallerIndex;
            if (childItemRight == null || childItemLeft.compareTo(childItemRight) < 0) {
                smallerItem = childItemLeft;
                smallerIndex = left(iterator);
            } else {
                smallerItem = childItemRight;
                smallerIndex = right(iterator);
            }

            // If the smaller child node is less than the parent node, swap them
            // and continue to the next level in the heap
            if (smallerItem.compareTo(parentItem) < 0) {
                heap.set(smallerIndex, parentItem);
                heap.set(iterator, smallerItem);
                iterator = smallerIndex;
            } else {
                break;
            }
        }
    }

    //Returns the index of the parent node in the heap
    protected int parent(final int index) {
        return (index - 1) / 2;
    }
    // Returns the index of the left child node in the heap of the index passed in.
    protected int left(final int index) {
        return 2 * index + 1;
    }
    //Returns the index of the right child node in the heap of the index passed in.
    protected int right(final int index) {
        return 2 * index + 2;
    }
    //Returns the first element
    public Type min() {
        return heap.get(0);
    }

    //Returns the number of elements in the heap.
    public int size() {
        return heap.size();
    }
    // Returns true if the heap is empty and false otherwise.
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    //Returns the contents of the heap in String format.
    @Override
    public String toString() {
        return heap.toString();
    }
}
