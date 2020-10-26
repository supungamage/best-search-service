package com.bestsearch.bestsearchservice.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bestsearch.bestsearchservice.organization.model.OrganizationType;

public interface OrganizationTypeRepository extends JpaRepository<OrganizationType, Long> {

}
