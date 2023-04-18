package com.majorproject.gradeusbackend.repository;

import com.majorproject.gradeusbackend.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}