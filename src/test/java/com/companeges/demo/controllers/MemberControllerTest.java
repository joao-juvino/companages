package com.companeges.demo.controllers;

import com.companeges.demo.dto.MemberPostPutDTO;
import com.companeges.demo.services.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    void testCreateMember() throws Exception {
        MemberPostPutDTO memberDTO = new MemberPostPutDTO(1L, "teste", 1L);
        Mockito.when(memberService.createMember(Mockito.any())).thenReturn(memberDTO);

        String memberJson = """
        {
            "name": "teste",
            "organizationId": 1
        }
        """;

        mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(memberJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("teste"))
                .andExpect(jsonPath("$.organizationId").value(1));

        Mockito.verify(memberService).createMember(Mockito.any());
    }
}
