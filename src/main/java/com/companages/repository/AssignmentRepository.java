package com.companages.repository;
import com.companages.entity.Assignment; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface AssignmentRepository extends JpaRepository<Assignment,Long>{ List<Assignment> findAllByOrganizationIdOrderByCreatedAtDesc(Long organizationId); Optional<Assignment> findByIdAndOrganizationId(Long id,Long organizationId); boolean existsByMemberIdAndPositionId(Long memberId,Long positionId); long countByOrganizationOwnerId(Long ownerId); }
