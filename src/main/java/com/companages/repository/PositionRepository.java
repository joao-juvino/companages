package com.companages.repository;
import com.companages.entity.*; import org.springframework.data.jpa.repository.*; import java.util.*;
public interface PositionRepository extends JpaRepository<Position,Long>,JpaSpecificationExecutor<Position>{List<Position> findAllByOrganizationIdOrderByName(Long organizationId);Optional<Position> findByIdAndOrganizationId(Long id,Long organizationId);long countByOrganizationId(Long organizationId);long countByOrganizationOwnerId(Long ownerId);}
