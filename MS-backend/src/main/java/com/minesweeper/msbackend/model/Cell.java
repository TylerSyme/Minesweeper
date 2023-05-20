package com.minesweeper.msbackend.model;

public class Cell {

    private boolean mine;
    private boolean revealed = false;
    private boolean flagged = false;

    private int adjacentMines = 0;

    public Cell(boolean mine) {
        this.mine = mine;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public boolean setFlagged(boolean flagged) {
        this.flagged = flagged;
        return flagged;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

}
