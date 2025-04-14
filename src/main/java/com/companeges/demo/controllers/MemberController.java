package com.companeges.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companeges.demo.dto.MemberPostPutDTO;
import com.companeges.demo.services.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
    
    @Autowired
    MemberService memberService;

    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody MemberPostPutDTO memberPostPutDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.createMember(memberPostPutDTO));
    }

    @GetMapping
    public ResponseEntity<?> getMembers() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.getMembers());
    }

}
