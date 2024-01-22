package Single_Machine_Total_Tardiness_Problem;
//  Created: September 2022
import java.util.Arrays;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Job> jobs = Arrays.asList(
                new Job(1, 3, 10),
                new Job(2, 2, 8),
                new Job(3, 5, 15)
        );
        int antCount = 5;
        double alpha = 1.0;
        double beta = 2.0;
        AntColony antColony = new AntColony(jobs, antCount, alpha, beta);
        int iterations = 100;
        antColony.runACO(iterations);
        Ant bestAnt = antColony.getBestAnt();
        List<Job> bestSolution = bestAnt.getSolution();
        int totalTardiness = antColony.calculateTotalTardiness(bestSolution);
        System.out.println("Best Solution: " + bestSolution);
        System.out.println("Total Tardiness: " + totalTardiness);
    }
}