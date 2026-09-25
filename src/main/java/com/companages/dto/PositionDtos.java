package com.companages.dto;
import jakarta.validation.constraints.*; import java.time.Instant;
public final class PositionDtos { private PositionDtos(){}
 public record Request(@NotBlank @Size(max=120) String name,@Size(max=500) String description){}
 public record Response(Long id,String name,String description,Long organizationId,Instant createdAt,Instant updatedAt){}
}
