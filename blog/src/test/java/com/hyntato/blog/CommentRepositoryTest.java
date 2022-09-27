package com.hyntato.blog;

import com.hyntato.blog.entity.Comment;
import com.hyntato.blog.entity.Post;
import com.hyntato.blog.entity.User;
import com.hyntato.blog.repository.CommentRepository;
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
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    @Order(1)
    public void createComment() {
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
        Comment newComment = commentRepository.findById(savedComment.getId()).get();

        assertEquals("hynComment", newComment.getContent());
    }

    @Test
    @Order(2)
    public void findCommentById() {
        Optional<Comment> comment = commentRepository.findById(1L);
        assertNotNull(comment.get());
    }

    @Test
    @Order(3)
    public void findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        assertNotNull(comments);
    }

    @Test
    @Order(4)
    public void updateComment() {
        Optional<Comment> comment = commentRepository.findById(1L);
        comment.ifPresent( currentComment -> {
            currentComment.setContent("updateComment");
            commentRepository.save(currentComment);
        });
        assertEquals("updateComment", comment.get().getContent());
    }

}
