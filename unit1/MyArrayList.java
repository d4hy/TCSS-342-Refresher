package unit1;

/*
 * TCSS 342 - Spring 2023
 * Assignment 1 - Lists
 */
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
        Type[] newList=(Type[])new Object[capacity];
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


        return  indexOf(item) != -1;
    }
    // Searches the list for the item and returns the index if found (and -1
    //otherwise).
    public int indexOf (Type item) {
        int indexOfItem = -1;
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }

        }
        return indexOfItem;
    }

    // Returns the element stored at index and null if the index is out of bounds.
    public Type get (int index){
        Type element;
        if( index <0||index>=size) {
            element= null;
        } else {
            element = list[index];
        }
        return element;
    }
    //Updates the element stored at index and does nothing if the index is out of
    // bounds.
    public void set (int index,Type item){
        if(index <size && index>=0){
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
    public String toString (){
        final StringBuilder contents = new StringBuilder();
        contents.append("[");
        for(int i =0 ; i< size-1; i++){
            contents.append(list[i]);
            contents.append(", ");
        }
        contents.append(list[size-1]);
        contents.append("]");
        return contents.toString();
    }



}
