package com.companeges.demo.services;

import java.util.List;

import com.companeges.demo.dto.OrganizationPostPutRequestDTO;

public interface OrganizationService {
    OrganizationPostPutRequestDTO createOrganization(OrganizationPostPutRequestDTO organizationPostPutRequestDTO);
    List<OrganizationPostPutRequestDTO> getOrganization();
}
