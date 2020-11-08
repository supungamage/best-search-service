package com.bestSearch.organization.controller;

import com.bestSearch.organization.dto.OrganizationTypeInputDTO;
import com.bestSearch.organization.dto.OrganizationTypeOutputDTO;
import com.bestSearch.organization.service.OrganizationTypeService;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/organization-types")
public class OrganizationTypeController { 
	
	private final OrganizationTypeService organizationTypeService;

	public OrganizationTypeController(OrganizationTypeService organizationTypeService) {
		this.organizationTypeService = organizationTypeService;
	}
	
	@GetMapping
	public ResponseEntity<List<OrganizationTypeOutputDTO>> getAllOrganizationTypes() {
		return ResponseEntity.ok(this.organizationTypeService.getOrganizationTypes());
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<OrganizationTypeOutputDTO> getOrganizationTypeById(@PathVariable("id") long id) {
		return ResponseEntity.ok(this.organizationTypeService.getOrganizationTypeById(id));
	} 
	
	@PostMapping
	public ResponseEntity<OrganizationTypeOutputDTO> addOrganizationType(@RequestBody OrganizationTypeInputDTO organizationTypeInputDTO) {
		return new ResponseEntity<>(this.organizationTypeService.addOrganizationType(organizationTypeInputDTO),  HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrganizationTypeOutputDTO> updateOrganizationTypeById(@PathVariable("id") long id, 
			@RequestBody OrganizationTypeInputDTO organizationTypeInputDTO) {
		return ResponseEntity.ok(this.organizationTypeService.updateOrganizationType(id, organizationTypeInputDTO));
	}

	@GetMapping("/hello")
	public String sayHello(){
		return "Hello...";
	}
}
