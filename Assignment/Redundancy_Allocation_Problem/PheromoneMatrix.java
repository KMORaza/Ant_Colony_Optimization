package Redundancy_Allocation_Problem;
// Created: September 2022
public class PheromoneMatrix {
    private double[][] pheromones;
    public PheromoneMatrix(int size) {
        this.pheromones = new double[size][size];
    }
    public double[] getPheromones(int node) {
        return pheromones[node];
    }
    public void evaporatePheromones(double evaporationRate) {
        for (int i = 0; i < pheromones.length; i++) {
            for (int j = 0; j < pheromones[i].length; j++) {
                pheromones[i][j] *= (1 - evaporationRate);
            }
        }
    }
    public void updatePheromones(Solution solution, double pheromoneChange) {
        for (int i = 0; i < pheromones.length; i++) {
            for (int j = 0; j < pheromones[i].length; j++) {
                pheromones[i][j] += pheromoneChange;
            }
        }
    }
    public void printPheromones() {
        for (int i = 0; i < pheromones.length; i++) {
            for (int j = 0; j < pheromones[i].length; j++) {
                System.out.print(pheromones[i][j] + " ");
            }
            System.out.println();
        }
    }
}