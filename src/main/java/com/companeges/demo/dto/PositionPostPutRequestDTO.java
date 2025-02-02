package com.companeges.demo.dto;

import com.companeges.demo.models.Organization;
import com.companeges.demo.models.Position;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PositionPostPutRequestDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("weight")
    private Long weight;

    public PositionPostPutRequestDTO(Position position) {
        this.id = position.getId();
        this.name = position.getName();
        this.weight = position.getWeight();
    }
}
