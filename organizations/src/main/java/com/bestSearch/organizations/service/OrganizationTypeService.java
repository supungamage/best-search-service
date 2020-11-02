package com.bestSearch.organizations.service;

import com.bestSearch.organizations.dto.OrganizationTypeInputDTO;
import com.bestSearch.organizations.dto.OrganizationTypeOutputDTO;
import java.util.List;


public interface OrganizationTypeService { 
	
	OrganizationTypeOutputDTO addOrganizationType(OrganizationTypeInputDTO organizationTypeInputDTO);
	
	List<OrganizationTypeOutputDTO> getOrganizationTypes();
	
	OrganizationTypeOutputDTO getOrganizationTypeById(long id);
	
	OrganizationTypeOutputDTO updateOrganizationType(long id, OrganizationTypeInputDTO organizationTypeInputDTO);

}
