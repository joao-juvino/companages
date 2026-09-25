package com.companages.dto;
import jakarta.validation.constraints.*; import java.time.Instant;
public final class AssignmentDtos { private AssignmentDtos(){}
 public record CreateRequest(@NotNull Long memberId,@NotNull Long positionId){}
 public record Response(Long id,Long organizationId,MemberDtos.Response member,PositionDtos.Response position,Instant createdAt){}
}
