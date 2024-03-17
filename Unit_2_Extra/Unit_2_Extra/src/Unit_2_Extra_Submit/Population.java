/*
 * TCSS 342 - Spring 2023
 * Assignment Extra Assignment 2 - Evolved Names
 */
import java.util.Random;

public class Population {
    /** The Genomes in the population. **/
    public MyLinkedList<Genome> population = new MyLinkedList<>();
    /** The Genome with the maximum fitness **/
    public Genome mostFit = new Genome();
    /** The number of times nextGeneration has been called **/
    public int generation;
    /** The number of genomes in the population **/
    private final int size = 100;

    private final Random rng = new Random();


    public Population() {
        while (population.size() < size) {
            population.addBefore(new Genome());
        }
        population.first();
    }

    public void nextGeneration() {
        generation++;
        population.first();

        // loop to half way point
        for (int i = 0; i < size/2; i++) {
            population.remove();
        }

        while (population.size() < size) {
            Genome genomeClone;
            population.first();
            if (rng.nextBoolean()) { // 50% chance
                // pick a remaining genome at random and clone it
                int randPos = rng.nextInt(population.size());
                int currentPos = 0;
                while (currentPos < randPos) {
                    population.next();
                    currentPos++;
                }

                genomeClone = new Genome(population.current());
                // then mutate the clone
                genomeClone.mutate();
            } else {
                // pick a remaining genome at random and clone it
                // pick two positions to traverse to
                // then do them in order
                int randPos1 = rng.nextInt(population.size());
                int randPos2 = rng.nextInt(population.size());

                int currentPos = 0;
                while (currentPos < Math.min(randPos1, randPos2)) {
                    population.next();
                    currentPos++;
                }
                genomeClone = new Genome(population.current());

                // Another random genome to crossover with
                while (currentPos < Math.max(randPos1, randPos2)) {
                    population.next();
                    currentPos++;
                }
                genomeClone.crossover(population.current());
                // mutate the result
                genomeClone.mutate();
            }
            population.addBefore(genomeClone);
        }

        // Sort population by fitness
        population.sort();
        // update mostFit variable
        population.first();
        int currentPos = 0;
        while (currentPos < population.size()-1) {
            currentPos++;
            population.next();
        }

        mostFit = population.current();
    }

    @Override
    public String toString() {
        return population.toString();
    }
}
