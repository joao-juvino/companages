package com.companeges.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companeges.demo.dto.UserPostPutRequestDTO;

@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserPostPutRequestDTO userPostPutRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(userPostPutRequestDTO));
    }
}
