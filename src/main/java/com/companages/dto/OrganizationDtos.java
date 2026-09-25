package com.companages.dto;
import jakarta.validation.constraints.*; import java.time.Instant;
public final class OrganizationDtos { private OrganizationDtos(){}
 public record CreateRequest(@NotBlank @Size(max=120) String name,@Size(max=500) String description){}
 public record UpdateRequest(@NotBlank @Size(max=120) String name,@Size(max=500) String description){}
 public record Response(Long id,String name,String description,Instant createdAt,Instant updatedAt){}
}
