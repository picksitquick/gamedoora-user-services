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
import com.gamedoora.backend.userservices.dto.SkillsDTO;
import com.gamedoora.backend.userservices.repository.SkillsRepository;
import com.gamedoora.model.dao.Skills;

@Component
public class SkillsServicesAssembler {

	@Autowired
	private SkillsRepository skillsRepository;

	public ResponseEntity<SkillsDTO> createSkills(SkillsDTO SkillsDto) {

		try {
			Skills skills = new Skills();
			skills.setName(SkillsDto.getName());
			skills.setDescription(SkillsDto.getDescription());
			skills.setCreatedBy("GameDoora");
			skills.setUpdateBy("GameDoora");
			skills.setCreatedOn(new Date());
			skills.setUpdateOn(new Date());

			skillsRepository.save(skills);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<RoleDTO> updateSkills(long id, RoleDTO skillsDto) {

		Optional<Skills> skillsRes = skillsRepository.findById(id);
		if (skillsRes.isPresent()) {
			Skills skills = skillsRes.get();
			skills.setName(skillsDto.getName());
			skills.setDescription(skillsDto.getDescription());
			skillsRepository.save(skills);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<HttpStatus> deleteSkills(long id) {
		try {
			skillsRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<HttpStatus> deleteAllSkills() {
		try {
			skillsRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Skills>> getAllSkills(String name) {
		try {
			List<Skills> skills = new ArrayList<Skills>();
			if (name == null)
				skillsRepository.findAll().forEach(skills::add);
			else
				skillsRepository.findByNameContaining(name).forEach(skills::add);
			if (skills.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(skills, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}