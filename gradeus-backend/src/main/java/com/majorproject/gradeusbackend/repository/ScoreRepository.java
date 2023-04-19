package com.majorproject.gradeusbackend.repository;

import com.majorproject.gradeusbackend.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}