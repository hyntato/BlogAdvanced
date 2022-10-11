package com.hyntato.blog;

import com.hyntato.blog.entity.Post;
import com.hyntato.blog.entity.User;
import com.hyntato.blog.repository.PostRepository;
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
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public void createPosts() {
        List<User> users = createUsers();
        List<Post> posts = new ArrayList<>();
        for(int i=0; i<10; i++) {
            Post post = Post.builder()
                    .user(users.get(i))
                    .title("hynTitle" + i)
                    .content("hynContent" + i)
                    .build();
            posts.add(post);
        }
        postRepository.saveAll(posts);
    }

    public List<User> createUsers() {
        List<User> users = new ArrayList<>();
        for(int i=0; i<10; i++) {
            User user = User.builder()
                    .name("hyn" + i)
                    .email("hyn@gmail.com" + i)
                    .password("hynPassword" + i)
                    .build();
            users.add(user);
        }
        return userRepository.saveAll(users);
    }

    @Test
    @Transactional
    public void findPostById() {
        Optional<Post> foundPost = postRepository.findById(1L);

        assertNotNull(foundPost.get());
    }

    @Test
    @Transactional
    public void findAllPosts() {
        List<Post> posts = postRepository.findAll();

        assertEquals(10, posts.size());
    }

}
