package com.companages.dto;
import com.companages.entity.*; import jakarta.validation.constraints.*; import java.time.Instant;
public final class OrganizationDtos { private OrganizationDtos(){}
 public record CreateRequest(@NotBlank @Size(max=120) String name,@Size(max=500) String description,@Size(max=500) String logoUrl,@Size(max=255) String website,@Size(max=40) String phone,@Email String email,@Size(max=500) String address){}
 public record UpdateRequest(@NotBlank @Size(max=120) String name,@Size(max=500) String description,@Size(max=500) String logoUrl,@Size(max=255) String website,@Size(max=40) String phone,@Email String email,@Size(max=500) String address){}
 public record Response(Long id,String name,String description,String logoUrl,String website,String phone,String email,String address,RecordStatus status,AccessRole currentRole,Instant createdAt,Instant updatedAt){}
}
