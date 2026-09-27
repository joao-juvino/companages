package com.companages.entity;

import jakarta.persistence.*;
import java.time.Instant; import java.time.LocalDate;

@Entity @Table(name="members")
public class Member {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,length=120) private String name;
    private String email;
    @ManyToOne(fetch=FetchType.LAZY) private User user;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private AccessRole accessRole=AccessRole.MEMBER;
    @ManyToOne(fetch=FetchType.LAZY) private Position position;
    @ManyToOne(fetch=FetchType.LAZY) private Team team;
    @ManyToOne(fetch=FetchType.LAZY) private Member manager;
    private LocalDate joinedAt;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private RecordStatus status=RecordStatus.ACTIVE;
    @Column(length=120) private String location; @Column(length=40) private String phone; @Column(length=1000) private String bio; @Column(length=500) private String avatarUrl;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="organization_id",nullable=false) private Organization organization;
    @Column(nullable=false,updatable=false) private Instant createdAt; @Column(nullable=false) private Instant updatedAt;
    @PrePersist void create(){createdAt=updatedAt=Instant.now();} @PreUpdate void update(){updatedAt=Instant.now();}
    public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;} public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public User getUser(){return user;} public void setUser(User v){user=v;} public AccessRole getAccessRole(){return accessRole;} public void setAccessRole(AccessRole v){accessRole=v;} public Position getPosition(){return position;} public void setPosition(Position v){position=v;} public Team getTeam(){return team;} public void setTeam(Team v){team=v;} public Member getManager(){return manager;} public void setManager(Member v){manager=v;} public LocalDate getJoinedAt(){return joinedAt;} public void setJoinedAt(LocalDate v){joinedAt=v;} public RecordStatus getStatus(){return status;} public void setStatus(RecordStatus v){status=v;} public String getLocation(){return location;} public void setLocation(String v){location=v;} public String getPhone(){return phone;} public void setPhone(String v){phone=v;} public String getBio(){return bio;} public void setBio(String v){bio=v;} public String getAvatarUrl(){return avatarUrl;} public void setAvatarUrl(String v){avatarUrl=v;}
    public Organization getOrganization(){return organization;} public void setOrganization(Organization v){organization=v;} public Instant getCreatedAt(){return createdAt;} public Instant getUpdatedAt(){return updatedAt;}
}
