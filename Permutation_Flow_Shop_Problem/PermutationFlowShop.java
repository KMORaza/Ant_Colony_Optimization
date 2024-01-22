package Permutation_Flow_Shop_Problem;
// Created: September 2022
import java.util.Random;
public class PermutationFlowShop {
    public static void main(String[] args) {
        int numAnts = 10;
        int numJobs = 5;
        Colony colony = new Colony(numAnts, numJobs);
        PheromoneMatrix pheromones = new PheromoneMatrix(numJobs);
        int iterations = 100;
        double evaporationRate = 0.5;
        double alpha = 1.0;
        double beta = 2.0;
        for (int i = 0; i < iterations; i++) {
            performAntsTour(colony, pheromones, alpha, beta);
            updatePheromones(pheromones, colony, evaporationRate);
        }
        for (Ant ant : colony.getAnts()) {
            System.out.println("Ant Tour: " + ant.getTour());
        }
    }
    private static void performAntsTour(Colony colony, PheromoneMatrix pheromones, double alpha, double beta) {
        Random rand = new Random();
        for (Ant ant : colony.getAnts()) {
            int randomJob = rand.nextInt(pheromones.getPheromones().length);
            ant.visit(randomJob);
        }
    }
    private static void updatePheromones(PheromoneMatrix pheromones, Colony colony, double evaporationRate) {
        Random rand = new Random();
        int randomJob1 = rand.nextInt(pheromones.getPheromones().length);
        int randomJob2 = rand.nextInt(pheromones.getPheromones()[randomJob1].length);
        double randomValue = rand.nextDouble();
        pheromones.updatePheromone(randomJob1, randomJob2, randomValue);
    }
}
