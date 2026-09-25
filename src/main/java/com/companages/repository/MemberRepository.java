package com.companages.repository;
import com.companages.entity.Member; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface MemberRepository extends JpaRepository<Member,Long>{ List<Member> findAllByOrganizationIdOrderByName(Long organizationId); Optional<Member> findByIdAndOrganizationId(Long id,Long organizationId); long countByOrganizationOwnerId(Long ownerId); List<Member> findTop5ByOrganizationOwnerIdOrderByCreatedAtDesc(Long ownerId); }
