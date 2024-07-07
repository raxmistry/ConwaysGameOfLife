package org.raxmistry.gameoflife;

public class GameBoard {

    Cell[][] grid;
    int rowSize;
    int colSize;

    public GameBoard(int rows, int cols) {
        grid = new Cell[rows][cols];
        rowSize = rows;
        colSize = cols;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = new Cell();
            }
        }
    }

    public boolean isAlive(int rowIndex, int colIndex) {
        return grid[rowIndex][colIndex].isAlive();
    }

    public void live(int rowIndex, int colIndex) {
        grid[rowIndex][colIndex].live();
    }

    public void generations(int gens) {
        Cell[][] copy = grid.clone();
        for (int g = 0; g < gens; g++) {
            for (int r = 0; r < rowSize; r++) {
                for (int c = 0; c < colSize; c++) {
                    //TODO: Rules here
                    int livingNeighbours = numberOfLiveNeighbours(r, c);
                    if (livingNeighbours < 2) copy[r][c].kill();
                    if (livingNeighbours == 2 || livingNeighbours == 3) copy[r][c].live();
                }
            }
        }
        grid = copy.clone();
    }

    private int numberOfLiveNeighbours(int r, int c) {
        int startingRow = 0;
        int startingCol = 0;
        int endingRow = 0;
        int endingCol = 0;
        int livingCount = 0;

        if (r > 0) startingRow = r - 1;
        if (c > 0) startingCol = c - 1;
        if (r < rowSize) endingRow = r + 1;
        if (c < colSize) endingCol = c + 1;

        System.out.printf("Grid[%s][%s] is alive? %s%n", r, c, grid[r][c].isAlive());
        System.out.printf("startingRow: %s startingCol: %s endingRow: %s endingCol: %s %n", startingRow, startingCol, endingRow, endingCol);

        for (int row = startingRow; row < endingRow; row++) {
            for (int col = startingCol; col < endingCol; col++) {
                if (grid[row][col].isAlive()) {
                    livingCount++;
                }
            }
        }
        System.out.printf("Has %s living neighbours", livingCount);
        return livingCount;
    }

    class Cell {
        boolean alive = false;

        void live() {
            alive = true;
        }

        public boolean isAlive() {
            return alive;
        }

        public void kill() {
            alive = false;
        }
    }
}
