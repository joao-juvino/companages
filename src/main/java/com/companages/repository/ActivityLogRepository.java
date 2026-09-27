package com.companages.repository;
import com.companages.entity.ActivityLog; import org.springframework.data.jpa.repository.*;
public interface ActivityLogRepository extends JpaRepository<ActivityLog,Long>,JpaSpecificationExecutor<ActivityLog>{}
