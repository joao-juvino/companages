package com.companages.repository;
import com.companages.entity.Position; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface PositionRepository extends JpaRepository<Position,Long>{ List<Position> findAllByOrganizationIdOrderByName(Long organizationId); Optional<Position> findByIdAndOrganizationId(Long id,Long organizationId); long countByOrganizationOwnerId(Long ownerId); }
