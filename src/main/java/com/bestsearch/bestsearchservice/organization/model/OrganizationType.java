package com.bestsearch.bestsearchservice.organization.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bestsearch.bestsearchservice.common.audit.Auditable;
import com.bestsearch.bestsearchservice.organization.dto.OrganizationTypeOutputDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "organization_type")
@Getter
@SuperBuilder
@NoArgsConstructor
public class OrganizationType extends Auditable<String> {
	
	@Id
	@GeneratedValue(generator = "OrganizationType_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "OrganizationType_id_seq", sequenceName = "OrganizationType_id_seq")
	private long id;
	
	private String type;
	private boolean isActive;
	
	@OneToMany(mappedBy = "organizationType", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Organization> organizations;
	
	@JsonIgnore
	public OrganizationTypeOutputDTO viewAsOrganizationTypeOutputDTO() {
		return OrganizationTypeOutputDTO.builder().id(id).type(type).isActive(isActive).build();
	}
}
