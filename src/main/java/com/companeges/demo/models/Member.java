package com.companeges.demo.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Position> positions;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private List<Member> members;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Position> getPositions() {
        return this.positions;
    }

    public List<Member> getMembers() {
        return this.members;
    }

}
