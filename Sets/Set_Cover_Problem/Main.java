package Set_Cover_Problem;
// Created: September 2022
import java.util.*;
public class Main {
    public static void main(String[] args) {
        SetCoverProblem problem = createSetCoverProblem();
        OptimizerClass aco = new OptimizerClass(problem, 10, 0.1, 1.0, 2.0, 0.1);
        Solution solution = aco.solve(100);
        System.out.println("Best Solution: " + solution.getSelectedSets());
    }
    private static SetCoverProblem createSetCoverProblem() {
        int universeSize = 5;
        List<Set<Integer>> sets = new ArrayList<>();
        sets.add(new HashSet<>(Arrays.asList(1, 2, 3)));
        sets.add(new HashSet<>(Arrays.asList(2, 4)));
        sets.add(new HashSet<>(Arrays.asList(1, 3, 5)));
        return new SetCoverProblem(universeSize, sets);
    }
}