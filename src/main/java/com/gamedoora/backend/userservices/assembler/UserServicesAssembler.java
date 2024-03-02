package com.gamedoora.backend.userservices.assembler;

import com.gamedoora.backend.userservices.helpers.KeycloakResponseHelper;
import com.gamedoora.backend.userservices.helpers.UserOperationsHelper;
import com.gamedoora.backend.userservices.repository.UsersRepository;
import com.gamedoora.model.dao.Users;
import com.gamedoora.model.dto.UserDTO;
import com.gamedoora.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserServicesAssembler {

  private UsersRepository usersRepository;

  private UserMapper userMapper;

  private UserOperationsHelper userOperationsHelper;

  private KeycloakResponseHelper keycloakResponseHelper;

  public KeycloakResponseHelper getKeycloakResponseHelper() {
    return keycloakResponseHelper;
  }

  @Autowired
  public void setKeycloakResponseHelper(KeycloakResponseHelper keycloakResponseHelper) {
    this.keycloakResponseHelper = keycloakResponseHelper;
  }

  public UserOperationsHelper getUserOperationsHelper() {
    return userOperationsHelper;
  }

  @Autowired
  public void setUserOperationsHelper(UserOperationsHelper userOperationsHelper) {
    this.userOperationsHelper = userOperationsHelper;
  }

  public UsersRepository getUsersRepository() {
    return usersRepository;
  }

  @Autowired
  public void setUsersRepository(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  public UserMapper getUserMapper() {
    return userMapper;
  }

  @Autowired
  public void setUserMapper(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  //------------------------------------------------------------\\

  public UserDTO createUsers(UserDTO userDto) {

    userOperationsHelper.addUser(userDto);
    //exception mapper implementation, this will automatically catch exception and handle it.

    Users users = userMapper.userDtoToUsers(userDto);
    // need to remove the requirement for token
    users.setProviderToken("");
    usersRepository.save(users);

    return userDto;
  }

  public UserDTO updateUsers(String emailId, UserDTO userDto) {

    Users usersRes = usersRepository.findByEmailId(emailId);
    if (usersRes.getEmailId() == null) {
      return null;
    }
    Users users = new Users();
    users.setEmailId(userDto.getEmail());
    users.setFirstName(userDto.getFirstName());
    users.setLastName(userDto.getLastName());
    usersRepository.save(users);

    return userDto;
  }

  public void deleteUsers(String emailId) {
    userOperationsHelper.deleteUser(emailId);
    getUsersRepository().deleteById(emailId);
  }

  public void deleteAllUsers() {
    usersRepository.findAll().forEach(users -> userOperationsHelper.deleteUser(String.valueOf(users.getEmailId())));
    //verify the behaviour in case of exception and define whether to continue the process or roll-back
    getUsersRepository().deleteAll();
  }

  public List<UserDTO> getAllUsers(String email) {

    List<UserDTO> usersDto = new ArrayList<>();
    if (email == null) usersRepository.findAll().forEach(user -> usersDto.add(getUserMapper().usersToUserDTO(user)));
    else usersRepository.findListByEmailId(email).forEach(user -> usersDto.add(getUserMapper().usersToUserDTO(user)));
    if (usersDto.isEmpty()) {
      return null;
    }
    return usersDto;
  }
public UserDTO getUserByEmail(String email){
    return getUserMapper().usersToUserDTO(getUsersRepository().findByEmailId(email));
}

  public List<UserDTO> getUsersByLastName(String lastName){
    return getUsersRepository().findByLastName(lastName).stream().map(
            users -> getUserMapper().usersToUserDTO(users)
    ).collect(Collectors.toList());
  }

  public List<UserDTO> getAllUsersByName(String name){
    List<UserDTO> userDtoList = new ArrayList<>();
    if (name == null) {
      usersRepository.findAll().forEach(user -> userDtoList.add(getUserMapper().usersToUserDTO(user)));
    } else {
      usersRepository.findByFirstName(name).forEach(user -> userDtoList.add(getUserMapper().usersToUserDTO(user)));;
    }
    return userDtoList.isEmpty() ? null : userDtoList;
  }

  public List<UserDTO> getAllUsersBySkillsName(String emailId){
    List<UserDTO> userDtoList = new ArrayList<>();
    if (emailId == null) {
      usersRepository.findAll().forEach(user -> userDtoList.add(getUserMapper().usersToUserDTO(user)));
    } else {
      usersRepository.findBySkills_Name(emailId).forEach(user -> userDtoList.add(getUserMapper().usersToUserDTO(user)));;
    }
    return userDtoList.isEmpty() ? null : userDtoList;
  }

  public List<UserDTO> getAllUsersByRoleName(String emailId){
    List<UserDTO> userDtoList = new ArrayList<>();
    if (emailId == null) {
      usersRepository.findAll().forEach(user -> userDtoList.add(getUserMapper().usersToUserDTO(user)));
    } else {
      usersRepository.findByRole_Name(emailId).forEach(user -> userDtoList.add(getUserMapper().usersToUserDTO(user)));;
    }
    return userDtoList.isEmpty() ? null : userDtoList;
  }

  public List<UserDTO> getAllUsersByStudio(String emailId){
      List<UserDTO> userDTOList = new ArrayList<>();
      if(emailId == null){
          usersRepository.findAll().forEach(user -> userDTOList.add(getUserMapper().usersToUserDTO(user)));
      } else{
          usersRepository.findByStudiosSet_EmailId(emailId).forEach(users -> userDTOList.add(getUserMapper().usersToUserDTO(users)));
      }
      return userDTOList.isEmpty() ? null : userDTOList;
  }
}