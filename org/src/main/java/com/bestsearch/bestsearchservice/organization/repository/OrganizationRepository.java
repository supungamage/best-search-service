package com.bestsearch.bestsearchservice.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bestsearch.bestsearchservice.organization.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
