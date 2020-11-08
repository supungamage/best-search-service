package com.bestSearch.organization.controller;

import com.bestSearch.organization.dto.OrganizationInputDTO;
import com.bestSearch.organization.dto.OrganizationOutputDTO;
import com.bestSearch.organization.dto.OrganizationTypeInputDTO;
import com.bestSearch.organization.dto.OrganizationTypeOutputDTO;
import com.bestSearch.organization.service.OrganizationService;
import com.bestSearch.organization.service.OrganizationTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {

	private final OrganizationService organizationService;

	public OrganizationController(final OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	@GetMapping("/type")
	public ResponseEntity<List<OrganizationOutputDTO>> getAllOrganizationsByType(@RequestParam ("typeId") long typeId) {
		return ResponseEntity.ok(this.organizationService.getOrganizationsByType(typeId));
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<OrganizationOutputDTO> getOrganizationById(@PathVariable("id") long id) {
		return ResponseEntity.ok(this.organizationService.getOrganizationById(id));
	} 
	
	@PostMapping
	public ResponseEntity<OrganizationOutputDTO> addOrganization(@RequestBody OrganizationInputDTO organizationInputDTO) {
		return new ResponseEntity<>(this.organizationService.addOrganization(organizationInputDTO),  HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrganizationOutputDTO> updateOrganizationById(@PathVariable("id") long id,
			@RequestBody OrganizationInputDTO organizationInputDTO) {
		return ResponseEntity.ok(this.organizationService.updateOrganization(id, organizationInputDTO));
	}

	@GetMapping("/hello")
	public String sayHello(){
		return "Hello...";
	}
}
