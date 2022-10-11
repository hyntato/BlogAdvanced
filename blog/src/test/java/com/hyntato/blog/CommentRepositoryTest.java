package com.hyntato.blog;

import com.hyntato.blog.entity.Comment;
import com.hyntato.blog.entity.Post;
import com.hyntato.blog.entity.User;
import com.hyntato.blog.repository.CommentRepository;
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
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @BeforeAll
    public void createComments() {
        List<User> users = createUsers();
        List<Post> posts = createPosts(users);
        List<Comment> comments = new ArrayList<>();

        for(int i=0; i<10; i++) {
            Comment comment = Comment.builder()
                    .user(users.get(i))
                    .post(posts.get(i))
                    .content("hynComment" + i)
                    .isDeleted(false)
                    .build();
            comments.add(comment);
        }
        commentRepository.saveAll(comments);
    }

    public List<Post> createPosts(List<User> users) {
        List<Post> posts = new ArrayList<>();
        for(int i=0; i<10; i++) {
            Post post = Post.builder()
                    .user(users.get(i))
                    .title("hynTitle" + i)
                    .content("hynContent" + i)
                    .build();
            posts.add(post);
        }
        return postRepository.saveAll(posts);
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
    public void findCommentById() {
        Optional<Comment> foundComment = commentRepository.findById(1L);

        assertNotNull(foundComment.get());
    }

    @Test
    @Transactional
    public void findAllComments() {
        List<Comment> comments = commentRepository.findAll();

        assertEquals(10, comments.size());
    }

}
