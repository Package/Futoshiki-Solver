package com.github.pkg.puzzle;

import com.github.pkg.square.Square;

public class Puzzle {

    private Square[][] grid;
    public static final int PUZZLE_SIZE = 5;

    public Puzzle() {
        configure();
        addState();
    }

    /**
     * Configures the Puzzle grid.
     */
    private void configure() {
        this.grid = new Square[PUZZLE_SIZE][PUZZLE_SIZE];
        for (int x = 0; x < PUZZLE_SIZE; x++) {
            for (int y = 0; y < PUZZLE_SIZE; y++) {
                grid[x][y] = new Square(x, y);
            }
        }
    }

    /**
     * Adds initial Puzzle state.
     */
    private void addState() {
        // Initial values
        grid[1][0].setValue(4);
        grid[1][4].setValue(2);
        grid[2][2].setValue(4);
        grid[3][4].setValue(4);

        // Initial condition sets
        grid[0][0].greaterThan(grid[0][1]);
        grid[0][1].lessThan(grid[0][0]);

        grid[0][2].greaterThan(grid[0][3]);
        grid[0][3].lessThan(grid[0][2]);

        grid[0][3].greaterThan(grid[0][4]);
        grid[0][4].lessThan(grid[0][3]);

        grid[3][4].greaterThan(grid[3][3]);
        grid[3][3].lessThan(grid[3][4]);

        grid[4][1].greaterThan(grid[4][0]);
        grid[4][0].lessThan(grid[4][1]);

        grid[4][1].lessThan(grid[4][2]);
        grid[4][2].greaterThan(grid[4][1]);
    }

    /**
     * Determine whether the Puzzle is considered solved, that is each Square has been populated with a value.
     *
     * @return boolean - whether the Puzzle is solved or not.
     */
    public boolean isSolved() {
        for (int x = 0; x < PUZZLE_SIZE; x++) {
            for (int y = 0; y < PUZZLE_SIZE; y++) {
                if (!grid[x][y].hasValue()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns the Puzzle grid.
     * @return Square[][] - the Puzzle grid
     */
    public Square[][] getGrid() {
        return grid;
    }

    /**
     * Build a string representation of the Puzzle for display to system console.
     *
     * @return String - a string representation of the Puzzle.
     */
    @Override
    public String toString() {
        var builder = new StringBuilder();

        for (int x = 0; x < PUZZLE_SIZE; x++) {
            for (int y = 0; y < PUZZLE_SIZE; y++) {
                builder.append(grid[x][y].getValue());
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
