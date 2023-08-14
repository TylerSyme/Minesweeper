package com.minesweeper.msbackend.controller;

import com.minesweeper.msbackend.model.Coordinate;
import com.minesweeper.msbackend.model.RevealResponse;
import com.minesweeper.msbackend.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class GameController {

    BoardService boardService = new BoardService();

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<?> createGame(@PathVariable BoardService.Difficulty difficulty) {
        return new ResponseEntity<>(boardService.createBoard(difficulty), HttpStatus.OK);
    }

    @GetMapping("/custom")
    public ResponseEntity<?> createGame(@RequestParam int size, @RequestParam int numMines) {
        if (size * size < numMines) {
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
        return new ResponseEntity<>(boardService.toggleFlag(coordinate.x(), coordinate.y()), HttpStatus.OK);
    }

    @PostMapping("/reveal")
    public ResponseEntity<?> reveal(@RequestBody Coordinate coordinate) {
        if (boardService.reveal(coordinate.x(), coordinate.y())) {
            return new ResponseEntity<>(new RevealResponse(true, false, boardService.getBoard()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new RevealResponse(false, boardService.checkWin(), boardService.getBoard()), HttpStatus.OK);
    }

}
