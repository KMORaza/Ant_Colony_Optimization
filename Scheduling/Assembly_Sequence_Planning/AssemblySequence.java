package Assembly_Sequence_Planning;
// Created: September 2022
import java.util.List;
public class AssemblySequence {
    private List<Task> tasks;
    public AssemblySequence(List<Task> tasks) {
        this.tasks = tasks;
    }
    public List<Task> getTasks() {
        return tasks;
    }
}