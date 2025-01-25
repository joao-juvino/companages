package com.companeges.demo.dto;

import java.util.List;

import com.companeges.demo.models.Organization;
import com.companeges.demo.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

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

    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Organization> getOrganizations() {
        return organizations;
    }
    
    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }
    
}
