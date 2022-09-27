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
        User user = new User();
        user.setName("hyn");
        user.setEmail("hyn@gmail.com");
        user.setPassword("hynPassword");
        User savedUser = userRepository.save(user);

        Post post = new Post();
        post.setUser(savedUser);
        post.setTitle("hynTitle");
        post.setContent("hynContent");
        Post savedPost = postRepository.save(post);

        Hit hit = new Hit();
        hit.setPost(savedPost);
        hit.setCount(1);
        Hit savedHit = hitRepository.save(hit);
        Hit newHit = hitRepository.findById(savedHit.getId()).get();

        assertEquals("hynTitle", newHit.getPost().getTitle());
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
            currentHit.setCount(currentHit.getCount() + 1);
            hitRepository.save(currentHit);
        });
        assertEquals(2, hit.get().getCount());
    }

}
