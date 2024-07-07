package org.raxmistry.gameoflife;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameBoardTest {

    @Test
    void initialGameBoardTest() {
        GameBoard board = new GameBoard(100, 100);

        for (int r = 0; r < 100; r++) {
            for (int c = 0; c < 100; c++) {
                assertThat(board.isAlive(r, c)).isFalse();
            }
        }
    }

    @Test
    void setSomeCellsAlive() {
        GameBoard board = new GameBoard(100, 100);
        board.live(1, 1);
        board.live(50, 50);
        board.live(99, 99);

        for (int r = 0; r < 100; r++) {
            for (int c = 0; c < 100; c++) {
                if ((r == 1 && c == 1) || (r == 50 && c == 50) || (r == 99 && c == 99)) {
                    assertThat(board.isAlive(r, c)).isTrue();
                } else {
                    assertThat(board.isAlive(r, c)).isFalse();
                }
            }
        }
    }

    @Test
    void givenInitialState_singleCell_withNoNeighbours_shouldDie() {
        int rows = 3;
        int cols = 3;
        GameBoard board = new GameBoard(rows, cols);
        board.live(1, 1);

        board.generations(1);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                assertThat(board.isAlive(r, c))
                        .as("row %s col %s should not be alive", r, c)
                        .isFalse();
            }
        }
    }

    @Test
    void givenInitialState_singleCell_withOneLiveNeighbour_shouldDie() {
        int rows = 3;
        int cols = 3;
        GameBoard board = new GameBoard(rows, cols);
        board.live(1, 1);
        board.live(0, 0);

        board.generations(1);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                assertThat(board.isAlive(r, c))
                        .as("row %s col %s should not be alive", r, c)
                        .isFalse();
            }
        }
    }

    @Test
    void givenInitialState_singleCell_withTwoLiveNeighbour_shouldLive() {
        int rows = 3;
        int cols = 3;
        GameBoard board = new GameBoard(rows, cols);
        board.live(1, 1);
        board.live(0, 0);
        board.live(0, 1);

        board.generations(1);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if ((r == 1 && c == 1) || (r == 0 && c == 0) || (r == 0 && c == 1)) {
                    assertThat(board.isAlive(r, c))
                            .as("row %s col %s should be alive", r, c)
                            .isTrue();
                } else {
                    assertThat(board.isAlive(r, c))
                            .as("row %s col %s should not be alive", r, c)
                            .isFalse();
                }
            }
        }
    }

}
