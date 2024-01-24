package Set_Cover_Problem;
// Created: September 2022
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class SetCoverProblem {
    private int universeSize;
    private List<Set<Integer>> sets;
    public SetCoverProblem(int universeSize, List<Set<Integer>> sets) {
        this.universeSize = universeSize;
        this.sets = sets;
    }
    public int getUniverseSize() {
        return universeSize;
    }
    public List<Set<Integer>> getSets() {
        return sets;
    }
}