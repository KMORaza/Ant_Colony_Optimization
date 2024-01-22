package Group_Shop_Scheduling_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.List;
public class Ant {
    private int antId;
    private List<Task> path;
    public Ant(int antId) {
        this.antId = antId;
        this.path = new ArrayList<>();
    }
    public void addTaskToPath(Task task) {
        path.add(task);
    }
    public List<Task> getPath() {
        return path;
    }
    public int getAntId() {
        return antId;
    }
}