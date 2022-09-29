package com.hyntato.blog;

import com.hyntato.blog.entity.LikePost;
import com.hyntato.blog.entity.Post;
import com.hyntato.blog.entity.User;
import com.hyntato.blog.repository.LikePostRepository;
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
class LikePostRepositoryTest {

    @Autowired
    private LikePostRepository likePostRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    public void createLikePost() {
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

        LikePost likePost = LikePost.builder()
                .user(savedUser)
                .post(savedPost)
                .build();
        LikePost savedLikePost = likePostRepository.save(likePost);

        assertEquals(likePost.getId(), savedLikePost.getId());
    }

    @Test
    @Transactional
    public void findLikePostById() {
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

        LikePost likePost = LikePost.builder()
                .user(savedUser)
                .post(savedPost)
                .build();
        LikePost savedLikePost = likePostRepository.save(likePost);

        Optional<LikePost> foundLikePost = likePostRepository.findById(savedLikePost.getId());

        assertNotNull(foundLikePost.get());
    }

    @Test
    @Transactional
    public void findAllLikePosts() {
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

        LikePost likePost = LikePost.builder()
                .user(savedUser)
                .post(savedPost)
                .build();
        likePostRepository.save(likePost);

        List<LikePost> likePosts = likePostRepository.findAll();

        assertEquals(1, likePosts.size());
    }

}
