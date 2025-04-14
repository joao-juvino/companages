package com.companeges.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.companeges.demo.dto.PositionPostPutRequestDTO;
import com.companeges.demo.services.PositionService;

@RestController("/positions")
public class PositionController {
    @Autowired
    PositionService positionService;

    @PostMapping
    public ResponseEntity<?> createPosition(@RequestBody PositionPostPutRequestDTO positionPostPutRequestDTO) {
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.positionService.createPosition(positionPostPutRequestDTO));
    }

    @GetMapping
    public ResponseEntity<?> getPositions() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.positionService.getPositions());
    }

}
