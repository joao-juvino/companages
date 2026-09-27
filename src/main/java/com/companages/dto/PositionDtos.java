package com.companages.dto;
import com.companages.entity.RecordStatus; import jakarta.validation.constraints.*; import java.time.Instant;
public final class PositionDtos { private PositionDtos(){}
 public record Request(@NotBlank @Size(max=120) String name,@Size(max=500) String description,@Min(1) @Max(20) Integer level){}
 public record Response(Long id,String name,String description,Integer level,RecordStatus status,Long organizationId,Instant createdAt,Instant updatedAt){}
}
