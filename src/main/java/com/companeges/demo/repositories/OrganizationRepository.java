package com.companeges.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.companeges.demo.models.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    
}
