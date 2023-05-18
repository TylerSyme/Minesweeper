package com.minesweeper.msbackend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    private Cell[][] cells;

    public Board(int width, int numMines) {

        this.cells = new Cell[width][width];
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < width; col++) {
                this.cells[row][col] = new Cell(false);
            }
        }

        // Generate unique mine positions for the board
        List<Coordinate> mineLocations = new ArrayList<>();
        Random random = new Random();

        while (mineLocations.size() < numMines) {
            Coordinate coordinate = new Coordinate(random.nextInt(width), random.nextInt(width));

            if (!mineLocations.contains(coordinate)) {
                mineLocations.add(coordinate);
                this.cells[coordinate.x][coordinate.y].setMine(true);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    private record Coordinate(int x, int y) {};

}
