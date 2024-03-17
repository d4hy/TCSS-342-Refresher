/*
 * TCSS 342 - Spring 2023
 * Assignment Extra Assignment 2 - Evolved Names
 */
public class MyLinkedList <Type extends Comparable<Type>> {
    public long comparisons;
    protected Node first;
    protected Node current;
    protected Node previous;
    protected int size;

    // Add the item before the current node
    public void addBefore(Type item) {
        if (previous == null) {
            Node newNode = new Node(item, first);
            previous = newNode;
            first = newNode;
        } else {
            Node newNode = new Node(item, current);
            previous.next = newNode;
            previous = newNode;
        }
        size++;
    }

    // Add the item after the current node
    public void addAfter(Type item) {
        if (current != null) {
            current.next = new Node(item, current.next);
            size++;
        }
    }

    // Get the current Item
    public Type current() {
        if (current == null) {
            return null;
        }
        return current.item;
    }

    // Set the current node to be the first node
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
        if (current == null) {
            return null;
        }
        return current.item;
    }

    public Type remove() {
        if (current == null) {
            return null;
        }
        Type removedItem = current.item;
        size--;

        current = current.next;
        if (previous != null) {
            previous.next = current;
        } else {
            first = current;
        }
        return removedItem;
    }

    public boolean contains(Type item) {
        for (Node itr = first; itr != null; itr = itr.next) {
            comparisons++;
            if (itr.item.compareTo(item) == 0) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void addToFront(Type item) {
        Node newNode = new Node(item, first);
        if (current == first) {
            previous = newNode;
        } else if (current == null) {
            current = newNode;
        }
        first = newNode;
        size++;
    }

    public void addToEnd(Type item) {
        Node newNode = new Node(item, null);
        if (first == null) {
            first = newNode;
            current = first;
        } else {
            // Move current to end of list
            // Does this really count as O(1)?
            Node itr = current;
            while (itr.next != null) {
                itr = itr.next;
            }
            itr.next = newNode;
        }
        size++;
    }

    // Move the current Node to the front of the list
    public void moveToFront() {
        addToFront(remove());
    }

    public void swapWithPrevious() {
        if (current != null && current != first) {
            swapItem(previous, current);
        }
    }

    /** Swap the *ITEM* between two nodes **/
    private void swapItem(Node n1, Node n2) {
        Type tmp = n1.item;
        n1.item = n2.item;
        n2.item = tmp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        String prefix = "";
        for (Node iterator = first; iterator != null; iterator = iterator.next) {
            sb.append(prefix).append(iterator.item);
            prefix = ", ";
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * Sort the list in ascending order
     */
    public void sort() {
        if (size <= 1) {
            return; // already sorted
        }
        first = mergeSort(first);
        current = first;
        previous = null;
    }

    /** Bubble Sort linked list **/
    private void bubbleSort() {
        for (int i = 0; i < size; i++) {
            Node iterator = first;
            // By definition after each pass (i) our highest value should be the max as it has bubbled to the top
            // hence -i
            for (int j = 0; iterator != null && j < size-i-1; j++) {
                if (iterator.next != null && iterator.item.compareTo(iterator.next.item) > 0) {
                    swapItem(iterator, iterator.next);
                }
                iterator = iterator.next;
            }
        }
    }


    /** Sort the list using merge sort **/
    public Node mergeSort(Node list) {
        if (list == null || list.next == null) {
            return list;
        }

        // Find the middle node.
        Node middle = getMiddle(list);
        Node left_list = list;

        // Create a new linked list and point first to middle+1
        Node right_list = null;

        // add middle.next
        if (middle.next != null) {
            right_list = middle.next;
        } else { // or if only two elements (no middle.next) split
            right_list = left_list.next;
            left_list.next = null;
        }

        // Break off middle from the left list
        middle.next = null;

        // Merge sort left and right lists
        left_list = mergeSort(left_list);
        right_list = mergeSort(right_list);

        return merge(left_list, right_list);
    }

    /** Get the middle node **/
    private Node getMiddle(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /** Merge two lists together **/
    private Node merge(Node list1, Node list2) {
        if (list1 == null && list2 == null) {
            return null;
        }

        // Dummy head/tail for operations
        Node newHead = null;
        Node newTail = null;

        while(list1 != null && list2 != null) {
            Node next_node;
            if (list1.item.compareTo(list2.item) < 0) {
                next_node = list1;
                list1 = list1.next;
            } else {
                next_node = list2;
                list2 = list2.next;
            }

            if (newTail != null) {
                newTail.next = next_node;
            } else {
                newHead = next_node;
                newTail = next_node;
                newHead.next = newTail;
            }
            newTail = next_node;
        }

        // Add any remaining nodes from list1 or list2 to the end of the merged list
        while (list1 != null) {
            newTail.next = list1;
            newTail = newTail.next;
            list1 = list1.next;
        }

        while (list2 != null) {
            newTail.next = list2;
            newTail = newTail.next;
            list2 = list2.next;
        }
        return newHead;
    }

    protected class Node {
        public Type item;
        public Node next;

        Node(Type theItem, Node theNodeRef) {
            item = theItem;
            next = theNodeRef;
        }
        @Override
        public String toString() {
            return item.toString();
        }
    }
}
