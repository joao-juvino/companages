package com.companages.repository;
import com.companages.entity.Organization; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface OrganizationRepository extends JpaRepository<Organization,Long>{ List<Organization> findAllByOwnerIdOrderByCreatedAtDesc(Long ownerId); Optional<Organization> findByIdAndOwnerId(Long id,Long ownerId); long countByOwnerId(Long ownerId); }
