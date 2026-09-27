package com.companages.repository;
import com.companages.entity.*; import org.springframework.data.jpa.repository.*; import java.util.*;
public interface TeamRepository extends JpaRepository<Team,Long>,JpaSpecificationExecutor<Team>{Optional<Team> findByIdAndOrganizationId(Long id,Long organizationId);long countByOrganizationIdAndStatus(Long organizationId,RecordStatus status);}
