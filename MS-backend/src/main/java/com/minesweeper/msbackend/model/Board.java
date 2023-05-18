package com.minesweeper.msbackend.model;

public class Board {

    private Cell[][] cells;

    public Board(int width, int height, int numMines) {
        // Todo: generate board
        this.cells = new Cell[width][height];
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

}
