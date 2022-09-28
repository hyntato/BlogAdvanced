package com.hyntato.blog;

import com.hyntato.blog.entity.Hit;
import com.hyntato.blog.entity.Post;
import com.hyntato.blog.entity.User;
import com.hyntato.blog.repository.HitRepository;
import com.hyntato.blog.repository.PostRepository;
import com.hyntato.blog.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class HitRepositoryTest {

    @Autowired
    private HitRepository hitRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    @Order(1)
    public void createHit() {
        User user = User.builder()
                .name("hyn")
                .email("hyn@gmail.com")
                .password("hynPassword")
                .build();
        User savedUser = userRepository.save(user);

        Post post = Post.builder()
                .user(savedUser)
                .title("hynTitle")
                .content("hynContent")
                .build();
        Post savedPost = postRepository.save(post);

        Hit hit = Hit.builder()
                .post(savedPost)
                .count(1)
                .build();
        Hit savedHit = hitRepository.save(hit);
        Hit newHit = hitRepository.findById(savedHit.getId()).get();

        assertEquals(10L, newHit.getPost().getId());
    }

    @Test
    @Order(2)
    public void findHitById() {
        Optional<Hit> hit = hitRepository.findById(1L);
        assertNotNull(hit.get());
    }

    @Test
    @Order(3)
    public void findAllHits() {
        List<Hit> hits = hitRepository.findAll();
        assertNotNull(hits);
    }

    @Test
    @Order(4)
    public void updateHit() {
        Optional<Hit> hit = hitRepository.findById(1L);
        hit.ifPresent( currentHit -> {
            Hit updateHit = Hit.builder()
                    .id(currentHit.getId())
                    .post(currentHit.getPost())
                    .count(currentHit.getCount()+1)
                    .build();
            hitRepository.save(updateHit);
        });
        Hit updatedHit = hitRepository.findById(1L).get();
        assertEquals(2, updatedHit.getCount());
    }

}
