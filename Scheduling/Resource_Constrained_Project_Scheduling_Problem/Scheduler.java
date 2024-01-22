package Resource_Constrained_Project_Scheduling_Problem;
// Created: September 2022
import java.util.List;
public class Scheduler {
    private List<Task> tasks;
    private List<Resource> resources;
    private AntColony antColony;
    public Scheduler(List<Task> tasks, List<Resource> resources, AntColony antColony) {
        this.tasks = tasks;
        this.resources = resources;
        this.antColony = antColony;
    }
    public void schedule() {
        for (Ant ant : antColony.getAnts()) {
            List<Task> antTour = ant.getTour();
            for (int i = 0; i < antTour.size(); i++) {
                Task task = antTour.get(i);
                Resource selectedResource = findAvailableResourceForTask(task);
                if (selectedResource != null) {
                    antTour.set(i, new Task(task.getId(), selectedResource.getId()));
                    selectedResource.availability -= task.getDuration();
                } else {
                    antTour.subList(i, antTour.size()).clear();
                    break;
                }
            }
        }
        antColony.updatePheromones();
    }
    private Resource findAvailableResourceForTask(Task task) {
        for (Resource resource : resources) {
            if (resource.getId() == task.getId() && resource.availability >= task.getDuration()) {
                return resource;
            }
        }
        return null;
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public List<Resource> getResources() {
        return resources;
    }
    public AntColony getAntColony() {
        return antColony;
    }
    @Override
    public String toString() {
        return "Scheduler{" +
                "tasks=" + tasks +
                ", resources=" + resources +
                ", antColony=" + antColony +
                '}';
    }
}