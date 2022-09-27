package com.hyntato.blog;

import com.hyntato.blog.entity.LikePost;
import com.hyntato.blog.entity.Post;
import com.hyntato.blog.entity.User;
import com.hyntato.blog.repository.LikePostRepository;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class LikePostRepositoryTest {

    @Autowired
    private LikePostRepository likePostRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    @Order(1)
    public void createLikePost() {
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

        LikePost likePost = new LikePost();
        likePost.setUser(savedUser);
        likePost.setPost(savedPost);
        LikePost savedLikePost = likePostRepository.save(likePost);
        LikePost newLikePost = likePostRepository.findById(savedLikePost.getId()).get();

//        assertEquals(9L, newLikePost.getPost().getId());
    }

    @Test
    @Order(2)
    public void findLikePostById() {
        Optional<LikePost> likePost = likePostRepository.findById(1L);
        assertNotNull(likePost.get());
    }

    @Test
    @Order(3)
    public void findAllLikePosts() {
        List<LikePost> likePosts = likePostRepository.findAll();
        assertNotNull(likePosts);
    }

}
