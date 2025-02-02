package com.companeges.demo.models;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column()
    private Long weight;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Long getWeight() {
        return this.weight;
    }

}
