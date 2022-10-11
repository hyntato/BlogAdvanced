package com.hyntato.blog.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_comment_id")
    Comment comment;

    String content;

    boolean isDeleted;

    @Builder
    public Comment(User user, Post post, Comment comment, String content, boolean isDeleted) {
        this.user = user;
        this.post = post;
        this.comment = comment;
        this.content = content;
        this.isDeleted = isDeleted;
    }
}

