package Group_Shop_Scheduling_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 3));
        tasks.add(new Task(2, 2));
        tasks.add(new Task(3, 4));
        tasks.add(new Task(4, 1));
        Scheduler scheduler = new Scheduler(tasks, 5);
        scheduler.runACO();
        double[][] pheromoneMatrix = {
                {1.0, 2.0, 3.0, 4.0},
                {2.0, 1.0, 5.0, 2.0},
                {3.0, 5.0, 1.0, 3.0},
                {4.0, 2.0, 3.0, 1.0}
        };
        scheduler.printSchedule();
        scheduler.printPheromoneMatrix(pheromoneMatrix);
    }
}