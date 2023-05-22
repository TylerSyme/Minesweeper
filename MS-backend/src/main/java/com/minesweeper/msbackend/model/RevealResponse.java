package com.minesweeper.msbackend.model;

public record RevealResponse(boolean isMine, boolean gameWon) {
}
