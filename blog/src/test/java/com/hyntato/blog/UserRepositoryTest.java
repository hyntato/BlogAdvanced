package com.hyntato.blog;

import com.hyntato.blog.entity.User;
import com.hyntato.blog.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public void createUsers() {
        List<User> users = new ArrayList<>();
        for(int i=0; i<10; i++) {
            User user = User.builder()
                    .name("hyn" + i)
                    .email("hyn@gmail.com" + i)
                    .password("hynPassword" + i)
                    .build();
            users.add(user);
        }
        userRepository.saveAll(users);
    }

    @Test
    @Transactional
    public void findUserById() {
        Optional<User> foundUser = userRepository.findById(1L);

        assertNotNull(foundUser.get());
    }

    @Test
    @Transactional
    public void findAllUsers() {
        List<User> users = userRepository.findAll();

        assertEquals(10, users.size());
    }

}
