package com.gamedoora.backend.userservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamedoora.model.dao.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
	Users findByEmailId(String emailId);

	List<Users> findByRole_Id(long id);

	List<Users> findBySkills_Id(long id);

	List<Users> findByFirstName(String firstName);

	List<Users> findByLastName(String lastName);

	List<Users> findBySkills_Name(String name);

	List<Users> findByRole_Name(String name);

	List<Users> findByStudiosSet_EmailId(String emailId);

	List<Users> findListByEmailId(String emailId);
}
