package com.hyntato.blog.repository;

import com.hyntato.blog.entity.Hit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HitRepository extends JpaRepository<Hit, Long> {
}
