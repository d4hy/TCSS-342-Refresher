/*
 * TCSS 342 - Spring 2023
 * Extra Assignment 3 - Trie
 */
import java.util.Arrays;


public class MyArrayList<Type extends Comparable<Type>> {

    //We store the elements of the list in this array.
    protected Type[] list;
    //The length of the array list and the current maximum size.
    //Initialized to 16
    protected int capacity;


    //The number of elements stored in the list
    protected int size;

    public long comparisons;

    //Constructor for the arrayList
    public MyArrayList(){
        capacity = 16;
        list = (Type[]) new Comparable[capacity];
        size =0;

    }

    //Inserts the item at position index.
    public void insert(Type item, int index){
        if(index<= size&& index>=0){
            if(size == capacity){
                resize();
            }

            for(int i= size-1 ; i>=index; i--){
                list[i+1] = list[i];

            }
            list[index] =item;
            size++;
        }
    }
    //Doubles the capacity of the list.
    protected void resize(){
        capacity= capacity*2;
        Type[] newList=(Type[])new Comparable[capacity];
        for( int i =0; i< size; i++){
            newList[i]= list[i];
        }
        list= newList;
    }

    //Removes the element at position index
    public Type remove(int index){
        Type removedElement = list[index];

        for(int i =index; i< size -1; i++){
            list[i] = list[i+1];
        }
        list[size - 1] = null;
        size--;
        return removedElement;
    }
    // Searches the list for the item and returns true if found (and false
    //otherwise).
    public boolean contains (Type item) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            comparisons++;
            if (list[i] != null && list[i].compareTo(item) == 0) {
                found = true;
                break;
            }
        }
        return found;
    }



    // Searches the list for the item and returns the index if found (and -1
    //otherwise).
    public int indexOf (Type item) {
        int indexOfItem = -1;
        for (int i = 0; i < size; i++) {
            if (list[i] != null && list[i].compareTo(item) == 0) {
                indexOfItem = i;
            }

        }
        return indexOfItem;
    }

    //Sorts the list in ascending order. You may select any sorting procedure you like
    // Using mergeSort
    public void sort(){
        sort(list);
    }
    // private helper method to recursively call Mergesort
    private void sort(Type[] array) {
        if (array.length > 1) {
            int mid = array.length / 2;
            Type[] left = Arrays.copyOfRange(array, 0, mid);
            Type[] right = Arrays.copyOfRange(array, mid, array.length);
            sort(left);
            sort(right);
            merge(array, left, right);
        }
    }
    //helper method for sort.
    private  void merge(Type[] array, Type[] left, Type[] right) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            Type leftValue =  left[i];
            Type rightValue =  right[j];
            if(leftValue !=null && rightValue !=null) {
                if (leftValue.compareTo(rightValue) < 0) {
                    array[k++] = left[i++];
                } else {
                    array[k++] = right[j++];
                }
            }else {
                break;
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }




    // Returns the element stored at index and null if the index is out of bounds.
    public Type get (int index){
        Type element;
        if( index <0||index>size) {
            element= null;
        } else {
            element = list[index];
        }
        return element;
    }
    //Updates the element stored at index and does nothing if the index is out of
    // bounds.
    public void set (int index,Type item){
        if(index <=size && index>=0){
            list[index]= item;
        }
    }

    //Returns the field size.
    public int size (){
        return size;
    }

    //Returns true if the size is 0 and false otherwise.
    public boolean isEmpty(){
        return size == 0;
    }

    // Returns a string that has the contents of the list separated by commas
    //and spaces and enclosed in square brackets.
    public String toString () {
        final StringBuilder contents = new StringBuilder();
        contents.append("[");
        if (size > 0) {
            for (int i = 0; i < size - 1; i++) {
                contents.append(list[i]);
                contents.append(", ");
            }
            contents.append(list[size - 1]);


        }
        contents.append("]");
        return contents.toString();
    }


}
