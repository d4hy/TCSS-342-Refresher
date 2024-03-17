public class MyArrayList<Type extends Comparable<Type>> {
    public long comparisons;
    protected Type[] list;
    protected int capacity;
    protected int size;

    public MyArrayList() {
        this.size = 0;
        this.capacity = 16;
        this.list = (Type[]) new Comparable[capacity];

    }

    public void insert(final Type object, final int index) {
        if (index > size || index < 0) {
            return;
        }

        if (size == capacity) {
            resize();
        }

        for (int i = size - 1; i >= index; i--) {
            list[i + 1] = list[i];
        }
        list[index] = object;
        size++;

    }

    public Type remove(final int index) {
        if (index > size || index < 0) {
            return null;
        }

        final Type removedItem = list[index];

        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }

        list[size - 1] = null;
        size--;

        return removedItem;

    }

    public boolean contains(final Type object) {
        for (int i = 0; i < size; i++) {
            comparisons++;
            if (list[i] != null && list[i].compareTo(object) == 0) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(final Type object) {
        for (int i = 0; i < size; i++) {
            if (list[i] != null && list[i].compareTo(object) == 0) {
                return i;
            }
        }
        return -1;
    }

    public Type get(final int index) {
        if (index > capacity - 1 || index < 0) {
            return null;
        }

        return list[index];

    }

    public void set(final int index, final Type object) {
        if (index > capacity - 1 || index < 0) {
            return;
        }

        list[index] = object;

    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void resize() {
        capacity *= 2;
        final Type[] resizedList = (Type[]) new Comparable[capacity];
        System.arraycopy(list, 0, resizedList, 0, size);
        list = resizedList;
    }


    public void sort() { // Via bubble sort
        boolean valueChanged = false;
        while (true) {
            valueChanged = false;
            for (int i = 0; i < size; i++) {
                Type nextItem = get(i + 1);
                Type currentItem = get(i);
                if (nextItem != null && currentItem.compareTo(nextItem) > 0) {
                    Type tempItem = nextItem;
                    set(i + 1, currentItem);
                    set(i, tempItem);
                    valueChanged = true;
                }
            }
            if (!valueChanged) {
                break;
            }
        }
    }


    @Override
    public String toString() {

        final StringBuilder s = new StringBuilder("[");
        if (size > 0) {
            s.append(list[0]);
            for (int i = 1; i < size; i++) {
                if (list[i] != null) {
                    s.append(", ").append(list[i]);
                }
            }
        }

        s.append("]");

        return s.toString();
    }
}
