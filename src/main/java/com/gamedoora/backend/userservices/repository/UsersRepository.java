package com.gamedoora.backend.userservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamedoora.model.dao.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	List<Users> findByEmailContaining(String emailId);

	Users findByEmail(String email);

	List<Users> findBySkills_Id(String emailId);

	List<Users> findByRole_Id(String emailId);

	List <Users> findByFirstName(String firstName);
	List <Users> findByLastName(String lastName);
	List<Users> findBySkills_Name(String name);

    List<Users> findByRole_Name(String name);

	List<Users> findByStudiosSet_EmailId(String emailId);

	void deleteByEmailId(String emailId);
}
