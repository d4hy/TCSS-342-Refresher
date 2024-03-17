public class MyOrderedList<Type extends Comparable<Type>> {

    public long comparisons;

    protected MyArrayList<Type> list;

    public MyOrderedList() {
        list = new MyArrayList<>();
    }

    public void add(final Type item) {
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

    public Type get(final int index) {
        return list.get(index);

    }

    public Type remove(final Type item) {
        final int index = list.indexOf(item);
        return index == -1 ? null : list.remove(index);
    }

    public Type binarySearch(final Type item) {
        return binarySearch(item, 0, list.size() - 1);
    }

    private Type binarySearch(final Type item, final int start, final int finish) {
        if (finish < start) {
            return null;
        }
        final int midPoint = (finish + start) / 2;
        final Type midPointItem = list.get(midPoint);

        final int compareToValue = item.compareTo(midPointItem);
        comparisons++;

        if (compareToValue == 0) {
            return midPointItem;
        } else if (compareToValue < 0) { // left
            return binarySearch(item, start, midPoint - 1);
        } else { // right
            return binarySearch(item, midPoint + 1, finish);
        }

    }

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
