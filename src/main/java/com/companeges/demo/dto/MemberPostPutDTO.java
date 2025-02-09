package com.companeges.demo.dto;

import java.lang.reflect.Member;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberPostPutDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    public MemberPostPutDTO (Member member) {
        this.id = member.getId();
        this.name = member.getName();
    }

    public MemberPostPutDTO (Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
