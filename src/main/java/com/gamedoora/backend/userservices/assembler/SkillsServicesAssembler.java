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

  public SkillsDTO createSkills(SkillsDTO SkillsDto) {

    Skills skills = new Skills();
    skills.setName(SkillsDto.getName());
    skills.setDescription(SkillsDto.getDescription());
    skills.setCreatedBy("GameDoora");
    skills.setUpdateBy("GameDoora");
    skills.setCreatedOn(new Date());
    skills.setUpdateOn(new Date());

    skillsRepository.save(skills);

    return SkillsDto;
  }

  public SkillsDTO updateSkills(long id, SkillsDTO skillsDto) {

    Optional<Skills> skillsRes = skillsRepository.findById(id);
    if (!skillsRes.isPresent()) {
      return null;
    }
    Skills skills = skillsRes.get();
    skills.setName(skillsDto.getName());
    skills.setDescription(skillsDto.getDescription());
    skillsRepository.save(skills);
    return skillsDto;
  }

  public void deleteSkills(long id) {
    skillsRepository.deleteById(id);
  }

  public void deleteAllSkills() {
    skillsRepository.deleteAll();
  }

  public List<Skills> getAllSkills(String name) {
    List<Skills> skills = new ArrayList<Skills>();
    if (name == null) {
      skillsRepository.findAll().forEach(skills::add);
      return skills;
    }
    skillsRepository.findByNameContaining(name).forEach(skills::add);
    return skills;
  }
}