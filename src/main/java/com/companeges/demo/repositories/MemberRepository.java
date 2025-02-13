package com.companeges.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.companeges.demo.models.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    
}
