package com.companages.dto;
import com.companages.entity.RecordStatus; import jakarta.validation.constraints.*; import java.time.Instant;
public final class TeamDtos {private TeamDtos(){} public record Request(@NotBlank @Size(max=120)String name,@Size(max=500)String description,Long leadMemberId){} public record Response(Long id,String name,String description,Long leadMemberId,String leadMemberName,RecordStatus status,long memberCount,Instant createdAt,Instant updatedAt){} }
