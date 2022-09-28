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
public class LikeComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_comment_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="comment_id")
    Comment comment;

    @Builder
    public LikeComment(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }
}
