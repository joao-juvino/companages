package com.companeges.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.companeges.demo.models.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
    
}
