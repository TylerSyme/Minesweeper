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

        final int width = 3;
        final int numMines = 9;
        Board board = boardService.createBoard(width, numMines);

        assertEquals(3, board.cells()[0][0].getAdjacentMines());
        assertEquals(5, board.cells()[0][1].getAdjacentMines());
        assertEquals(8, board.cells()[1][1].getAdjacentMines());
    }

    @Test
    void testToggleFlag() {

        final int width = 1;
        final int numMines = 0;
        Board board = boardService.createBoard(width, numMines);

        assertFalse(board.cells()[0][0].isFlagged());
        boardService.toggleFlag(new Coordinate(0, 0));
        assertTrue(board.cells()[0][0].isFlagged());
    }

    @Test
    void testReveal() {

        final int width = 1;
        final int numMines = 0;
        Board board = boardService.createBoard(width, numMines);

        assertFalse(board.cells()[0][0].isRevealed());
        assertFalse(boardService.reveal(0, 0));
        assertTrue(board.cells()[0][0].isRevealed());
    }

    @Test
    void testRevealMine() {

        final int width = 1;
        final int numMines = 1;
        Board board = boardService.createBoard(width, numMines);

        assertFalse(board.cells()[0][0].isRevealed());
        assertTrue(boardService.reveal(0, 0));
        assertTrue(board.cells()[0][0].isRevealed());
    }

}
