package com.gamedoora.backend.userservices.repository;

import com.gamedoora.model.dao.Users;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;


@DataJpaTest(properties = {"spring.cloud.config.enabled=false"})
class SampleUsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    private Users user;

    @BeforeEach
    public void setup(){
        user = Users.builder()
                .id(1L)
                .firstName("Test")
                .email("test@gmail.com")
                .providerToken("")
                .build();
    }

    @Test
    void findUserbyId(){
//        assertThat(usersRepository).isNotNull();
//        Users users = new Users();
//        users.setId(1);

        usersRepository.save(user);

        Users userDB = usersRepository.findById(user.getId()).get();

//        Optional<Users> dummy = usersRepository.findById(1L);
//          assertNotNull(dummy);

         assertThat(userDB).isNotNull();
    }

}
