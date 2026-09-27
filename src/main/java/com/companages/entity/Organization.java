package com.companages.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name="organizations")
public class Organization {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,length=120) private String name;
    @Column(length=500) private String description;
    @Column(length=500) private String logoUrl; private String website; @Column(length=40) private String phone; private String email; @Column(length=500) private String address;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private RecordStatus status=RecordStatus.ACTIVE;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="owner_id",nullable=false) private User owner;
    @Column(nullable=false,updatable=false) private Instant createdAt;
    @Column(nullable=false) private Instant updatedAt;
    @PrePersist void create(){createdAt=updatedAt=Instant.now();} @PreUpdate void update(){updatedAt=Instant.now();}
    public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;} public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public String getLogoUrl(){return logoUrl;} public void setLogoUrl(String v){logoUrl=v;} public String getWebsite(){return website;} public void setWebsite(String v){website=v;} public String getPhone(){return phone;} public void setPhone(String v){phone=v;} public String getEmail(){return email;} public void setEmail(String v){email=v;} public String getAddress(){return address;} public void setAddress(String v){address=v;} public RecordStatus getStatus(){return status;} public void setStatus(RecordStatus v){status=v;}
    public User getOwner(){return owner;} public void setOwner(User v){owner=v;} public Instant getCreatedAt(){return createdAt;} public Instant getUpdatedAt(){return updatedAt;}
}
