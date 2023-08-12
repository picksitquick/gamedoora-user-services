package com.gamedoora.backend.userservices.repository;

import com.gamedoora.model.dao.Users;
import com.gamedoora.model.dao.Roles;
import com.gamedoora.model.dao.Skills;
import com.gamedoora.model.dao.UserRole;
import com.gamedoora.model.dao.UserSkills;
import com.gamedoora.model.dao.Users;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest(properties = {"spring.cloud.config.enabled=false"})
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    private Users users = new Users();

    @BeforeEach
    void setup(){

        users = Users.builder()
                .id(1L)
                .firstName("Test")
                .email("test@gmail.com")
                .providerToken("")//required fields while building objects
                .build();
    }


    @Test
    public void sampleTest(){

        usersRepository.save(users);

        Optional<Users> dummy = usersRepository.findById(1L);
        List<Users> userValue = usersRepository.findByFirstName(users.getFirstName());
        List<Users> sample = usersRepository.findBySkills_Name("sample");

        assertNotNull(sample);
        assertNotNull(dummy);
        assertNotNull(userValue);
    }

    @Test
    void findByName() {
        usersRepository.save(users);
        List<Users> userValue = usersRepository.findByFirstName(users.getFirstName());
        assertNotNull(userValue);
        assertEquals(userValue.get(0).getFirstName() , "Test");
    }

}