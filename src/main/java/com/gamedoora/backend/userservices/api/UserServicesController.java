package com.gamedoora.backend.userservices.api;

import com.gamedoora.backend.userservices.assembler.UserServicesAssembler;
import com.gamedoora.model.dto.UserDTO;
import com.gamedoora.backend.userservices.exceptions.NotFoundException;

import java.text.MessageFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

@RestController
@RequestMapping("/users")
public class UserServicesController extends BaseController {


  private UserServicesAssembler userServicesAssembler;

  @PostMapping(
      value = "/",
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<UserDTO> createUsers(@RequestBody UserDTO usersDto) {
    return createResponse(getUserServicesAssembler().createUsers(usersDto), HttpStatus.CREATED);
  }

  @PutMapping(
      value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<UserDTO> updateUsers(
          @PathVariable("id") long id, @RequestBody UserDTO usersDto) {
    UserDTO userDTO = getUserServicesAssembler().updateUsers(id, usersDto);
    if (null == userDTO) {
      throw new NotFoundException(MessageFormat.format("User by id {0} not found", id));
    }
    return createResponse(userDTO, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteUsers(@PathVariable("id") long id) {
    getUserServicesAssembler().deleteUsers(id);
    return createResponse(null, HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/")
  public ResponseEntity<HttpStatus> deleteAllUsers() {
    getUserServicesAssembler().deleteAllUsers();
    return createResponse(null, HttpStatus.NO_CONTENT);
  }

  @GetMapping(
      value = "/",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(required = false) String name) {
    return createResponse(getUserServicesAssembler().getAllUsers(name), HttpStatus.OK);
  }

  @GetMapping(
          value = "/{name}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<UserDTO>> getAllUsersByName(@PathVariable String name) {
    return createResponse(getUserServicesAssembler().getAllUsersByName(name), HttpStatus.OK);
  }
  @GetMapping(
          value = "/name/{lastName}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<UserDTO>> getAllUsersByLastName(@PathVariable String lastName) {
    return createResponse(getUserServicesAssembler().getUsersByLastName(lastName), HttpStatus.OK);
  }
  @GetMapping(
          value = "/skills/{name}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<UserDTO>> getAllUsersBySkillsName(@PathVariable String name) {
    return createResponse(getUserServicesAssembler().getAllUsersBySkillsName(name), HttpStatus.OK);
  }

  @GetMapping(
          value = "/roles/{name}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<UserDTO>> getAllUsersByRoles(@PathVariable String name) {
    return createResponse(getUserServicesAssembler().getAllUsersByRoleName(name), HttpStatus.OK);
  }//confirm mapping
  @GetMapping(
          value = "/email",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<UserDTO> getUserByEmail(@RequestParam(required = true) String email) {
    return createResponse(getUserServicesAssembler().getUserByEmail(email), HttpStatus.OK);
  }

  public UserServicesAssembler getUserServicesAssembler() {
    return userServicesAssembler;
  }
  @Autowired
  public void setUserServicesAssembler(UserServicesAssembler userServicesAssembler) {
    this.userServicesAssembler = userServicesAssembler;
  }
}
