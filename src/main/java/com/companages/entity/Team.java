package com.companages.entity;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="teams") public class Team {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="organization_id") private Organization organization;
 @Column(nullable=false,length=120) private String name; @Column(length=500) private String description;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="lead_member_id") private Member lead;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private RecordStatus status=RecordStatus.ACTIVE;
 @Column(nullable=false,updatable=false) private Instant createdAt; @Column(nullable=false) private Instant updatedAt;
 @PrePersist void create(){createdAt=updatedAt=Instant.now();} @PreUpdate void update(){updatedAt=Instant.now();}
 public Long getId(){return id;} public Organization getOrganization(){return organization;} public void setOrganization(Organization v){organization=v;} public String getName(){return name;} public void setName(String v){name=v;} public String getDescription(){return description;} public void setDescription(String v){description=v;} public Member getLead(){return lead;} public void setLead(Member v){lead=v;} public RecordStatus getStatus(){return status;} public void setStatus(RecordStatus v){status=v;} public Instant getCreatedAt(){return createdAt;} public Instant getUpdatedAt(){return updatedAt;}
}
