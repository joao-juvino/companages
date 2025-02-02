package com.companeges.demo.dto;

import java.util.List;

import com.companeges.demo.models.Member;
import com.companeges.demo.models.Organization;
import com.companeges.demo.models.Position;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrganizationPostPutRequestDTO {
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("members")
    private List<Member> members;

    @JsonProperty("positions")
    private List<Position> positions;

    public OrganizationPostPutRequestDTO(Organization organization) {
        this.id = organization.getId();
        this.name = organization.getName();
        this.userId = organization.getUserId();
        this.members = organization.getMembers();
        this.positions = organization.getPositions();
    }
}
