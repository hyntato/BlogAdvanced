package com.hyntato.blog;

import com.hyntato.blog.entity.Comment;
import com.hyntato.blog.entity.LikeComment;
import com.hyntato.blog.entity.Post;
import com.hyntato.blog.entity.User;
import com.hyntato.blog.repository.CommentRepository;
import com.hyntato.blog.repository.LikeCommentRepository;
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
class LikeCommentRepositoryTest {

    @Autowired
    private LikeCommentRepository likeCommentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @Order(1)
    public void createLikeComment() {
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

        Comment comment = new Comment();
        comment.setUser(savedUser);
        comment.setPost(savedPost);
        comment.setContent("hynComment");
        comment.setDeleted(false);
        Comment savedComment = commentRepository.save(comment);

        LikeComment likeComment = new LikeComment();
        likeComment.setUser(savedUser);
        likeComment.setComment(savedComment);
        LikeComment savedLikeComment = likeCommentRepository.save(likeComment);
        LikeComment newLikeComment = likeCommentRepository.findById(savedLikeComment.getId()).get();

//        assertEquals(13L, newLikeComment.getUser().getId());
    }

    @Test
    @Order(2)
    public void findLikeCommentById() {
        Optional<LikeComment> likeComment = likeCommentRepository.findById(1L);
        assertNotNull(likeComment.get());
    }

    @Test
    @Order(3)
    public void findAllLikeComments() {
        List<LikeComment> likeComments = likeCommentRepository.findAll();
        assertNotNull(likeComments);
    }

}
