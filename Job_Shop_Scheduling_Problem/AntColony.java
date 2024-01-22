package Job_Shop_Scheduling_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class AntColony {
    private List<Ant> ants;
    private int[][] pheromoneMatrix;
    private Job[] jobs;
    private Machine[] machines;
    private int numAnts;
    public AntColony(int numAnts, Job[] jobs, Machine[] machines) {
        this.numAnts = numAnts;
        this.jobs = jobs;
        this.machines = machines;
        ants = new ArrayList<>();
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant());
        }
        pheromoneMatrix = new int[jobs.length][jobs.length];
    }
    public void runAntOptimization() {
        int maxIterations = 100;
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            for (Ant ant : ants) {
                constructSolution(ant);
                updatePheromones(ant);
            }
            evaporatePheromones();
            Ant bestAnt = getBestAnt();
            System.out.println("Iteration " + iteration + ": Best Solution - " + bestAnt.getTour());
        }
    }
    private void constructSolution(Ant ant) {
        int numJobs = jobs.length;
        int[] remainingJobs = new int[numJobs];
        for (int i = 0; i < numJobs; i++) {
            remainingJobs[i] = i;
        }
        for (int i = 0; i < numJobs; i++) {
            int selectedJobIndex = selectJob(ant, remainingJobs);
            ant.addToTour(selectedJobIndex);
            remainingJobs = removeElement(remainingJobs, selectedJobIndex);
        }
    }
    private int selectJob(Ant ant, int[] remainingJobs) {
        Random random = new Random();
        return remainingJobs[random.nextInt(remainingJobs.length)];
    }
    private void updatePheromones(Ant ant) {
        List<Integer> tour = ant.getTour();
        int tourLength = tour.size();
        for (int i = 0; i < tourLength - 1; i++) {
            int job1 = tour.get(i);
            int job2 = tour.get(i + 1);
        }
    }

    private void evaporatePheromones() {
    }
    public Ant getBestAnt() {
        Ant bestAnt = ants.get(0);
        double bestTourLength = calculateTourLength(bestAnt);
        for (Ant ant : ants) {
            double tourLength = calculateTourLength(ant);
            if (tourLength < bestTourLength) {
                bestTourLength = tourLength;
                bestAnt = ant;
            }
        }
        return bestAnt;
    }
    private double calculateTourLength(Ant ant) {
        List<Integer> tour = ant.getTour();
        double tourLength = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            int job1 = tour.get(i);
            int job2 = tour.get(i + 1);
        }
        return tourLength;
    }
    private int[] removeElement(int[] array, int element) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            int[] newArray = new int[array.length - 1];
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, newArray.length - index);
            return newArray;
        } else {
            return array;
        }
    }
}