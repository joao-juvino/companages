package com.companeges.demo.dto;

import com.companeges.demo.models.Organization;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrganizationPostPutRequestDTO {
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("userId")
    private Long userId;

    public OrganizationPostPutRequestDTO(Organization organization) {
        this.id = organization.getId();
        this.name = organization.getName();
        this.userId = organization.getUserId();
    }
}
