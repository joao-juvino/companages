package com.companages.dto;
import com.companages.entity.*; import jakarta.validation.constraints.*; import java.time.*;
public final class MemberDtos { private MemberDtos(){}
 public record Request(@NotBlank @Size(max=120) String name,@Email @Size(max=255) String email,AccessRole accessRole,Long positionId,Long teamId,Long managerId,RecordStatus status,LocalDate joinedAt,@Size(max=120) String location,@Size(max=40) String phone,@Size(max=1000) String bio,@Size(max=500) String avatarUrl){}
 public record Response(Long id,String name,String email,Long userId,Long organizationId,AccessRole accessRole,Long positionId,String positionName,Long teamId,String teamName,Long managerId,String managerName,RecordStatus status,LocalDate joinedAt,String location,String phone,String bio,String avatarUrl,Instant createdAt,Instant updatedAt){}
 public record ChartNode(Long id,String name,String email,String avatarUrl,String position,String team,String role,Long managerId,java.util.List<ChartNode> children){}
}
