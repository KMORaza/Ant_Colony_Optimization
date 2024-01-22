package Job_Shop_Scheduling_Problem;
// Created: September 2022
public class Main {
    public static void main(String[] args) {
        int numJobs = 5;
        int numMachines = 3;
        Job[] jobs = new Job[numJobs];
        for (int i = 0; i < numJobs; i++) {
            int[] processingTimes = {};
            jobs[i] = new Job(i, processingTimes);
        }
        Machine[] machines = new Machine[numMachines];
        for (int i = 0; i < numMachines; i++) {
            machines[i] = new Machine(i);
        }
        int numAnts = 10;
        AntColony antColony = new AntColony(numAnts, jobs, machines);
        antColony.runAntOptimization();
        Ant bestAnt = antColony.getBestAnt();
        System.out.println("Best Solution Found: " + bestAnt.getTour());
        printBestSolutionDetails(bestAnt, jobs, machines);
    }
    private static void printBestSolutionDetails(Ant bestAnt, Job[] jobs, Machine[] machines) {
        System.out.println("Detailed Schedule:");
        for (int i = 0; i < machines.length; i++) {
            int machineId = machines[i].getMachineId();
            System.out.println("Machine " + machineId + ":");
            for (int jobId : bestAnt.getTour()) {
                int processingTime = jobs[jobId].getProcessingTime(machineId);
                System.out.println("Job " + jobId + " - Processing Time: " + processingTime);
            }
            System.out.println();
        }
    }
}