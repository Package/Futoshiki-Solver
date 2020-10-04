package com.github.pkg.solver;

import com.github.pkg.puzzle.Puzzle;

public class Solver {

    private Puzzle puzzle;

    /**
     * Kicks off the solving process and reports back to the console whether the
     * Puzzle was successfully able to be solved.
     *
     * @return boolean - whether the Puzzle was successfully solved.
     */
    public boolean solve(Puzzle puzzle) {
        this.puzzle = puzzle;

        System.out.println("Solver starting - initial Puzzle: ");
        System.out.println(puzzle);

        performSolve(0, 0);

        System.out.println("Solver finished - final Puzzle: ");
        System.out.println(puzzle);

        return puzzle.isSolved();
    }

    /**
     * Recursive call to solve the next position on the board.
     *
     * @param x - current X location
     * @param y - current Y location
     * @return boolean - the result of solving the next position on the board.
     */
    private boolean solveNext(int x, int y) {
        if (y >= (Puzzle.PUZZLE_SIZE - 1)) {
            return performSolve(x + 1, 0);
        } else {
            return performSolve(x, y + 1);
        }
    }

    /**
     * Determines whether the provided value is valid to be played in the current (X, Y) location on the grid.
     *
     * @param x     - the X location
     * @param y     - the Y location
     * @param value - the value to consider
     * @return boolean - whether this move is valid or not.
     */
    private boolean canPlayValue(int x, int y, int value) {
        var grid = puzzle.getGrid();

        // Same value cannot appear multiple times on any row/column
        for (var index = 0; index < Puzzle.PUZZLE_SIZE; index++) {
            if (grid[x][index].getValue() == value || grid[index][y].getValue() == value) {
                return false;
            }
        }

        // Check validation rules, certain cells need to be greater than/less than other cells.
        return grid[x][y].lessThanIsValid(value) && grid[x][y].greaterThanIsValid(value);
    }

    /**
     * Recursive backtracking algorithm to solve the Puzzle. As the input size is only a 5x5 board this does not
     * cause any performance issues. Similar techniques are applied to solving Sudoku which is on a 9x9 grid.
     *
     * @param x - starting X location
     * @param y - starting Y location
     * @return boolean - whether the Puzzle has been solved
     */
    private boolean performSolve(int x, int y) {
        // Edge case to stop the recursion, when the Puzzle is solved we can return.
        if (puzzle.isSolved()) {
            return true;
        }

        var grid = puzzle.getGrid();

        // Position has already been solved, no need to attempt this location again.
        if (grid[x][y].hasValue()) {
            return solveNext(x, y);
        }

        // Brute force all potential values into a cell, including backtracking if we
        // get into an invalid board position.
        for (var value = 1; value <= Puzzle.PUZZLE_SIZE; value++) {
            if (canPlayValue(x, y, value)) {
                grid[x][y].setValue(value);

                // Recursively try to brute force the remaining solution
                if (solveNext(x, y)) {
                    return true;
                }

                // Backtrack if we get to a state where the Puzzle is not valid
                grid[x][y].setValue(0);
            }
        }

        return false;
    }
}
