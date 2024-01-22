package Resource_Constrained_Project_Scheduling_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Task task1 = new Task(1, 5);
        Task task2 = new Task(2, 3);
        Task task3 = new Task(3, 4);
        Resource resource1 = new Resource(1, 10);
        Resource resource2 = new Resource(2, 8);
        List<Ant> ants = new ArrayList<>();
        ants.add(new Ant());
        ants.add(new Ant());
        AntColony antColony = new AntColony(ants);
        Scheduler scheduler = new Scheduler(List.of(task1, task2, task3),
                List.of(resource1, resource2),
                antColony);
        System.out.println("Initial Ant Colony: " + antColony);
        System.out.println("Initial Scheduler: " + scheduler);
        scheduler.schedule();
        System.out.println("Final Ant Colony: " + antColony);
        System.out.println("Final Scheduler: " + scheduler);
    }
}
