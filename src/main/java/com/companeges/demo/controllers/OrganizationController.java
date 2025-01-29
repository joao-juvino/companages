package com.companeges.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.companeges.demo.dto.OrganizationPostPutRequestDTO;
import com.companeges.demo.services.OrganizationService;

public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<?> createOrganization(@RequestBody OrganizationPostPutRequestDTO organizationPostPutRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(organizationService.createOrganization(organizationPostPutRequestDTO));
    }  
}
