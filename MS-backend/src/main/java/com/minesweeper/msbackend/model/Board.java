package com.minesweeper.msbackend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    private Cell[][] cells;

    public Board(Cell[][] cells) {
        this.cells = cells;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

}
