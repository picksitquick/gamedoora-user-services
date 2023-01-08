package com.gamedoora.backend.userservices.assembler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gamedoora.backend.userservices.dto.RoleDTO;
import com.gamedoora.backend.userservices.repository.RolesRepository;
import com.gamedoora.model.dao.Roles;

@Component
public class RolesServicesAssembler {

  @Autowired private RolesRepository rolesRepository;

  public RoleDTO createRoles(RoleDTO rolesDto) {

    Roles roles = new Roles();
    roles.setName(rolesDto.getName());
    roles.setDescription(rolesDto.getDescription());
    roles.setCreatedBy("GameDoora");
    roles.setUpdateBy("GameDoora");
    roles.setCreatedOn(new Date());
    roles.setUpdateOn(new Date());

    rolesRepository.save(roles);
    return rolesDto;
  }

  public RoleDTO updateRoles(long id, RoleDTO rolesDto) {

    Optional<Roles> rolesRes = rolesRepository.findById(id);
    if (!rolesRes.isPresent()) {
      return null;
    }
    Roles roles = rolesRes.get();
    roles.setName(rolesDto.getName());
    roles.setDescription(rolesDto.getDescription());
    rolesRepository.save(roles);
    return rolesDto;
  }

  public void deleteRoles(long id) {
    rolesRepository.deleteById(id);
  }

  public void deleteAllRoles() {
    rolesRepository.deleteAll();
  }

  public List<Roles> getAllRoles(String name) {
    List<Roles> roles = new ArrayList<>();
    if (name == null) rolesRepository.findAll().forEach(roles::add);
    else rolesRepository.findByNameContaining(name).forEach(roles::add);

    return roles;
  }
}
