package com.gamedoora.backend.userservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamedoora.model.dao.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	List<Users> findByEmailContaining(String name);
}
