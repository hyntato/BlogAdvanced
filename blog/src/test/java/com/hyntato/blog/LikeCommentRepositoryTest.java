package com.hyntato.blog;

import com.hyntato.blog.entity.Comment;
import com.hyntato.blog.entity.LikeComment;
import com.hyntato.blog.entity.Post;
import com.hyntato.blog.entity.User;
import com.hyntato.blog.repository.CommentRepository;
import com.hyntato.blog.repository.LikeCommentRepository;
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
    @Transactional
    public void createLikeComment() {
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

        Comment comment = Comment.builder()
                .user(savedUser)
                .post(savedPost)
                .content("hynComment")
                .isDeleted(false)
                .build();
        Comment savedComment = commentRepository.save(comment);

        LikeComment likeComment = LikeComment.builder()
                .user(savedUser)
                .comment(savedComment)
                .build();
        LikeComment savedLikeComment = likeCommentRepository.save(likeComment);

        assertEquals(likeComment.getId(), savedLikeComment.getId());
    }

    @Test
    @Transactional
    public void findLikeCommentById() {
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

        Comment comment = Comment.builder()
                .user(savedUser)
                .post(savedPost)
                .content("hynComment")
                .isDeleted(false)
                .build();
        Comment savedComment = commentRepository.save(comment);

        LikeComment likeComment = LikeComment.builder()
                .user(savedUser)
                .comment(savedComment)
                .build();
        LikeComment savedLikeComment = likeCommentRepository.save(likeComment);

        Optional<LikeComment> foundLikeComment = likeCommentRepository.findById(savedLikeComment.getId());

        assertNotNull(foundLikeComment.get());
    }

    @Test
    @Transactional
    public void findAllLikeComments() {
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

        Comment comment = Comment.builder()
                .user(savedUser)
                .post(savedPost)
                .content("hynComment")
                .isDeleted(false)
                .build();
        Comment savedComment = commentRepository.save(comment);

        LikeComment likeComment = LikeComment.builder()
                .user(savedUser)
                .comment(savedComment)
                .build();
        likeCommentRepository.save(likeComment);

        List<LikeComment> likeComments = likeCommentRepository.findAll();

        assertEquals(1, likeComments.size());
    }

}
