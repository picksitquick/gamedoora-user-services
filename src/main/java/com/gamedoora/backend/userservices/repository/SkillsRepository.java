package com.gamedoora.backend.userservices.repository;

import java.util.List;

import com.gamedoora.model.dao.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamedoora.model.dao.Skills;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {

	List<Skills> findByNameContaining(String name);

	List<Skills> findBySources_Name(String name);

	List<Skills> findByRoles_Name(String name);

	List<Skills> findByUsers_FirstName(String name);
}
