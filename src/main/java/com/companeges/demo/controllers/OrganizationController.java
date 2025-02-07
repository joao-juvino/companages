package com.companeges.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.companeges.demo.dto.OrganizationPostPutRequestDTO;
import com.companeges.demo.services.OrganizationService;

@Controller
@RequestMapping("/organizations")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<?> createOrganization(@RequestBody OrganizationPostPutRequestDTO organizationPostPutRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(organizationService.createOrganization(organizationPostPutRequestDTO));
    } 
    
    @GetMapping
    public ResponseEntity<?> getOrganizations() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organizationService.getOrganization());
    }  
}
