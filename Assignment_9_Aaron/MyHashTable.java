public class MyHashTable<Key extends Comparable<Key>, Value> {


    protected Integer capacity;

    protected Key[] keyBuckets;

    protected Value[] valueBuckets;

    protected Integer size = 0;

    public MyArrayList<Key> keys = new MyArrayList<>();

    public Integer comparisons = 0;

    public Integer maxProbe = 0;



    private final boolean chaining;

    private MyArrayList<KeyValuePair>[] chainBuckets;


    public MyHashTable(final Integer capacity, final boolean chaining) {
        this.chaining = chaining;
        this.capacity = capacity;


        if (chaining) {
            this.chainBuckets = new MyArrayList[capacity];
        } else {
            this.keyBuckets = (Key[]) new Comparable[capacity];
            this.valueBuckets = (Value[]) new Object[capacity];
        }
    }

    public MyHashTable(final Integer capacity) {
        this(capacity, false);
    }

    private Integer hash(final Key key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public Value get(final Key key) {
        int searchIndex = hash(key);

        if (chaining) {

            final MyArrayList<KeyValuePair> bucket = chainBuckets[searchIndex];

            if (bucket == null) {
                return null;
            }

            final int index = bucket.indexOf(new KeyValuePair(key, null));

            return index == -1 ? null : bucket.get(index).value;


        } else {
            while (true) {
                if (keyBuckets[searchIndex] != null && keyBuckets[searchIndex].equals(key)) {
                    break;
                } else if (keyBuckets[searchIndex] != null) {
                    searchIndex++;
                } else {
                    return null;
                }
            }

            return valueBuckets[searchIndex];
        }

    }

    public void put(final Key key, final Value value) {
        int searchIndex = hash(key);
        int numProbes = 0;

        if (chaining) {
            comparisons++;
            MyArrayList<KeyValuePair> bucket = chainBuckets[searchIndex];
            final KeyValuePair pair = new KeyValuePair(key, value);

            if (bucket == null) { // nothing stored at that index yet
                bucket = chainBuckets[searchIndex] = new MyArrayList<>();
            } else {
                comparisons += numProbes += bucket.size();
            }

            final int index = bucket.indexOf(pair);

            if (index == -1) { // doesn't exist in bucket
                bucket.insert(pair, bucket.size());
                size++;
            } else {
                bucket.set(index, pair); // overwrite old pair with new one
            }

            numProbes++;

            if (numProbes > maxProbe) {
                maxProbe = numProbes;
            }

        } else {
            while (true) {
                comparisons++;
                numProbes++;

                if (numProbes > maxProbe) {
                    maxProbe = numProbes;
                }

                if (keyBuckets[searchIndex] == null || keyBuckets[searchIndex].equals(key)) {
                    // If it reaches an empty index OR if it reaches an identical key, replace it.
                    final int arrayListIndex = keys.indexOf(key);
                    if (arrayListIndex != -1) { // already exists in arraylist
                        keys.set(arrayListIndex, key);
                    } else {
                        size++;
                        keys.insert(key, keys.size());
                    }
                    keyBuckets[searchIndex] = key;
                    valueBuckets[searchIndex] = value;
                    break;
                } else {
                    searchIndex++;
                }


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


        if (chaining) {
            for (final MyArrayList<KeyValuePair> l : chainBuckets) {
                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        final KeyValuePair pair = l.get(i);
                        str += prefix + pair;
                        prefix = ", ";
                    }
                }
            }


        } else {
            for (final Key k : keyBuckets) {
                if (k != null) {
                    str += prefix + k + ":" + get(k);
                    prefix = ", ";
                }

            }
        }

        str += "]";
        return str;
    }



    private class KeyValuePair implements Comparable<KeyValuePair> {

        public Key key;

        public Value value;

        public KeyValuePair(final Key k, final Value v) {
            this.key = k;
            this.value = v;
        }


        @Override
        public String toString() {
            return key + ":" + value;
        }


        @Override
        public int compareTo(final KeyValuePair other) {
            return this.key.compareTo(other.key);
        }

    }


}
