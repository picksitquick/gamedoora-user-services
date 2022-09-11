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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gamedoora.backend.userservices.assembler.SkillsServicesAssembler;
import com.gamedoora.backend.userservices.dto.RoleDTO;
import com.gamedoora.backend.userservices.dto.SkillsDTO;
import com.gamedoora.model.dao.Skills;

@RestController
@RequestMapping("/api/users")
public class UserServicesController extends BaseController {

	@Autowired
	SkillsServicesAssembler skillsServicesAssembler;

	@PostMapping(value = "/createSkills")
	public ResponseEntity<SkillsDTO> createSkills(@RequestBody SkillsDTO skillsDto) {
		return skillsServicesAssembler.createSkills(skillsDto);

	}

	@PutMapping("/Skills/{id}")
	public ResponseEntity<RoleDTO> updateSkills(@PathVariable("id") long id, @RequestBody RoleDTO skillsDto) {
		return skillsServicesAssembler.updateSkills(id, skillsDto);

	}

	@DeleteMapping("/Skills/{id}")
	public ResponseEntity<HttpStatus> deleteSkills(@PathVariable("id") long id) {
		return skillsServicesAssembler.deleteSkills(id);
	}

	@DeleteMapping("/deleteAllSkills")
	public ResponseEntity<HttpStatus> deleteAllSkills() {
		return skillsServicesAssembler.deleteAllSkills();
	}

	@GetMapping("/getSkills")
	public ResponseEntity<List<Skills>> getAllSkills(@RequestParam(required = false) String name) {
		return skillsServicesAssembler.getAllSkills(name);
	}
}
