package com.companages.dto;
import java.util.List;
public record DashboardResponse(long organizations,long members,long positions,long assignments,List<MemberDtos.Response> recentMembers){}
