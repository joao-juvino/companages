package com.companages.service;
import com.companages.dto.*; import com.companages.entity.*;
final class Mappers { private Mappers(){}
 static AuthDtos.UserResponse user(User u){return new AuthDtos.UserResponse(u.getId(),u.getName(),u.getEmail(),u.getAvatarUrl(),u.getPhone(),u.getBio(),u.getCreatedAt());}
 static OrganizationDtos.Response organization(Organization o,AccessRole role){return new OrganizationDtos.Response(o.getId(),o.getName(),o.getDescription(),o.getLogoUrl(),o.getWebsite(),o.getPhone(),o.getEmail(),o.getAddress(),o.getStatus(),role,o.getCreatedAt(),o.getUpdatedAt());}
 static MemberDtos.Response member(Member m){return new MemberDtos.Response(m.getId(),m.getName(),m.getEmail(),m.getUser()==null?null:m.getUser().getId(),m.getOrganization().getId(),m.getAccessRole(),m.getPosition()==null?null:m.getPosition().getId(),m.getPosition()==null?null:m.getPosition().getName(),m.getTeam()==null?null:m.getTeam().getId(),m.getTeam()==null?null:m.getTeam().getName(),m.getManager()==null?null:m.getManager().getId(),m.getManager()==null?null:m.getManager().getName(),m.getStatus(),m.getJoinedAt(),m.getLocation(),m.getPhone(),m.getBio(),m.getAvatarUrl(),m.getCreatedAt(),m.getUpdatedAt());}
 static PositionDtos.Response position(Position p){return new PositionDtos.Response(p.getId(),p.getName(),p.getDescription(),p.getLevel(),p.getStatus(),p.getOrganization().getId(),p.getCreatedAt(),p.getUpdatedAt());}
 static TeamDtos.Response team(Team t,long members){return new TeamDtos.Response(t.getId(),t.getName(),t.getDescription(),t.getLead()==null?null:t.getLead().getId(),t.getLead()==null?null:t.getLead().getName(),t.getStatus(),members,t.getCreatedAt(),t.getUpdatedAt());}
 static AssignmentDtos.Response assignment(Assignment a){return new AssignmentDtos.Response(a.getId(),a.getOrganization().getId(),member(a.getMember()),position(a.getPosition()),a.getCreatedAt());}
}
