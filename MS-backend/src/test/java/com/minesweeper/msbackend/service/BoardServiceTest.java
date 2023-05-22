package com.minesweeper.msbackend.service;

import com.minesweeper.msbackend.model.Board;
import com.minesweeper.msbackend.model.Cell;
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
        for (Cell[] cellRow : board.cells()) {
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
        for (Cell[] cellRow : board.cells()) {
            for (Cell cell : cellRow) {
                if (cell.isMine()) {
                    mineCount++;
                }
            }
        }

        assertEquals(width, board.cells().length);
        assertEquals(numMines, mineCount);
    }

    @Test
    void testCreateBoardAdjacentMines() {

        Board board = boardService.createBoard(3, 9);

        assertEquals(3, board.cells()[0][0].getAdjacentMines());
        assertEquals(5, board.cells()[0][1].getAdjacentMines());
        assertEquals(8, board.cells()[1][1].getAdjacentMines());
    }

    @Test
    void testToggleFlag() {

        Board board = boardService.createBoard(1, 0);

        assertFalse(board.cells()[0][0].isFlagged());
        boardService.toggleFlag(0, 0);
        assertTrue(board.cells()[0][0].isFlagged());
    }

    @Test
    void testReveal() {

        Board board = boardService.createBoard(1, 0);

        assertFalse(board.cells()[0][0].isRevealed());
        assertFalse(boardService.reveal(0, 0));
        assertTrue(board.cells()[0][0].isRevealed());
    }

    @Test
    void testRevealMine() {

        Board board = boardService.createBoard(1, 1);

        assertFalse(board.cells()[0][0].isRevealed());
        assertTrue(boardService.reveal(0, 0));
        assertTrue(board.cells()[0][0].isRevealed());
    }

    @Test
    void testMultiReveal() {

        Board board = boardService.createBoard(2, 0);

        assertFalse(board.cells()[0][0].isRevealed());
        assertFalse(boardService.reveal(0, 0));
        assertTrue(board.cells()[1][1].isRevealed());
    }

    @Test
    void testToggleFlagRevealed() {

        Board board = boardService.createBoard(1, 0);

        assertFalse(board.cells()[0][0].isFlagged());
        boardService.reveal(0, 0);
        boardService.toggleFlag(0, 0);
        assertFalse(board.cells()[0][0].isFlagged());
    }

    @Test
    void testCheckWin() {

        boardService.createBoard(1, 0);

        boardService.reveal(0, 0);
        assertTrue(boardService.checkWin());
    }

    @Test
    void testCheckWin2() {

        boardService.createBoard(1, 1);

        assertTrue(boardService.checkWin());
    }

}
