package com.minesweeper.msbackend.service;

import com.minesweeper.msbackend.model.Board;
import com.minesweeper.msbackend.model.Cell;
import com.minesweeper.msbackend.model.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardServiceTest {

    BoardService boardService = new BoardService();

    @Test
    void testCreateBoard() {

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
    void testCreateMediumBoard() {

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
    void testToggleFlag() {

        final int width = 1;
        final int numMines = 0;
        Board board = boardService.createBoard(width, numMines);

        assertFalse(board.getCells()[0][0].isFlagged());
        boardService.toggleFlag(new Coordinate(0, 0));
        assertTrue(board.getCells()[0][0].isFlagged());
    }

    @Test
    void testToggleFlagMine() {

        final int width = 1;
        final int numMines = 1;
        Board board = boardService.createBoard(width, numMines);

        assertFalse(board.getCells()[0][0].isFlagged());
        boardService.toggleFlag(new Coordinate(0, 0));
        assertFalse(board.getCells()[0][0].isFlagged());
    }

    @Test
    void testReveal() {

        final int width = 1;
        final int numMines = 0;
        Board board = boardService.createBoard(width, numMines);

        assertFalse(board.getCells()[0][0].isRevealed());
        assertFalse(boardService.reveal(new Coordinate(0, 0)));
        assertTrue(board.getCells()[0][0].isRevealed());
    }

    @Test
    void testRevealMine() {

        final int width = 1;
        final int numMines = 1;
        Board board = boardService.createBoard(width, numMines);

        assertFalse(board.getCells()[0][0].isRevealed());
        assertTrue(boardService.reveal(new Coordinate(0, 0)));
        assertTrue(board.getCells()[0][0].isRevealed());
    }

}
