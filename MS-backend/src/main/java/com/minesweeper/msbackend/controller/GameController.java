package com.minesweeper.msbackend.controller;

import com.minesweeper.msbackend.model.Coordinate;
import com.minesweeper.msbackend.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    BoardService boardService = new BoardService();

    @PostMapping("/difficulty/{difficulty}")
    public ResponseEntity<?> createGame(@PathVariable BoardService.Difficulty difficulty) {
        return new ResponseEntity<>(boardService.createBoard(difficulty), HttpStatus.OK);
    }

    @PostMapping("/size/{size}/mines/{numMines}")
    public ResponseEntity<?> createGame(@PathVariable int size, @PathVariable int numMines) {
        if (size * size > numMines) {
            return new ResponseEntity<>("Too many mines", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(boardService.createBoard(size, numMines), HttpStatus.OK);
    }

    @GetMapping("/board")
    public ResponseEntity<?> getBoard() {
        if (boardService.getBoard() == null) {
            return new ResponseEntity<>("Please create a board", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(boardService.getBoard(), HttpStatus.OK);
    }

    @PostMapping("/flag")
    public ResponseEntity<?> toggleFlag(@RequestBody Coordinate coordinate) {
        return new ResponseEntity<>(boardService.toggleFlag(coordinate), HttpStatus.OK);
    }

    @PostMapping("/reveal")
    public ResponseEntity<?> reveal(@RequestBody Coordinate coordinate) {
        return new ResponseEntity<>(boardService.reveal(coordinate), HttpStatus.OK);
    }

}
