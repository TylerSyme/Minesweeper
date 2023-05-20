package com.minesweeper.msbackend.service;

import com.minesweeper.msbackend.model.Board;
import com.minesweeper.msbackend.model.Cell;
import com.minesweeper.msbackend.model.Coordinate;

import java.util.ArrayList;
import java.util.List;
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

        for (int row = 0; row < width; row++) {
            for (int col = 0; col < width; col++) {
                cells[row][col] = new Cell(false);
            }
        }

        // Generate unique mine positions for the board
        List<Coordinate> mineLocations = new ArrayList<>();
        Random random = new Random();

        while (mineLocations.size() < numMines) {
            Coordinate coordinate = new Coordinate(random.nextInt(width), random.nextInt(width));

            if (!mineLocations.contains(coordinate)) {
                mineLocations.add(coordinate);
                cells[coordinate.x()][coordinate.y()].setMine(true);
            }
        }

        // Calculate number of adjacent mines
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < width; col++) {
                int adjacentMines = 0;
                if (row > 0 && col > 0 && cells[row - 1][col - 1].isMine()) {
                    adjacentMines++;
                }
                if (row > 0 && cells[row - 1][col].isMine()) {
                    adjacentMines++;
                }
                if (row > 0 && col < width - 1 && cells[row - 1][col + 1].isMine()) {
                    adjacentMines++;
                }
                if (col > 0 && cells[row][col - 1].isMine()) {
                    adjacentMines++;
                }
                if (col < width - 1 && cells[row][col + 1].isMine()) {
                    adjacentMines++;
                }
                if (row < width - 1 && col > 0 && cells[row + 1][col - 1].isMine()) {
                    adjacentMines++;
                }
                if (row < width - 1 && cells[row + 1][col].isMine()) {
                    adjacentMines++;
                }
                if (row < width - 1 && col < width - 1 && cells[row + 1][col + 1].isMine()) {
                    adjacentMines++;
                }
                cells[row][col].setAdjacentMines(adjacentMines);
            }
        }

        return this.board = new Board(cells);
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
     * Toggle a flag
     * @param coordinate coordinate to flag
     * @return flag value
     */
    public boolean toggleFlag(Coordinate coordinate) {
        Cell target = this.board.getCells()[coordinate.x()][coordinate.y()];
        if (target.isMine() || target.isRevealed()) {
            return target.setFlagged(false);
        }
        return target.setFlagged(!target.isFlagged());
    }

    /**
     * Reveal a cell
     * @param coordinate coordinate to reveal
     * @return true if revealed mine, false if safe
     */
    public boolean reveal(Coordinate coordinate) {
        // TODO: update full board instead --> reveal needs to expand
        Cell target = this.board.getCells()[coordinate.x()][coordinate.y()];
        target.setRevealed(true);
        return target.isMine();
    }

    public enum Difficulty {
        easy,
        medium,
        hard
    }

}
