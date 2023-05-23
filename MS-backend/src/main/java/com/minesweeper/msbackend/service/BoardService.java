package com.minesweeper.msbackend.service;

import com.minesweeper.msbackend.model.Board;
import com.minesweeper.msbackend.model.Cell;

import java.util.Optional;
import java.util.Random;

public class BoardService {

    Board board;

    public Board getBoard() {
        return board;
    }

    /**
     * Create a new board
     * @param width size of the board
     * @param numMines number of mines on the board
     * @return new board
     */
    public Board createBoard(int width, int numMines) {

        Cell[][] cells = new Cell[width][width];
        board = new Board(cells, width, numMines);

        for (int row = 0; row < width; row++) {
            for (int col = 0; col < width; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }

        // Generate unique mine positions for the board
        int mineCounter = 0;
        Random random = new Random();

        while (mineCounter < numMines) {
            Optional<Cell> cell = board.getCell(random.nextInt(width), random.nextInt(width));
            if (cell.isPresent() && !cell.get().isMine()) {
                cell.get().setMine();
                mineCounter++;
            }
        }

        // Calculate number of adjacent mines
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < width; col++) {
                if (cells[row][col].isMine()) {
                    for (Cell cell : board.getNeighborCells(row, col)) {
                        cell.incrementAdjacentMines();
                    }
                }
            }
        }

        return board;
    }

    /**
     * Create a new board
     * @param difficulty easy, medium or hard difficulty
     * @return new board
     */
    public Board createBoard(Difficulty difficulty) {
        return switch (difficulty) {
            case easy -> createBoard(10, 10);
            case medium -> createBoard(18, 40);
            case hard -> createBoard(24, 100);
        };
    }

    /**
     * Toggle a flag for a cell on the board
     * @param row int
     * @param col int
     * @return flag value
     */
    public boolean toggleFlag(int row, int col) {
        Cell target = board.cells()[row][col];
        if (target.isRevealed()) {
            return target.setFlagged(false);
        }
        return target.setFlagged(!target.isFlagged());
    }

    /**
     * Reveal a cell on the board
     * @param row int
     * @param col int
     * @return true if mine is revealed, false otherwise
     */
    public boolean reveal(int row, int col) {
        Optional<Cell> optionalCell = board.getCell(row, col);
        if (optionalCell.isEmpty()) {
            return false;
        }

        Cell target = optionalCell.get();
        target.setFlagged(false);

        if (target.isMine()) {
            target.setRevealed();
            return true;
        } else if (!target.isRevealed() && target.getAdjacentMines() == 0) {
            target.setRevealed();
            // Reveal all surrounding cells
            for (Cell cell : board.getNeighborCells(row, col)) {
                reveal(cell.getCoordinate().x(), cell.getCoordinate().y());
            }
        }

        return false;
    }

    public boolean checkWin() {
        int numRevealed = 0;
        for (int row = 0; row < board.width(); row++) {
            for (int col = 0; col < board.width(); col++) {
                Optional<Cell> optionalCell = board.getCell(row, col);
                if (optionalCell.isEmpty()) {
                    return false;
                }
                if (optionalCell.get().isRevealed()) {
                    numRevealed++;
                }
            }
        }
        return numRevealed == board.width() * board.width() - board.numMines();
    }

    public enum Difficulty {
        easy,
        medium,
        hard
    }

}
