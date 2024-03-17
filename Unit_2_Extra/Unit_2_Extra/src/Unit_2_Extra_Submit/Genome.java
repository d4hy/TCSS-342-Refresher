/*
 * TCSS 342 - Spring 2023
 * Assignment Extra Assignment 2 - Evolved Names
 */
import java.util.Random;

public class Genome implements Comparable<Genome> {
    /** characters representing the string encoded by the genome **/
    protected MyLinkedList<Character> genes;
    /** Target name **/
    private static final MyLinkedList<Character> target = setTarget(); // Or My name
    private static final double mutationRate = 0.05;
    private static final Random rng = new Random();


    private static MyLinkedList<Character> setTarget() {
        MyLinkedList<Character> localTarget= new MyLinkedList<>();
        String name = "CHRISTOPHER PAUL MARRIOTT";
        for (int i = 0; i < name.length(); i++) {
            localTarget.addToEnd(name.charAt(i));
        }

        return localTarget;
    }

    /** Genome default constructor **/
    public Genome() {
        genes = new MyLinkedList<>();
    }

    /** Genome copy Constructor **/
    public Genome(Genome genome) {
        genes = new MyLinkedList<>();
        for (genome.genes.first(); genome.genes.current() != null; genome.genes.next()) {
            this.genes.addToEnd(genome.genes.current());
        }
    }

    /** mutate the genes **/
    public void mutate() {
        //add a randomly selected character to a randomly selected position in genes
        if (rng.nextDouble() < mutationRate) {
            genes.first();
            int randPos;
            if (genes.size() != 0) {
                randPos = rng.nextInt(genes.size());
            } else {
                randPos = 0;
            }
            int currentPos = 0;
            while (currentPos < randPos) {
                genes.next();
                currentPos++;
            }

            if (currentPos != genes.size()) {
                genes.addAfter(getRandomChar());
            } else {
                genes.addBefore(getRandomChar());
            }
        }

        // Delete a single character from a randomly selected position
        if (genes.size() != 0 && rng.nextDouble() < mutationRate) {
            genes.first();
            int randPos;
            if (genes.size() != 0) {
                randPos = rng.nextInt(genes.size());
            } else {
                randPos = 0;
            }
            int currentPos = 0;
            while (currentPos < randPos) {
                genes.next();
                currentPos++;
            }
            genes.remove();
        }

        // Character is replaced by a randomly selected character
        for (int i = 0; i < genes.size(); i++) {
            if (rng.nextDouble() < mutationRate) {
                genes.addBefore(getRandomChar());
                genes.remove();
            }
            genes.next();
        }

    }

    /** update genes by crossing it over with the parent**/
    public void crossover(Genome parent) {
        MyLinkedList<Character> newGenes = new MyLinkedList<>();
        // Randomly choose one of the two lists, the provided parent or our own genes?
        this.genes.first();
        parent.genes.first();

        MyLinkedList<Character> currentParent;
        if (rng.nextBoolean()) {
            currentParent = this.genes;
        } else {
            currentParent = parent.genes;
        }
        while (currentParent.current() != null) {
            newGenes.addBefore(currentParent.current());
            newGenes.next();
            if (rng.nextBoolean()) {
                currentParent = this.genes;
            } else {
                currentParent = parent.genes;
            }
            this.genes.next();
            parent.genes.next();
        }

        genes = newGenes;
    }

    public int fitness() {
        if (genes.size() == 0) {
            return -target.size()*2;
        }
        int difference = 0;
        genes.first();
        target.first();
        for (int i = 0; i < genes.size() && i < target.size(); i++) {
            if (genes.current() != target.current()) {
                difference++;
            }
            genes.next();
            target.next();
        }

        return -((Math.abs(target.size() - genes.size())) + difference);
    }

    @Override
    public int compareTo(Genome genome) {
        return this.fitness() - genome.fitness();
    }

    private char getRandomChar() {
        int letterRange = 'Z'-'A';
        int value = rng.nextInt(letterRange + 4); // A-Z + 3 special characters _, -, and '
        if (value <= letterRange) {
            return (char)('A'+value);
        } else {
            return switch (value - letterRange) {
                case 1 -> ' ';
                case 2 -> '-';
                case 3 -> '\'';
                default -> throw new IllegalStateException("Unexpected value: when generating char");
            };
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(\"");
        for (genes.first(); genes.current() != null; genes.next()) {
            sb.append(genes.current());
        }

        sb.append("\", ").append(fitness()).append(")");
        return sb.toString();
    }
}
