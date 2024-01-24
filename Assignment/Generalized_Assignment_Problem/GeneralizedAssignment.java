package Generalized_Assignment_Problem;
// Created: September 2022
public class GeneralizedAssignment {
    private int numJobs;
    private int numAgents;
    private double[][] costMatrix;
    public GeneralizedAssignment(int numJobs, int numAgents, double[][] costMatrix) {
        this.numJobs = numJobs;
        this.numAgents = numAgents;
        this.costMatrix = costMatrix;
    }
    public double getCost(int job, int agent) {
        return costMatrix[job][agent];
    }
    public int getNumJobs() {
        return numJobs;
    }
    public int getNumAgents() {
        return numAgents;
    }
}