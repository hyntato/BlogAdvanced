package com.hyntato.blog;

import com.hyntato.blog.entity.User;
import com.hyntato.blog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void createUser() {
        User user = User.builder()
                .name("hyn")
                .email("hyn@gmail.com")
                .password("hynPassword")
                .build();
        User savedUser = userRepository.save(user);

        assertEquals(user.getId(), savedUser.getId());
    }

    @Test
    @Transactional
    public void findUserById() {
        User user = User.builder()
                .name("hyn")
                .email("hyn@gmail.com")
                .password("hynPassword")
                .build();
        User savedUser = userRepository.save(user);

        Optional<User> foundUser = userRepository.findById(savedUser.getId());

        assertNotNull(foundUser.get());
    }

    @Test
    @Transactional
    public void findAllUsers() {
        User user = User.builder()
                .name("hyn")
                .email("hyn@gmail.com")
                .password("hynPassword")
                .build();
        userRepository.save(user);

        List<User> users = userRepository.findAll();

        assertEquals(1, users.size());
    }

    @Test
    @Transactional
    public void updateUser() {
        User user = User.builder()
                .name("hyn")
                .email("hyn@gmail.com")
                .password("hynPassword")
                .build();
        User savedUser = userRepository.save(user);

        User updateUser = User.builder()
                .id(savedUser.getId())
                .name("updateName")
                .email("updateEmail")
                .password("updatePassword")
                .build();
        User updatedUser = userRepository.save(updateUser);

        assertEquals(updateUser.getName(), updatedUser.getName());
    }

}
