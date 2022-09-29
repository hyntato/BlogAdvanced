package com.hyntato.blog;

import com.hyntato.blog.entity.Hit;
import com.hyntato.blog.entity.Post;
import com.hyntato.blog.entity.User;
import com.hyntato.blog.repository.HitRepository;
import com.hyntato.blog.repository.PostRepository;
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
class HitRepositoryTest {

    @Autowired
    private HitRepository hitRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
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

        assertEquals(savedPost.getId(), savedHit.getPost().getId());
    }

    @Test
    @Transactional
    public void findHitById() {
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

        Optional<Hit> foundHit = hitRepository.findById(savedHit.getId());

        assertNotNull(foundHit.get());
    }

    @Test
    @Transactional
    public void findAllHits() {
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
        hitRepository.save(hit);

        List<Hit> hits = hitRepository.findAll();

        assertEquals(1, hits.size());
    }

    @Test
    @Transactional
    public void updateHit() {
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

        Hit updateHit = Hit.builder()
                .id(savedHit.getId())
                .post(savedHit.getPost())
                .count(savedHit.getCount()+1)
                .build();
        Hit updatedHit = hitRepository.save(updateHit);

        assertEquals(updateHit.getCount(), updatedHit.getCount());
    }

}
