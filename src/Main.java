import com.github.pkg.puzzle.Puzzle;
import com.github.pkg.solver.Solver;

public class Main {

    public static void main(String[] args) {
        var puzzle = new Puzzle();
        var solver = new Solver();

        solver.solve(puzzle);
    }
}
