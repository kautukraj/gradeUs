package com.majorproject.gradeusbackend.repository;

import com.majorproject.gradeusbackend.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {
    List<Class> findByTeacher_Id(Long teacherId);
}