package com.companages.entity;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="password_reset_tokens") public class PasswordResetToken {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @ManyToOne(fetch=FetchType.LAZY,optional=false) private User user; @Column(nullable=false,unique=true,length=64) private String tokenHash; @Column(nullable=false) private Instant expiresAt; private Instant usedAt; @Column(nullable=false,updatable=false) private Instant createdAt; @PrePersist void create(){createdAt=Instant.now();}
 public User getUser(){return user;} public void setUser(User v){user=v;} public String getTokenHash(){return tokenHash;} public void setTokenHash(String v){tokenHash=v;} public Instant getExpiresAt(){return expiresAt;} public void setExpiresAt(Instant v){expiresAt=v;} public Instant getUsedAt(){return usedAt;} public void setUsedAt(Instant v){usedAt=v;} public boolean active(){return usedAt==null&&expiresAt.isAfter(Instant.now());}
}
