package com.companeges.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.companeges.demo.dto.MemberPostPutDTO;
import com.companeges.demo.services.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {
    
    @Autowired
    MemberService memberService;

    @PostMapping
    public ResponseEntity<?> createMember(MemberPostPutDTO memberPostPutDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.createMember(memberPostPutDTO));
    }

}
