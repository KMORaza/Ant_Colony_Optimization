package Single_Machine_Total_Weighted_Tardiness_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(0, 3, 5));
        jobs.add(new Job(1, 2, 8));
        jobs.add(new Job(2, 4, 3));
        jobs.add(new Job(3, 1, 9));
        int numAnts = 5;
        double alpha = 1.0;
        double beta = 2.0;
        double rho = 0.5;
        int numIterations = 100;
        AntColony antColony = new AntColony(jobs, numAnts, alpha, beta, rho);
        List<Job> solution = antColony.solve(numIterations);
        System.out.println("Best solution order:");
        for (Job job : solution) {
            System.out.println("Job " + job.getId() + " (Processing Time: " + job.getProcessingTime() + ")");
        }
        System.out.println("Total Weighted Tardiness: " + antColony.calculateTourWeight(solution));
    }
}