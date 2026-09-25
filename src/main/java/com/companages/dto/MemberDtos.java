package com.companages.dto;
import jakarta.validation.constraints.*; import java.time.Instant;
public final class MemberDtos { private MemberDtos(){}
 public record Request(@NotBlank @Size(max=120) String name,@Email @Size(max=255) String email){}
 public record Response(Long id,String name,String email,Long organizationId,Instant createdAt,Instant updatedAt){}
}
