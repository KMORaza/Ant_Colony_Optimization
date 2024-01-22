package Permutation_Flow_Shop_Problem;
// Created: September 2022
public class PheromoneMatrix {
    private double[][] pheromones;
    public PheromoneMatrix(int numJobs) {
        pheromones = new double[numJobs][numJobs];
    }
    public double[][] getPheromones() {
        return pheromones;
    }
    public double getPheromone(int i, int j) {
        return pheromones[i][j];
    }
    public void updatePheromone(int i, int j, double value) {
        pheromones[i][j] = value;
    }
}