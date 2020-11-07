package com.bestSearch.organization.service.impl;

import com.bestSearch.organization.dto.OrganizationTypeInputDTO;
import com.bestSearch.organization.dto.OrganizationTypeOutputDTO;
import com.bestSearch.organization.model.OrganizationType;
import com.bestSearch.organization.repository.OrganizationTypeRepository;
import com.bestSearch.organization.service.OrganizationTypeService;
import com.bestSearch.share.exception.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


@Service
public class OrganizationTypeServiceImpl implements OrganizationTypeService {
	
	private final OrganizationTypeRepository organizationTypeRepository;
	

	public OrganizationTypeServiceImpl(final OrganizationTypeRepository organizationTypeRepository) {
		this.organizationTypeRepository = organizationTypeRepository;
	}

	@Override
	public List<OrganizationTypeOutputDTO> getOrganizationTypes() {
		return organizationTypeRepository.findAll().stream()
				.map(OrganizationType::viewAsOrganizationTypeOutputDTO)
				.collect(Collectors.toList());
	}

	@Override
	public OrganizationTypeOutputDTO getOrganizationTypeById(long id) {
		return organizationTypeRepository.findById(id).map(OrganizationType::viewAsOrganizationTypeOutputDTO)
				.orElseThrow(() -> new ResourceNotFoundException("OrganizationType not configured"));
	}
	
	@Override
	public OrganizationTypeOutputDTO addOrganizationType(OrganizationTypeInputDTO organizationTypeInputDTO) {
		return organizationTypeRepository.save(OrganizationType.builder().type(organizationTypeInputDTO.getType()).build())
				.viewAsOrganizationTypeOutputDTO();
	}
	
	@Override
	public OrganizationTypeOutputDTO updateOrganizationType(long id, OrganizationTypeInputDTO organizationTypeInputDTO) {
		return organizationTypeRepository.save(OrganizationType.builder().type(organizationTypeInputDTO.getType()).id(id).build())
				.viewAsOrganizationTypeOutputDTO();
	}

}
