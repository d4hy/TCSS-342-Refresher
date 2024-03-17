/*
 * TCSS 342 - Spring 2023
 * Extra Assignment 3 - Trie
 */
public class MyTrie {

    public long comparisons;

    protected Node root;

    protected int size;


    public MyTrie() {
        root = new Node(' ');
    }

    public void insert(final String item) {
        Node currentNode = root;
        int iterator = 0;


        while (true) {
            comparisons++;
            Node iteratorNode = new Node(item.charAt(iterator));
            Node containingNode = currentNode.children.binarySearch(iteratorNode);

            if (containingNode == null) { // doesnt exist
                currentNode.addChildren(iteratorNode);
                currentNode = iteratorNode;

            } else { // exists
                currentNode = containingNode;
            }

            iterator++;
            if (iterator == item.length()) {
                currentNode.terminal = true;
                size++;
                return;
            }
        }

    }

    public void remove(final String item) {
        Node currentNode = root;
        int iterator = 0;
        while (true) {
            final Node iteratorNode = new Node(item.charAt(iterator));
            final Node containingNode = currentNode.children.binarySearch(iteratorNode);

            if (containingNode == null) { // doesnt exist
                return;

            } else { // exists
                currentNode = containingNode;
            }

            iterator++;
            if (iterator == item.length()) {
                size--;
                currentNode.terminal = false;
                break;
            }
        }
    }

    public boolean find(final String item) {
        Node currentNode = root;

        int iterator = 0;
        while (true) {
            comparisons++;
            final Node iteratorNode = new Node(item.charAt(iterator));
            final Node containingNode = currentNode.children.binarySearch(iteratorNode);

            if (containingNode == null) { // doesnt exist
                return false;

            } else { // exists
                currentNode = containingNode;
            }

            iterator++;
            if (iterator == item.length()) {
                return currentNode.terminal;
            }


        }

    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        addWords(root, "", sb);

        sb.append("]");
        sb.replace(sb.length() - 3, sb.length() - 1, "");
        return sb.toString();
    }

    protected void addWords(Node node, String str, final StringBuilder output) {
        if (node == null) {
            return;
        }

        str += node.character == ' ' ? "" : node.character;

        if (node.terminal) {
            output.append(str).append(", ");
        }


        for (int i = 0; i < node.children.size(); i++) {
            addWords(node.children.get(i), str, output);
        }


    }


    private class Node implements Comparable<Node> {
        public boolean terminal;

        public char character;

        public MyOrderedList<Node> children = new MyOrderedList<>();

        public Node(final char c) {
            this.character = c;
        }

        public void addChildren(final Node node) {
            this.children.add(node);
        }


        @Override
        public int compareTo(final Node otherNode) {
            return Character.compare(character, otherNode.character);
        }
    }

}
