package com.bestSearch.organization.service.impl;

import com.bestSearch.organization.model.OrganizationType;
import com.bestSearch.organization.repository.OrganizationRepository;
import com.bestSearch.organization.repository.OrganizationTypeRepository;
import com.bestSearch.organization.service.OrganizationService;
import com.bestSearch.share.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.bestSearch.organization.dto.OrganizationInputDTO;
import com.bestSearch.organization.dto.OrganizationOutputDTO;
import com.bestSearch.organization.model.Organization;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    final private OrganizationRepository organizationRepository;
    final private OrganizationTypeRepository organizationTypeRepository;


    public OrganizationServiceImpl(final OrganizationRepository organizationRepository,
                                   final OrganizationTypeRepository organizationTypeRepository) {
        this.organizationRepository = organizationRepository;
        this.organizationTypeRepository = organizationTypeRepository;
    }

    @Override
    public OrganizationOutputDTO addOrganization(OrganizationInputDTO organizationInputDTO) {
        OrganizationType organizationType = organizationTypeRepository.findById(organizationInputDTO.getOrganizationTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("No organization type configured"));

        return organizationRepository.save(Organization.builder()
                .name(organizationInputDTO.getName())
                .province(organizationInputDTO.getProvince())
                .city(organizationInputDTO.getCity())
                .district(organizationInputDTO.getDistrict())
                .latitude(organizationInputDTO.getLatitude())
                .longitude(organizationInputDTO.getLongitude())
                .organizationType(organizationType)
                .build()).viewAsOrganizationOutputDTO();
    }

    @Override
    public List<OrganizationOutputDTO> getActiveOrganizationsByType(long organizationTypeId) {
        return organizationRepository.findActiveOrganizationByType(organizationTypeId, true)
                .orElseThrow(() -> new ResourceNotFoundException("No organization available for given type"))
                .stream()
                .map(Organization::viewAsOrganizationOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationOutputDTO getOrganizationById(long id) {
        return organizationRepository.findById(id).map(Organization::viewAsOrganizationOutputDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found"));
    }

    @Override
    public OrganizationOutputDTO updateOrganization(long id, OrganizationInputDTO organizationInputDTO) {
        OrganizationType organizationType = organizationTypeRepository.findById(organizationInputDTO.getOrganizationTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("No organization type configured"));

        return organizationRepository.save(Organization.builder()
                .id(id)
                .name(organizationInputDTO.getName())
                .province(organizationInputDTO.getProvince())
                .city(organizationInputDTO.getCity())
                .district(organizationInputDTO.getDistrict())
                .latitude(organizationInputDTO.getLatitude())
                .longitude(organizationInputDTO.getLongitude())
                .organizationType(organizationType)
                .build()).viewAsOrganizationOutputDTO();
    }
}
