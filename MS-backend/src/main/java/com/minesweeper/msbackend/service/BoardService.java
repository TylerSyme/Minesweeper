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

        return this.board = new Board(cells);
    }

    public Board createBoard(Difficulty difficulty) {
        return switch (difficulty) {
            case easy -> createBoard(10, 10);
            case medium -> createBoard(18, 40);
            case hard -> createBoard(24, 100);
        };
    }

    public boolean toggleFlag(Coordinate coordinate) {
        Cell target = this.board.getCells()[coordinate.x()][coordinate.y()];
        if (target.isMine() || target.isRevealed()) {
            return target.setFlagged(false);
        }
        return target.setFlagged(!target.isFlagged());
    }

    public enum Difficulty {
        easy,
        medium,
        hard
    }

}
