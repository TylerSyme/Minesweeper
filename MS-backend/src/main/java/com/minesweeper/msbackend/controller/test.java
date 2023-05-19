package com.minesweeper.msbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @GetMapping("/")
    public ResponseEntity<?> getTest() {
        return new ResponseEntity<>("Test success", HttpStatus.OK);
    }

}
