package Open_Job_Scheduling_Algorithm;
// Created: September 2022
public class Main {
    public static void main(String[] args) {
        int numAnts = 10;
        int numJobs = 5;
        int numMachines = 3;
        AntColony antColony = new AntColony(numAnts, numJobs);
        ACOAlgorithm acoAlgorithm = new ACOAlgorithm(antColony, numMachines);
        acoAlgorithm.runACO();
        displayResults(antColony);
    }
    private static void displayResults(AntColony antColony) {
        System.out.println("Final Ant Tours:");
        for (Ant ant : antColony.getAnts()) {
            System.out.println("Ant Tour: " + ant.getTour());
        }
    }
}
