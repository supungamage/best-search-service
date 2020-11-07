package com.bestSearch.organization.service;

import com.bestSearch.organization.dto.OrganizationTypeInputDTO;
import com.bestSearch.organization.dto.OrganizationTypeOutputDTO;
import java.util.List;


public interface OrganizationTypeService { 
	
	OrganizationTypeOutputDTO addOrganizationType(OrganizationTypeInputDTO organizationTypeInputDTO);
	
	List<OrganizationTypeOutputDTO> getOrganizationTypes();
	
	OrganizationTypeOutputDTO getOrganizationTypeById(long id);
	
	OrganizationTypeOutputDTO updateOrganizationType(long id, OrganizationTypeInputDTO organizationTypeInputDTO);
}
