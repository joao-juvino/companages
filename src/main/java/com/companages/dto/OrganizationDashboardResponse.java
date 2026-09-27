package com.companages.dto; import java.util.*;
public record OrganizationDashboardResponse(long members,long activeMembers,long teams,long positions,long pendingInvitations,List<MemberDtos.Response> recentMembers,Map<String,Long> membersByTeam,Map<String,Long> membersByPosition,Map<String,Long> membersByStatus,Map<String,Long> memberGrowth){}
