package Multistage_Flowshop_Scheduling_Problem;
// Created: September 2022
public class PheromoneMatrix {
    private double[][] pheromones;
    public PheromoneMatrix(int numJobs, int numMachines) {
        this.pheromones = new double[numJobs][numMachines];
        initializePheromones();
    }
    private void initializePheromones() {
        double initialPheromoneValue = 0.1;
        for (int i = 0; i < pheromones.length; i++) {
            for (int j = 0; j < pheromones[i].length; j++) {
                pheromones[i][j] = initialPheromoneValue;
            }
        }
    }
    public double getPheromone(int job, int machine) {
        return pheromones[job][machine];
    }
    public void setPheromone(int job, int machine, double value) {
        pheromones[job][machine] = value;
    }
    public int getNumJobs() {
        return pheromones.length;
    }
    public int getNumMachines() {
        return pheromones[0].length;
    }
}