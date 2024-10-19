package unit1;

/*
 * TCSS 342 - Spring 2023
 * Assignment 1 - Lists
 */
//The <Type> in this case is called a type parameter, and it works like a placeholder for the actual type (such as Integer, String, Double, or a user-defined type) that will be used when you instantiate the class.
public class MyArrayList<Type> {

    //We store the elements of the list in this array.
    protected Type[] list;
    //The length of the array list and the current maximum size.
    //Initialized to 16
    protected int capacity;


    //The number of elements stored in the list
    protected int size;

    //Constructor for the arrayList
    public MyArrayList(){
        capacity = 16;
        list = (Type[]) new Object[capacity];
        size =0;

    }

    //Inserts the item at position index.
    //Any elements after the inserted element shuffle down one position to make room
    //for the new element.
    //If the index is greater than the size or is negative then this method does
    //nothing.
    //This method calls resize if there is not enough room in the array for the new
    //element.
    // This method should run in O(i) time where i is the number of elements shuffled.
    public void insert(Type item, int index){
        if (index <= size  && index >=0  ) {
            if(size == capacity){
                resize();
        }
            Type previous = item;
            for(int i = index ; i<=size; i++ ){
                Type current = list[i];
                list[i] = previous;
                previous = current;

            }


            size++;


    }
    }
    //Doubles the capacity of the list.

    protected void resize() {
        capacity = capacity*2;
        Type[] newList = (Type[]) new Object[capacity];
        for(int i = 0; i<size; i++){
            newList[i] = list[i];
        }
        list = newList;
    }

    //Removes the element at position index
    //○ Returns the element that was removed.
    //○ Any elements after the removed element shuffle down to fill the empty position.
    //○ If the index is out of bounds this method does nothing and returns null.
    //○ This method should run in O(i) time where i is the number of elements shuffled.
    public Type remove(int index){
        Type itemToReturn = list[index];

        for(int i = index; i< size -1; i++){
            list[i] = list[i+1];

        }
        list[size-1] = null;
        size--;
        return itemToReturn;

    }
    // Searches the list for the item and returns true if found (and false
    //otherwise).
    //○ This method should run in O(n) time.
    public boolean contains (Type item) {


        return  indexOf(item) != -1;
    }
    // Searches the list for the item and returns the index if found (and -1
    //otherwise).
    //○ This method should run in O(n) time.
    public int indexOf (Type item) {
        int numToReturn = -1;

        for(int i = 0; i< size; i++) {
            if(list[i].equals(item)){
                return i;

            }
        }
        return numToReturn;
    }

    // Returns the element stored at index and null if the index is out of bounds.
    //This method should run in O(1) time.
    public Type get (int index){
        if(index <0 || index>= size ) {
            return null;
        }
        return list[index];
    }
    //Updates the element stored at index and does nothing if the index is out of
    // bounds.
    //This method should run in O(1) time.
    public void set (int index,Type item){
        if(index <size && index>=0){
            list[index]= item;
        }
    }



    //Returns true if the size is 0 and false otherwise.
    public boolean isEmpty(){
        return size == 0;
    }

    //Returns the field size.
    public int size(){
        return size;
    }
    //toString - Returns a string that has the contents of the list separated by commas
    //and spaces and enclosed in square brackets.
    //○ Example: [1, 2, 3, 4]
    //○ This method should run in O(n) time

    public String toString(){
        final StringBuilder string = new StringBuilder();
        string.append("[");

        for(int i=0; i<size-1; i++){
            string.append(get(i));
            string.append(", ");

        }
        string.append(list[size-1]);
        string.append("]");
        return string.toString();
    }


}
