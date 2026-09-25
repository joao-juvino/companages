package com.companages.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name="assignments", uniqueConstraints=@UniqueConstraint(name="uk_assignment_member_position",columnNames={"member_id","position_id"}))
public class Assignment {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="member_id",nullable=false) private Member member;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="position_id",nullable=false) private Position position;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="organization_id",nullable=false) private Organization organization;
    @Column(nullable=false,updatable=false) private Instant createdAt; @PrePersist void create(){createdAt=Instant.now();}
    public Long getId(){return id;} public Member getMember(){return member;} public void setMember(Member v){member=v;} public Position getPosition(){return position;} public void setPosition(Position v){position=v;}
    public Organization getOrganization(){return organization;} public void setOrganization(Organization v){organization=v;} public Instant getCreatedAt(){return createdAt;}
}
