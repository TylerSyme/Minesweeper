interface Cell {
    mine: boolean;
    revealed: boolean;
    flagged: boolean;
    adjacentMines: number;
    coordinate: Coordinate;
}