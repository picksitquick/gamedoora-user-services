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

import com.gamedoora.backend.userservices.assembler.SourcesServicesAssembler;
import com.gamedoora.backend.userservices.dto.RoleDTO;
import com.gamedoora.backend.userservices.dto.SourceDTO;
import com.gamedoora.model.dao.Sources;

@RestController
public class SourcesServicesController implements BaseController {

	@Autowired
	SourcesServicesAssembler sourcesServicesAssembler;

	@PostMapping(value = "/createSources")
	public ResponseEntity<SourceDTO> createSources(@RequestBody SourceDTO sourcesDto) {
		return sourcesServicesAssembler.createSources(sourcesDto);

	}

	@PutMapping("/Sources/{id}")
	public ResponseEntity<RoleDTO> updateSources(@PathVariable("id") long id, @RequestBody RoleDTO SourcesDto) {
		return sourcesServicesAssembler.updateSources(id, SourcesDto);

	}

	@DeleteMapping("/Sources/{id}")
	public ResponseEntity<HttpStatus> deleteSources(@PathVariable("id") long id) {
		return sourcesServicesAssembler.deleteSources(id);
	}

	@DeleteMapping("/deleteAllSources")
	public ResponseEntity<HttpStatus> deleteAllSources() {
		return sourcesServicesAssembler.deleteAllSources();
	}

	@GetMapping("/getSources")
	public ResponseEntity<List<Sources>> getAllSources(@RequestParam(required = false) String name) {
		return sourcesServicesAssembler.getAllSources(name);
	}
}
