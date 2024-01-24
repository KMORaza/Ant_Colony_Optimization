package Set_Cover_Problem;
// Created: September 2022
import java.util.HashSet;
import java.util.Set;
public class Ant {
    private Set<Integer> visitedElements;
    public Ant() {
        this.visitedElements = new HashSet<>();
    }
    public Set<Integer> getVisitedElements() {
        return visitedElements;
    }
    public void visitElement(int element) {
        visitedElements.add(element);
    }
    public void clearVisitedElements() {
        visitedElements.clear();
    }
}