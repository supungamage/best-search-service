package com.bestSearch.organizations.repository;

import com.bestSearch.organizations.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
