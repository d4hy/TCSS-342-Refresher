/*
 *w TCSS 342 - Spring 2023
 * Extra Assignment 3 - Trie
 */

import java.util.Arrays;

public class MyArrayList<Type extends Comparable<Type>> {

    // We store the elements of the list in this array.
    protected Type[] list;
    // The length of the array list and the current maximum size.
    // Initialized to 16
    protected int capacity;
    // The number of elements stored in the list
    protected int size;
    public long comparisons;

    // Constructor for the arrayList
    public MyArrayList() {
        capacity = 16;
        list = (Type[]) new Comparable[capacity];
        size = 0;
    }

    // Inserts the item at position index.
    public void insert(Type item, int index) {
        if (index <= size && index >= 0) {
            if (size == capacity) {
                resize();
            }
            for (int i = size - 1; i >= index; i--) {
                list[i + 1] = list[i];
            }
            list[index] = item;
            size++;
        }
    }

    // Doubles the capacity of the list.
    protected void resize() {
        capacity = capacity * 2;
        Type[] newList = Arrays.copyOf(list, capacity);
        list = newList;
    }

    // Removes the element at position index
    public Type remove(int index) {
        Type removedElement = list[index];
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[size - 1] = null;
        size--;
        return removedElement;
    }

    // Searches the list for the item and returns true if found (and false
    // otherwise).
    public boolean contains(Type item) {
        for (int i = 0; i < size; i++) {
            comparisons++;
            if (list[i] != null && list[i].compareTo(item) == 0) {
                return true;
            }
        }
        return false;
    }

    // Searches the list for the item and returns the index if found (and -1
    // otherwise).
    public int indexOf(Type item) {
        for (int i = 0; i < size; i++) {
            if (list[i] != null && list[i].compareTo(item) == 0) {
                return i;
            }
        }
        return -1;
    }

    // Sorts the list in ascending order. You may select any sorting procedure you like.
    // Using mergeSort
    public void sort() {
        mergeSort(list, 0, size - 1);
    }


    // Private helper method to recursively call Mergesort
    private void mergeSort(Type[] array, int left, int right) {
        // Base case: If the left index is less than the right index, there's more than
        // one element to be sorted.
        if (left < right) {
            // Calculate the middle index.
            int mid = (left + right) / 2;

            // Recursively sort the left and right halves of the array.
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            // Merge the sorted left and right halves together.
            merge(array, left, mid, right);
        }
    }

    // Helper method for sort.
    private void merge(Type[] array, int left, int mid, int right) {
        // Calculate the sizes of the two subarrays to be merged.
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays to hold the left and right subarrays.
        Type[] leftArray = Arrays.copyOfRange(array, left, mid + 1);
        Type[] rightArray = Arrays.copyOfRange(array, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        // Merge the left and right subarrays into the original array in sorted order.
        while (i < n1 && j < n2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy any remaining elements from the left and right subarrays, if any.
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // Returns the element stored at index and null if the index is out of bounds.
    public Type get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return list[index];
    }

    // Updates the element stored at index and does nothing if the index is out of
    // bounds.
    public void set(int index, Type item) {
        if (index >= 0 && index < size) {
            list[index] = item;
        }
    }

    // Returns the field size.
    public int size() {
        return size;
    }

    // Returns true if the size is 0 and false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns a string that has the contents of the list separated by commas
    // and spaces and enclosed in square brackets.
    public String toString() {
        StringBuilder contents = new StringBuilder();
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