package com.gamedoora.backend.userservices.assembler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.gamedoora.backend.userservices.dto.RoleDTO;
import com.gamedoora.backend.userservices.repository.RolesRepository;
import com.gamedoora.model.dao.Roles;

@Component
public class RolesServicesAssembler {

	@Autowired
	private RolesRepository rolesRepository;

	public ResponseEntity<RoleDTO> createRoles(RoleDTO rolesDto) {

		try {
			Roles roles = new Roles();
			roles.setName(rolesDto.getName());
			roles.setDescription(rolesDto.getDescription());
			roles.setCreatedBy("GameDoora");
			roles.setUpdateBy("GameDoora");
			roles.setCreatedOn(new Date());
			roles.setUpdateOn(new Date());

			rolesRepository.save(roles);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<RoleDTO> updateRoles(long id, RoleDTO rolesDto) {

		Optional<Roles> rolesRes = rolesRepository.findById(id);
		if (rolesRes.isPresent()) {
			Roles roles = rolesRes.get();
			roles.setName(rolesDto.getName());
			roles.setDescription(rolesDto.getDescription());
			rolesRepository.save(roles);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<HttpStatus> deleteRoles(long id) {
		try {
			rolesRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<HttpStatus> deleteAllRoles() {
		try {
			rolesRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Roles>> getAllRoles(String name) {
		try {
			List<Roles> roles = new ArrayList<Roles>();
			if (name == null)
				rolesRepository.findAll().forEach(roles::add);
			else
				rolesRepository.findByNameContaining(name).forEach(roles::add);
			if (roles.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(roles, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
