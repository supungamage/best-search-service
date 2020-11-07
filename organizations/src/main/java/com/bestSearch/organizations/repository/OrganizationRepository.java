package com.bestSearch.organizations.repository;

import com.bestSearch.organizations.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<List<Organization>> findByOrganizationType(long organizationTypeId);

}
