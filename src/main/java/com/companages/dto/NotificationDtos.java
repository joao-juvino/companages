package com.companages.dto; import java.time.Instant;
public final class NotificationDtos{private NotificationDtos(){} public record Response(Long id,Long organizationId,String type,String title,String message,String link,boolean read,Instant createdAt){} public record Summary(long unreadCount){} }
