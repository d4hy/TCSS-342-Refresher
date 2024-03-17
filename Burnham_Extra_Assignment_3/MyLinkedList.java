public class MyLinkedList<Type extends Comparable<Type>> {

    public long comparisons;
    protected Node first = null;
    protected Node current = null;
    protected Node previous = null;
    protected int size;

    public void addBefore(final Type object) {
        if (current == first) {
            final Node newNode = new Node(object, first);
            previous = newNode;
            first = newNode;

        } else {
            final Node newNode = new Node(object, current);
            previous.next = newNode;
            previous = newNode;
        }
        size++;
    }

    public void addAfter(final Type object) {
        if (current == null) {
            return;
        }
        current.next = new Node(object, current.next);
        size++;

    }

    public Type current() {
        return current == null ? null : current.item;
    }

    public Type first() {
        if (first == null) {
            return null;
        }

        current = first;
        previous = null;

        return current.item;

    }

    public Type next() {
        if (current == null) {
            return null;
        }

        previous = current;
        current = current.next;

        return current == null ? null : current.item;

    }

    public Type remove() {
        if (current == null) {
            return null;
        }

        final Type returnItem = current.item;

        if (current == first) {
            first = current.next;
            current = first;
        } else {
            previous.next = current.next;
            current = current.next;
        }

        size--;
        return returnItem;

    }

    public boolean contains(final Type item) {
        for (Node iterator = first; iterator != null; iterator = iterator.next) {
            comparisons++;
            if (iterator.item.compareTo(item) == 0) {
                return true;
            }
        }
        return false;
    }

    public void sort() { // Via bubble sort
        boolean valueChanged;
        do {
            valueChanged = false;
            for (Node iterator = first; iterator != null; iterator = iterator.next) {
                if (iterator.next != null && iterator.item.compareTo(iterator.next.item) > 0) {
                    final Type temp = iterator.item;
                    iterator.item = iterator.next.item;
                    iterator.next.item = temp;
                    valueChanged = true;
                }
            }

        } while (valueChanged);


    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");

        String prefix = "";

        for (Node iterator = first; iterator != null; iterator = iterator.next) {
            sb.append(prefix).append(iterator);
            prefix = ", ";
        }

        sb.append("]");

        return sb.toString();
    }


    protected class Node {

        Type item;

        Node next;

        Node(final Type item, final Node next) {
            this.item = item;
            this.next = next;
        }


        @Override
        public String toString() {
            return item.toString();
        }


    }
}
