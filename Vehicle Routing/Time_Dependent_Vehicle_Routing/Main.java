package Time_Dependent_Vehicle_Routing;
// Created: September 2022
public class Main {
    public static void main(String[] args) {
        TDVRPACO tdvrpAco = new TDVRPACO();
        tdvrpAco.initialize();
        tdvrpAco.runACO();
        System.out.println("Final Solution:");
        tdvrpAco.printSolution();
    }
}
