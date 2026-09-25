package com.companages.service;
import com.companages.dto.PositionDtos.*; import com.companages.entity.*; import com.companages.exception.ResourceNotFoundException; import com.companages.repository.PositionRepository; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.List;
@Service public class PositionService { private final PositionRepository positions; private final OrganizationService organizations; public PositionService(PositionRepository p,OrganizationService o){positions=p;organizations=o;}
 @Transactional public Response create(Long oid,Request r){Organization o=organizations.require(oid);Position p=new Position();p.setName(r.name().trim());p.setDescription(r.description());p.setOrganization(o);return Mappers.position(positions.save(p));}
 @Transactional(readOnly=true) public List<Response> list(Long oid){organizations.require(oid);return positions.findAllByOrganizationIdOrderByName(oid).stream().map(Mappers::position).toList();}
 @Transactional(readOnly=true) public Response get(Long oid,Long id){organizations.require(oid);return Mappers.position(require(oid,id));}
 @Transactional public Response update(Long oid,Long id,Request r){organizations.require(oid);Position p=require(oid,id);p.setName(r.name().trim());p.setDescription(r.description());return Mappers.position(p);}
 @Transactional public void delete(Long oid,Long id){organizations.require(oid);positions.delete(require(oid,id));}
 Position require(Long oid,Long id){return positions.findByIdAndOrganizationId(id,oid).orElseThrow(()->new ResourceNotFoundException("Position not found"));}
}
