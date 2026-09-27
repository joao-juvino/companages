package com.companages.dto; import com.companages.entity.ActivityAction; import java.time.Instant;
public final class ActivityDtos{private ActivityDtos(){} public record Response(Long id,ActivityAction action,Long actorId,String actorName,String targetType,String targetId,String metadata,Instant createdAt){} }
