package com.companeges.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companeges.demo.dto.OrganizationPostPutRequestDTO;
import com.companeges.demo.models.Organization;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    
    @Autowired
    ModelMapper modelMapper;

    @Override
    public OrganizationPostPutRequestDTO createOrganization(
            OrganizationPostPutRequestDTO organizationPostPutRequestDTO) {
        Organization org = modelMapper.map(organizationPostPutRequestDTO, Organization.class);
        this.organizationRepository.save(org);
        return modelMapper.map(org, OrganizationPostPutRequestDTO.class);

    }
    
}
