package Multistage_Flowshop_Scheduling_Problem;
// Created: September 2022
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Job> jobs = readJobsFromFile("Multistage_Flowshop_Scheduling_Problem/jobs.txt");
        List<Machine> machines = readMachinesFromFile("Multistage_Flowshop_Scheduling_Problem/machines.txt");
        int numAnts = 10;
        double alpha = 1.0;
        double beta = 2.0;
        double evaporationRate = 0.5;
        Optimization aco = new Optimization(numAnts, alpha, beta, evaporationRate, jobs.size(), machines.size());
        aco.run(jobs, machines);
        printResults(aco);
        visualizePheromones(aco.getPheromoneMatrix());
    }
    private static List<Job> readJobsFromFile(String filePath) {
        List<Job> jobs = new ArrayList<>();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobs;
    }
    private static List<Machine> readMachinesFromFile(String filePath) {
        List<Machine> machines = new ArrayList<>();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return machines;
    }
    private static void printResults(Optimization aco) {
        Ant bestAnt = aco.getBestAnt();
        System.out.println("Best Tour: " + bestAnt.getTour());
        System.out.println("Best Tour Length: " + bestAnt.getTourLength());
    }

    private static void visualizePheromones(PheromoneMatrix pheromoneMatrix) {
        System.out.println("Pheromone Matrix:");
        for (int i = 0; i < pheromoneMatrix.getNumJobs(); i++) {
            for (int j = 0; j < pheromoneMatrix.getNumMachines(); j++) {
                System.out.print(pheromoneMatrix.getPheromone(i, j) + "\t");
            }
            System.out.println();
        }
    }
}
