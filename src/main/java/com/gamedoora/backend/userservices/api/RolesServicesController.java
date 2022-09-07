package com.gamedoora.backend.userservices.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gamedoora.backend.userservices.assembler.RolesServicesAssembler;
import com.gamedoora.backend.userservices.dto.RoleDTO;
import com.gamedoora.model.dao.Roles;

@RestController
public class RolesServicesController implements BaseController {

	@Autowired
	RolesServicesAssembler rolesServicesAssembler;

	@PostMapping(value = "/")
	public ResponseEntity<RoleDTO> createRoles(@RequestBody RoleDTO rolesDto) {
		return rolesServicesAssembler.createRoles(rolesDto);

	}

	@PutMapping("/roles/{id}")
	public ResponseEntity<RoleDTO> updateRoles(@PathVariable("id") long id, @RequestBody RoleDTO rolesDto) {
		return rolesServicesAssembler.updateRoles(id, rolesDto);

	}

	@DeleteMapping("/roles/{id}")
	public ResponseEntity<HttpStatus> deleteRoles(@PathVariable("id") long id) {
		return rolesServicesAssembler.deleteRoles(id);
	}

	@DeleteMapping("/")
	public ResponseEntity<HttpStatus> deleteAllRoles() {
		return rolesServicesAssembler.deleteAllRoles();
	}

	@GetMapping("/")
	public ResponseEntity<List<Roles>> getAllRoles(@RequestParam(required = false) String name) {
		return rolesServicesAssembler.getAllRoles(name);
	}
}
