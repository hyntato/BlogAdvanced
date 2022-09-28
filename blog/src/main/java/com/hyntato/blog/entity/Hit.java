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
public class Hit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hit_id")
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    Post post;

    int count;
    @Builder
    public Hit(Post post, int count) {
        this.post = post;
        this.count = count;
    }
}
