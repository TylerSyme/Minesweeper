package com.minesweeper.msbackend.service;

import com.minesweeper.msbackend.model.Board;
import com.minesweeper.msbackend.model.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardServiceTest {

    BoardService boardService = new BoardService();

    @Test
    void createBoard() {

        final int width = 10;
        final int numMines = 10;
        Board board = boardService.createBoard(width, numMines);

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

    @Test
    void createMediumBoard() {

        final int width = 18;
        final int numMines = 40;
        Board board = boardService.createBoard(BoardService.Difficulty.medium);

        int mineCount = 0;
        for (Cell[] cellRow : board.getCells()) {
            for (Cell cell : cellRow) {
                if (cell.isMine()) {
                    mineCount++;
                }
            }
        }

        assertEquals(width, board.getCells().length);
        assertEquals(numMines, mineCount);
    }
}
