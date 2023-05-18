package com.minesweeper.msbackend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void testBoardNumMines() {
        final int width = 10;
        final int numMines = 10;
        Board board = new Board(width, numMines);

        int mineCount = 0;
        for (Cell[] cellRow : board.getCells()) {
            for (Cell cell : cellRow) {
                if (cell.isMine()) {
                    mineCount++;
                }
            }
        }

        assertEquals(numMines, mineCount);
    }

}