package com.companeges.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companeges.demo.dto.OrganizationPostPutRequestDTO;
import com.companeges.demo.models.Organization;
import com.companeges.demo.repositories.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public OrganizationPostPutRequestDTO createOrganization(
            OrganizationPostPutRequestDTO organizationPostPutRequestDTO) {
        Organization org = modelMapper.map(organizationPostPutRequestDTO, Organization.class);
        this.organizationRepository.save(org);
        return modelMapper.map(org, OrganizationPostPutRequestDTO.class);

    }

    @Override
    public List<OrganizationPostPutRequestDTO> getOrganization() {
        List<Organization> organization = this.organizationRepository.findAll();
        
        return organization.stream()
                .map(org -> new OrganizationPostPutRequestDTO(org))
                .collect(Collectors.toList());
    }
    
}
