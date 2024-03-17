public class MyPriorityQueue<Type extends Comparable<Type>> {

    protected MyArrayList<Type> heap = new MyArrayList<>();


    public void insert(final Type item) {
        heap.insert(item, heap.size());

        bubbleUp();
    }

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

    public Type min() {
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }


    @Override
    public String toString() {
        return heap.toString();
    }

    protected void bubbleUp() {

        int iterator = heap.size() - 1;

        while (true) {

            Type currentItem = heap.get(iterator);
            Type parentItem = heap.get(parent(iterator));

            if (iterator > 0 && currentItem.compareTo(parentItem) < 0) {
                heap.set(parent(iterator), currentItem);
                heap.set(iterator, parentItem);

                iterator = parent(iterator);

            } else {
                break;
            }
        }


    }

    protected void sinkDown() {
        int iterator = 0;

        while (iterator < heap.size() - 1) {

            final Type parentItem = heap.get(iterator);
            final Type childItemLeft = heap.get(left(iterator));
            final Type childItemRight = heap.get(right(iterator));

            if (childItemLeft == null) {
                break;
            }

            final Type smallerItem = (childItemRight == null || childItemLeft.compareTo(childItemRight) < 0)
                    ? childItemLeft
                    : childItemRight;

            final int smallerIndex = (smallerItem == childItemLeft) ? left(iterator) : right(iterator);


            if (smallerItem.compareTo(parentItem) < 0) {
                heap.set(smallerIndex, parentItem);
                heap.set(iterator, smallerItem);

                iterator = smallerIndex;

            } else {
                break;
            }

        }

    }

    protected int parent(final int index) {
        return (index - 1) / 2;
    }

    protected int left(final int index) {
        return 2 * index + 1;
    }

    protected int right(final int index) {
        return 2 * index + 2;
    }


}
