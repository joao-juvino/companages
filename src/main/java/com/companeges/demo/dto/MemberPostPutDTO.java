package com.companeges.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberPostPutDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
}
