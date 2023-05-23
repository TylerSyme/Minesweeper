package com.minesweeper.msbackend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record Board(Cell[][] cells, int width, int numMines) {

    public Optional<Cell> getCell(int row, int col) {
        if (row >= 0 && row < cells.length && col >= 0 && col < cells.length) {
            return Optional.ofNullable(cells[row][col]);
        }
        return Optional.empty();
    }

    public List<Cell> getNeighborCells(int row, int col) {

        List<Cell> neighbors = new ArrayList<>();

        getCell(row - 1, col - 1).ifPresent(neighbors::add);
        getCell(row - 1, col).ifPresent(neighbors::add);
        getCell(row - 1, col + 1).ifPresent(neighbors::add);
        getCell(row, col - 1).ifPresent(neighbors::add);
        getCell(row, col + 1).ifPresent(neighbors::add);
        getCell(row + 1, col - 1).ifPresent(neighbors::add);
        getCell(row + 1, col).ifPresent(neighbors::add);
        getCell(row + 1, col + 1).ifPresent(neighbors::add);

        return neighbors;
    }

}
