package com.gamedoora.backend.userservices.assembler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.gamedoora.backend.userservices.dto.UserDTO;
import com.gamedoora.backend.userservices.repository.UsersRepository;
import com.gamedoora.model.dao.Users;

@Component
public class UserServicesAssembler {

	@Autowired
	private UsersRepository usersRepository;

	public ResponseEntity<UserDTO> createUsers(UserDTO userDto) {

		try {
			Users users = new Users();
			users.setEmail(userDto.getEmail());
			users.setFirstName(userDto.getFirstName());
			users.setLastName(userDto.getLastName());
			users.setSignInCount(0);
			users.setPhotoUrl(userDto.getPhotoUrl());
			users.setPhoneNumber(userDto.getPhoneNumber());
			users.setCreatedBy("GameDoora");
			users.setUpdateBy("GameDoora");
			users.setCreatedOn(new Date());
			users.setUpdateOn(new Date());

			usersRepository.save(users);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<UserDTO> updateUsers(long id, UserDTO userDto) {

		Optional<Users> usersRes = usersRepository.findById(id);
		if (usersRes.isPresent()) {
			Users users = usersRes.get();
			users.setEmail(userDto.getEmail());
			users.setFirstName(userDto.getFirstName());
			users.setLastName(userDto.getLastName());
			usersRepository.save(users);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<HttpStatus> deleteUsers(long id) {
		try {
			usersRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			usersRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Users>> getAllUsers(String email) {
		try {
			List<Users> users = new ArrayList<Users>();
			if (email == null)
				usersRepository.findAll().forEach(users::add);
			else
				usersRepository.findByEmailContaining(email).forEach(users::add);
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}