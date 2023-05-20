package com.minesweeper.msbackend.service;

import com.minesweeper.msbackend.model.Board;
import com.minesweeper.msbackend.model.Cell;
import com.minesweeper.msbackend.model.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void toggleFlag() {

        final int width = 1;
        final int numMines = 0;
        Board board = boardService.createBoard(width, numMines);

        assertFalse(board.getCells()[0][0].isFlagged());
        boardService.toggleFlag(new Coordinate(0, 0));
        assertTrue(board.getCells()[0][0].isFlagged());
    }

    @Test
    void toggleFlagMine() {

        final int width = 1;
        final int numMines = 1;
        Board board = boardService.createBoard(width, numMines);

        assertFalse(board.getCells()[0][0].isFlagged());
        boardService.toggleFlag(new Coordinate(0, 0));
        assertFalse(board.getCells()[0][0].isFlagged());
    }
}
