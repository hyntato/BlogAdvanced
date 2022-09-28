package com.hyntato.blog;

import com.hyntato.blog.entity.Post;
import com.hyntato.blog.entity.User;
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
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    public void createPost() {
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
        Post newPost = postRepository.findById(savedPost.getId()).get();

        assertEquals("hynTitle", newPost.getTitle());
    }

    @Test
    @Order(2)
    public void findPostById() {
        Optional<Post> post = postRepository.findById(1L);
        assertNotNull(post.get());
    }

    @Test
    @Order(3)
    public void findAllPosts() {
        List<Post> posts = postRepository.findAll();
        assertNotNull(posts);
    }

    @Test
    @Order(4)
    public void updatePost() {
        Optional<Post> post = postRepository.findById(1L);
        post.ifPresent( currentPost -> {
            Post updatePost = Post.builder()
                    .id(currentPost.getId())
                    .title("updateTitle")
                    .content("updateContent")
                    .build();
            postRepository.save(updatePost);
        });
        Post updatedPost = postRepository.findById(1L).get();
        assertEquals("updateTitle", updatedPost.getTitle());
    }

}
