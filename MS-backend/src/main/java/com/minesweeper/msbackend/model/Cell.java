package com.minesweeper.msbackend.model;

public class Cell {

    private boolean mine = false;
    private boolean revealed = false;
    private boolean flagged = false;
    private int adjacentMines = 0;
    private final Coordinate coordinate;

    public Cell(int row, int col) {
        this.coordinate = new Coordinate(row, col);
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine() {
        this.mine = true;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed() {
        this.revealed = true;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public boolean setFlagged(boolean flagged) {
        return this.flagged = flagged;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    public void incrementAdjacentMines() {
        this.adjacentMines++;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
