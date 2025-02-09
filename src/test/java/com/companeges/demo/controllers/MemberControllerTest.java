package com.companeges.demo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.companeges.demo.dto.MemberPostPutDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MemberControllerTest.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateMember() throws Exception {
        // Arrange 
        MemberPostPutDTO memberDTO = new MemberPostPutDTO(new Long(1), "Juvino");

        String requestJson = objectMapper.writeValueAsString(memberDTO);

        mockMvc.perform(post("/members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").isNumber())
            .andExpect(jsonPath("$.name").value("juvino"));
    }

}
