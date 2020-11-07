package com.bestSearch.organization.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bestSearch.organization.model.OrganizationType;
import com.bestSearch.organization.dto.OrganizationOutputDTO;
import com.bestSearch.share.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "organization")
@Getter
@SuperBuilder
@NoArgsConstructor
public class Organization extends Auditable<String> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="org_seq")
    @SequenceGenerator(name = "org_seq", sequenceName = "org_seq" ,allocationSize = 1)
	private long id;
	
	@NonNull
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizationType")
	@JsonIgnore
	private OrganizationType organizationType;
	
	private String province;
	private String district;
	private String city;
	
	@NonNull
	private Double longitude;
	
	@NonNull
	private Double latitude;

	@Builder.Default
	private boolean isActive = true;

	@JsonIgnore
	public OrganizationOutputDTO viewAsOrganizationOutputDTO() {
		return OrganizationOutputDTO.builder().id(id).name(name).city(city).district(district)
				.province(province).isActive(isActive).latitude(latitude).longitude(longitude)
				.type(organizationType.viewAsOrganizationTypeOutputDTO())
				.build();
	}
}
