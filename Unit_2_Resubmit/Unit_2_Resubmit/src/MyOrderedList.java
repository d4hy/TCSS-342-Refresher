/*
 * TCSS 342 - Spring 2023
 * Assignment 2 - Unique Words
 */
public class MyOrderedList<Type extends Comparable<Type>> {

    protected MyArrayList<Type> list;
    public long  comparisons;

    // Constructor
    public MyOrderedList() {
        list = new MyArrayList<>();
    }


    // Adds the item to the position of the list where it belongs.
    public void add (Type item){
        comparisons++;
        int index = list.size();
        while (true) {
            final Type prevItem = list.get(index - 1);
            if (prevItem != null && item.compareTo(prevItem) < 0) {
                index--;
                comparisons++;
            } else {
                list.insert(item, index);
                break;
            }

        }
    }
    //Removes the item from the list if found.
    public Type remove(final Type item) {

        return  list.remove(list.indexOf(item));
    }

    //Uses a binary search to search the list for the item and returns
    //true if found, and false, otherwise
    public boolean binarySearch(final Type item) {
        return binarySearch(item, 0, list.size() - 1);
    }

    //A private recursive method to be called by the public
    //binarySearch.
    protected boolean binarySearch(final Type item, final int start, final int finish) {
        if (finish < start) {
            return false;
        }
        final int midPoint = (finish + start) / 2;

        final int compareToValue = item.compareTo(list.get(midPoint));
        comparisons++;

        if (compareToValue == 0) {
            return true;
        } else if (compareToValue < 0) { // left
            return binarySearch(item, start, midPoint - 1);
        } else { // right
            return binarySearch(item, midPoint + 1, finish);
        }

    }
    //
    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
