package Multistage_Flowshop_Scheduling_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Optimization {
    private static final int numGenerations = 100;
    private int numAnts;
    private double alpha;
    private double beta;
    private double evaporationRate;
    private PheromoneMatrix pheromoneMatrix;
    private List<Ant> ants;
    public Optimization(int numAnts, double alpha, double beta, double evaporationRate, int numJobs, int numMachines) {
        this.numAnts = numAnts;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;
        this.pheromoneMatrix = new PheromoneMatrix(numJobs, numMachines);
    }
    public void run(List<Job> jobs, List<Machine> machines) {
        ants = initializeAnts();
        for (int generation = 0; generation < numGenerations; generation++) {
            constructSolutions(ants, jobs, machines);
            updatePheromones(ants);
            evaporatePheromones();
        }
    }
    public PheromoneMatrix getPheromoneMatrix() {
        return pheromoneMatrix;
    }
    public Ant getBestAnt() {
        Ant bestAnt = ants.get(0);
        for (Ant ant : ants) {
            if (ant.getTourLength() < bestAnt.getTourLength()) {
                bestAnt = ant;
            }
        }
        return bestAnt;
    }
    private List<Ant> initializeAnts() {
        List<Ant> ants = new ArrayList<>();
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant());
        }
        return ants;
    }
    private void constructSolutions(List<Ant> ants, List<Job> jobs, List<Machine> machines) {
        Random random = new Random();
        for (Ant ant : ants) {
            List<Integer> remainingJobs = new ArrayList<>();
            for (int i = 0; i < jobs.size(); i++) {
                remainingJobs.add(i);
            }
            while (!remainingJobs.isEmpty()) {
                int selectedJobIndex = random.nextInt(remainingJobs.size());
                int selectedJob = remainingJobs.remove(selectedJobIndex);
                int selectedMachineIndex = random.nextInt(machines.size());
                int selectedMachine = machines.get(selectedMachineIndex).getId();
                ant.addToTour(selectedJob);
                ant.setTourLength(ant.getTourLength() + jobs.get(selectedJob).getProcessingTimes()[selectedMachine]);
                double currentPheromone = pheromoneMatrix.getPheromone(selectedJob, selectedMachine);
                double updatedPheromone = (1 - evaporationRate) * currentPheromone + (1 / ant.getTourLength());
                pheromoneMatrix.setPheromone(selectedJob, selectedMachine, updatedPheromone);
            }
        }
    }
    private void updatePheromones(List<Ant> ants) {
        for (Ant ant : ants) {
            List<Integer> tour = ant.getTour();
            double pheromoneDelta = 1 / ant.getTourLength();

            for (int i = 0; i < tour.size() - 1; i++) {
                int job = tour.get(i);
                int nextJob = tour.get(i + 1);

                double currentPheromone = pheromoneMatrix.getPheromone(job, nextJob);
                double updatedPheromone = (1 - evaporationRate) * currentPheromone + pheromoneDelta;
                pheromoneMatrix.setPheromone(job, nextJob, updatedPheromone);
            }
        }
    }
    private void evaporatePheromones() {
        for (int i = 0; i < pheromoneMatrix.getNumJobs(); i++) {
            for (int j = 0; j < pheromoneMatrix.getNumMachines(); j++) {
                double currentPheromone = pheromoneMatrix.getPheromone(i, j);
                double evaporatedPheromone = (1 - evaporationRate) * currentPheromone;
                pheromoneMatrix.setPheromone(i, j, evaporatedPheromone);
            }
        }
    }
}