package com.bestsearch.bestsearchservice.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bestsearch.bestsearchservice.organization.model.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<List<Organization>> findByOrganizationType(long organizationTypeId);

}
