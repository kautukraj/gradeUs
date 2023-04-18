package com.majorproject.gradeusbackend.repository;

import com.majorproject.gradeusbackend.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, Long> {
}