package com.hyntato.blog.repository;

import com.hyntato.blog.entity.HitSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HitSessionRepository extends JpaRepository<HitSession, Long> {
}
