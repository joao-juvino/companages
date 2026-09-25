package com.companages.service;
import com.companages.dto.OrganizationDtos.*; import com.companages.entity.Organization; import com.companages.exception.ResourceNotFoundException; import com.companages.repository.OrganizationRepository; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.List;
@Service public class OrganizationService { private final OrganizationRepository organizations; private final CurrentUserService current; public OrganizationService(OrganizationRepository o,CurrentUserService c){organizations=o;current=c;}
 @Transactional public Response create(CreateRequest r){Organization o=new Organization();o.setName(r.name().trim());o.setDescription(r.description());o.setOwner(current.get());return Mappers.organization(organizations.save(o));}
 @Transactional(readOnly=true) public List<Response> list(){return organizations.findAllByOwnerIdOrderByCreatedAtDesc(current.get().getId()).stream().map(Mappers::organization).toList();}
 @Transactional(readOnly=true) public Response get(Long id){return Mappers.organization(require(id));}
 @Transactional public Response update(Long id,UpdateRequest r){Organization o=require(id);o.setName(r.name().trim());o.setDescription(r.description());return Mappers.organization(o);}
 @Transactional public void delete(Long id){organizations.delete(require(id));}
 @Transactional(readOnly=true) public Organization require(Long id){return organizations.findByIdAndOwnerId(id,current.get().getId()).orElseThrow(()->new ResourceNotFoundException("Organization not found"));}
}
