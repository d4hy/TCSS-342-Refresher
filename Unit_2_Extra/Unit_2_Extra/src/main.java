public class main {
    public static void main (String [] args){
        Population  pop = new Population();
        while(pop.mostFit.fitness() < 0 ) {
           System.out.print("Generation " + pop.generation + " ");
            System.out.println(pop.mostFit);
            pop.nextGeneration();


       }


    }
}
