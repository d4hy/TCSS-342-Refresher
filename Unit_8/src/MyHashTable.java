/*
 * TCSS 342 - Spring 2023
 * Assignment 8- Hash Table
 */
public class MyHashTable<Key extends Comparable<Key>,Value> {
    //This is the capacity of the hash table.
    //This is set in the constructor and for testing with War and Peace please pass in a
    //capacity of 21^5 = 32768.
    protected Integer capacity;
    //This array stores the keys according to their hash value.
    protected Key[] keyBuckets;
    //This array stores the values according to the hash value of the
    //associated key.
    protected Value[] valueBuckets;

    //This list stores the unique keys stored in the hash table.
    //This list should only be used to iterate over all keys in the table.
    public MyArrayList<Key> keys = new MyArrayList<>();
    //The number of key-value pairs stored in the table.
    protected Integer size;

    //The total number of probes made when putting a new key-value pair.
    public Integer comparisons;
    //The maximum number of probes made to put any key-value pair.
    public Integer maxProbe;

    //MyHashTable - Takes the capacity of the hash table as an argument and initializes
    //the bucket arrays.
     MyHashTable(Integer capacity){
         comparisons= 0;
         size= 0;
         maxProbe=0;
        this.capacity= capacity;
        this.keyBuckets = (Key[]) new Comparable[capacity];
        this.valueBuckets=  (Value[]) new Object[capacity];
     }
     //This method will use the hashcode method of the key to produce an index in the
     //range 0 to capacity-1.
     // This method should run in O(1) time.
     private Integer hash(Key key){
         //used math.abs to guarantee positive value
         //mod to guarantee a value  that is between 0 and capacity-1
        return Math.abs(key.hashCode() % capacity);
     }
     //This method returns the value associated with the key.
     // It does so by following these steps:
     //i. Use hash to determine the index of the key.
     //ii. Find the key in the hash table using probing if necessary. Use a step size
     //of 1 for probing.
     //iii. Using the index of the found key, retrieve the value and return it.
     //iv. If no key is found, return null.
    public Value get(Key key){
         int searchIndex= hash(key);
         boolean notFound = true;
        while (notFound) {
            if (keyBuckets[searchIndex] != null && keyBuckets[searchIndex].equals(key)) {
                notFound = false;
            } else {
                searchIndex++;
                if (searchIndex > capacity - 1) { // doesn't exist
                    return null;
                }
            }

        }
        return valueBuckets[searchIndex];
    }
    //This method updates the value stored with the key.
    // It does so by following these steps:
    //i. Use hash to determine the index of the key.
    //ii. Find the key, or the first null location, in the hash table using probing if
    //necessary. Use a step size of 1 for probing.
    //iii. Using the index of the found location, write the value into the table.
    // This method should run in O(1) time under uniform hashing assumptions.
    public void put(Key key, Value value){
         int numProbes = 0;
         int index = hash(key);
        while (true) {
            comparisons++;
            numProbes++;

            if (numProbes > maxProbe) {
                maxProbe = numProbes;
            }

            if (keyBuckets[index] == null || keyBuckets[index] == key) {
                // If it reaches an empty index OR if it reaches an identical key, replace it.

                final int arrayListIndex = keys.indexOf(key);
                if (arrayListIndex != -1) { // already exists in arraylist
                    keys.set(arrayListIndex, key);
                } else {
                    size++;
                    keys.insert(key, index);
                }

                keyBuckets[index] = key;
                valueBuckets[index] = value;
                break;
            } else {
                index++;
            }


        }


    }
    final Integer size() {
        return this.size;
    }

    @Override
    public String toString() {
        String str = "[";

        String prefix = "";

        for (final Key k : keyBuckets) {
            if (k != null) {
                str += prefix + k + ":" + get(k);
                prefix = ", ";
            }

        }

        str += "]";
        return str;
    }



}
