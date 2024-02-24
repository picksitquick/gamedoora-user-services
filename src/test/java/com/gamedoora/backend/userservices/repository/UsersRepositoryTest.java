package com.gamedoora.backend.userservices.repository;

import com.gamedoora.model.dao.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest(properties = {"spring.cloud.config.enabled=false"})
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    private Users users = new Users();

    private Users testUsers = new Users();

    @BeforeEach
    void setup(){

        users = Users.builder()
                .emailId("test@gamedoora.com")
                .firstName("Test")
                .lastName("doe")
                .providerToken("")//required fields while building objects
                .build();

        testUsers = Users.builder()
                .emailId("test1@gamedoora.com")
                .firstName("Test2")
                .lastName("joe")
                .providerToken("")//required fields while building objects
                .build();
    }


    @Test
    public void sampleTest(){

        usersRepository.save(users);

        Optional<Users> dummy = usersRepository.findById("test@gamedoora.com");
        List<Users> test = usersRepository.findByFirstName("ok");

        List<Users> sample = usersRepository.findBySkills_Name("sample");

        assertNotNull(sample);
        assertNotNull(dummy);
        assertNotNull(test);
    }

    @Test
    void findByName() {
        usersRepository.save(users);
        List<Users> userValue = usersRepository.findByFirstName(users.getFirstName());
        assertNotNull(userValue);
        assertEquals(userValue.get(0).getFirstName() , "Test");
    }
    @Test
    void findByEmail() {
        usersRepository.save(users);
        Users userValue = usersRepository.findByEmailId(users.getEmail());
        assertNotNull(userValue);
        assertEquals(userValue.getEmail() , "test@gamedoora.com");
    }

    @Test
    void findByLastName() {
        usersRepository.save(users);
        List<Users> userValue = usersRepository.findByLastName(users.getLastName());
        assertNotNull(userValue);
        assertEquals(userValue.get(0).getLastName() , "doe");
    }

    @Test
    void findListOfUsers(){
        usersRepository.save(users);
        usersRepository.save(testUsers);
        List<Users> userList = usersRepository.findListByEmailId("test1@gamedoora.com");
        assertNotNull(userList);
        assertEquals(userList.size() , 1);
    }
}