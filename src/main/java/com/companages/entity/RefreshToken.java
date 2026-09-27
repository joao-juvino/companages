package com.companages.entity;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="refresh_tokens") public class RefreshToken {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @ManyToOne(fetch=FetchType.LAZY,optional=false) private User user; @Column(nullable=false,unique=true,length=64) private String tokenHash; @Column(nullable=false) private Instant expiresAt; private Instant revokedAt; @Column(nullable=false,updatable=false) private Instant createdAt; @PrePersist void create(){createdAt=Instant.now();}
 public Long getId(){return id;} public User getUser(){return user;} public void setUser(User v){user=v;} public String getTokenHash(){return tokenHash;} public void setTokenHash(String v){tokenHash=v;} public Instant getExpiresAt(){return expiresAt;} public void setExpiresAt(Instant v){expiresAt=v;} public Instant getRevokedAt(){return revokedAt;} public void setRevokedAt(Instant v){revokedAt=v;} public boolean active(){return revokedAt==null&&expiresAt.isAfter(Instant.now());}
}
