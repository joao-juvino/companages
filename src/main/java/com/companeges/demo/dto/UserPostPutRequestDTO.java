package com.companeges.demo.dto;

import java.util.List;

import com.companeges.demo.models.Organization;
import com.companeges.demo.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPostPutRequestDTO {
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("organizations")
    private List<Organization> organizations;

    public UserPostPutRequestDTO (User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.organizations = user.getOrganizations();
    }
}
