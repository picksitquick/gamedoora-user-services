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
import com.gamedoora.backend.userservices.dto.SourceDTO;
import com.gamedoora.backend.userservices.repository.SourcesRepository;
import com.gamedoora.model.dao.Sources;

@Component
public class SourcesServicesAssembler {

	@Autowired
	private SourcesRepository sourcesRepository;

	public ResponseEntity<SourceDTO> createSources(SourceDTO SourcesDto) {

		try {
			Sources Sources = new Sources();
			Sources.setName(SourcesDto.getName());
			Sources.setCreatedBy("GameDoora");
			Sources.setUpdateBy("GameDoora");
			Sources.setCreatedOn(new Date());
			Sources.setUpdateOn(new Date());

			sourcesRepository.save(Sources);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<RoleDTO> updateSources(long id, RoleDTO SourcesDto) {

		Optional<Sources> sourcesRes = sourcesRepository.findById(id);
		if (sourcesRes.isPresent()) {
			Sources sources = sourcesRes.get();
			sources.setName(SourcesDto.getName());

			sourcesRepository.save(sources);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<HttpStatus> deleteSources(long id) {
		try {
			sourcesRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<HttpStatus> deleteAllSources() {
		try {
			sourcesRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Sources>> getAllSources(String name) {
		try {
			List<Sources> sources = new ArrayList<Sources>();
			if (name == null)
				sourcesRepository.findAll().forEach(sources::add);
			else
				sourcesRepository.findByNameContaining(name).forEach(sources::add);
			if (sources.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(sources, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
