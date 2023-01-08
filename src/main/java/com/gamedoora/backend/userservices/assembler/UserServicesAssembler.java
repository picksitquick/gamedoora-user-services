package com.gamedoora.backend.userservices.assembler;

import com.gamedoora.backend.userservices.dto.UserDTO;
import com.gamedoora.backend.userservices.repository.UsersRepository;
import com.gamedoora.model.dao.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServicesAssembler {

  @Autowired private UsersRepository usersRepository;

  public UserDTO createUsers(UserDTO userDto) {

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
    return userDto;
  }

  public UserDTO updateUsers(long id, UserDTO userDto) {

    Optional<Users> usersRes = usersRepository.findById(id);
    if (usersRes.isPresent()) {
      return null;
    }
    Users users = usersRes.get();
    users.setEmail(userDto.getEmail());
    users.setFirstName(userDto.getFirstName());
    users.setLastName(userDto.getLastName());
    usersRepository.save(users);
    return userDto;
  }

  public void deleteUsers(long id) {
    usersRepository.deleteById(id);
  }

  public void deleteAllUsers() {
    usersRepository.deleteAll();
  }

  public List<Users> getAllUsers(String email) {

    List<Users> users = new ArrayList<>();
    if (email == null) usersRepository.findAll().forEach(users::add);
    else usersRepository.findByEmailContaining(email).forEach(users::add);
    if (users.isEmpty()) {
      return null;
    }
    return users;
  }
}