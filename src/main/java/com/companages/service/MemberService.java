package com.companages.service;
import com.companages.dto.MemberDtos.*; import com.companages.entity.*; import com.companages.exception.ResourceNotFoundException; import com.companages.repository.MemberRepository; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.List;
@Service public class MemberService { private final MemberRepository members; private final OrganizationService organizations; public MemberService(MemberRepository m,OrganizationService o){members=m;organizations=o;}
 @Transactional public Response create(Long oid,Request r){Organization o=organizations.require(oid);Member m=new Member();m.setName(r.name().trim());m.setEmail(blank(r.email()));m.setOrganization(o);return Mappers.member(members.save(m));}
 @Transactional(readOnly=true) public List<Response> list(Long oid){organizations.require(oid);return members.findAllByOrganizationIdOrderByName(oid).stream().map(Mappers::member).toList();}
 @Transactional(readOnly=true) public Response get(Long oid,Long id){organizations.require(oid);return Mappers.member(require(oid,id));}
 @Transactional public Response update(Long oid,Long id,Request r){organizations.require(oid);Member m=require(oid,id);m.setName(r.name().trim());m.setEmail(blank(r.email()));return Mappers.member(m);}
 @Transactional public void delete(Long oid,Long id){organizations.require(oid);members.delete(require(oid,id));}
 Member require(Long oid,Long id){return members.findByIdAndOrganizationId(id,oid).orElseThrow(()->new ResourceNotFoundException("Member not found"));} private String blank(String v){return v==null||v.isBlank()?null:v.trim();}
}
