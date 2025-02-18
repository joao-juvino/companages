package com.companeges.demo.dto;

import com.companeges.demo.models.Member;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberPostPutDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("organizationId")
    private Long organizationId;

    public MemberPostPutDTO (Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.organizationId = member.getOrganization().getId();
    }

    public MemberPostPutDTO (Long id, String name, Long organizationId) {
        this.id = id;
        this.name = name;
        this.organizationId = organizationId;
    }

}
