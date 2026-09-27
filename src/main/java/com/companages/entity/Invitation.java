package com.companages.entity;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="invitations") public class Invitation {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) private Organization organization;
 @Column(nullable=false) private String email; @Enumerated(EnumType.STRING) @Column(name="access_role",nullable=false) private AccessRole role;
 @Column(nullable=false,unique=true,length=64) private String tokenHash; @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="invited_by_id") private User invitedBy;
 @Column(nullable=false) private Instant expiresAt; private Instant acceptedAt; @Enumerated(EnumType.STRING) @Column(nullable=false) private InvitationStatus status=InvitationStatus.PENDING;
 @Column(nullable=false,updatable=false) private Instant createdAt; @Column(nullable=false) private Instant updatedAt; @PrePersist void create(){createdAt=updatedAt=Instant.now();} @PreUpdate void update(){updatedAt=Instant.now();}
 public Long getId(){return id;} public Organization getOrganization(){return organization;} public void setOrganization(Organization v){organization=v;} public String getEmail(){return email;} public void setEmail(String v){email=v;} public AccessRole getRole(){return role;} public void setRole(AccessRole v){role=v;} public String getTokenHash(){return tokenHash;} public void setTokenHash(String v){tokenHash=v;} public User getInvitedBy(){return invitedBy;} public void setInvitedBy(User v){invitedBy=v;} public Instant getExpiresAt(){return expiresAt;} public void setExpiresAt(Instant v){expiresAt=v;} public Instant getAcceptedAt(){return acceptedAt;} public void setAcceptedAt(Instant v){acceptedAt=v;} public InvitationStatus getStatus(){return status;} public void setStatus(InvitationStatus v){status=v;} public Instant getCreatedAt(){return createdAt;}
}
