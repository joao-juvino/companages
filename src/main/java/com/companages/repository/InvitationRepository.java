package com.companages.repository;
import com.companages.entity.*; import org.springframework.data.jpa.repository.*; import java.util.Optional;
public interface InvitationRepository extends JpaRepository<Invitation,Long>,JpaSpecificationExecutor<Invitation>{Optional<Invitation> findByIdAndOrganizationId(Long id,Long organizationId);Optional<Invitation> findByTokenHash(String hash);boolean existsByOrganizationIdAndEmailIgnoreCaseAndStatus(Long org,String email,InvitationStatus status);long countByOrganizationIdAndStatus(Long organizationId,InvitationStatus status);}
