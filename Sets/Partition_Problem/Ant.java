package Partition_Problem;
// Created: September 2022
import java.util.Arrays;
public class Ant {
    private AntColony antColony;
    private int[] partition;
    private double[] pheromones;
    private int solutionValue;
    public Ant(AntColony antColony, int numItems) {
        this.antColony = antColony;
        this.partition = new int[numItems];
        this.pheromones = new double[numItems];
        this.solutionValue = 0;
    }
    public void constructSolution(int[] remainingItems) {
        Arrays.fill(partition, 0);
        int itemsPerPartition = remainingItems.length / 2;
        int itemsAdded = 0;
        while (itemsAdded < itemsPerPartition) {
            int randomIndex = (int) (Math.random() * remainingItems.length);
            if (remainingItems[randomIndex] == 1) {
                partition[randomIndex] = 1;
                remainingItems[randomIndex] = 0;
                itemsAdded++;
            }
        }
        System.out.println("Ant Solution: " + Arrays.toString(partition));
        System.out.println("Remaining Items: " + Arrays.toString(remainingItems));
        solutionValue = antColony.calculateValue(partition);
    }
    public int[] getPartition() {
        return Arrays.copyOf(partition, partition.length);
    }
    public int getSolutionValue() {
        return solutionValue;
    }
}