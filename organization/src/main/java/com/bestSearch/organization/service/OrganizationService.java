package com.bestSearch.organization.service;

import com.bestSearch.organization.dto.OrganizationInputDTO;
import com.bestSearch.organization.dto.OrganizationOutputDTO;

import java.util.List;

public interface OrganizationService {

    OrganizationOutputDTO addOrganization(OrganizationInputDTO organizationInputDTO);

    List<OrganizationOutputDTO> getActiveOrganizationsByType(long organizationTypeId);

    OrganizationOutputDTO getOrganizationById(long id);

    OrganizationOutputDTO updateOrganization(long id, OrganizationInputDTO organizationInputDTO);
}
