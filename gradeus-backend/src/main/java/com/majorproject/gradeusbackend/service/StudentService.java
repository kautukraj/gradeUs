package com.majorproject.gradeusbackend.service;

import com.majorproject.gradeusbackend.entity.Class;
import com.majorproject.gradeusbackend.entity.Topic;
import com.majorproject.gradeusbackend.entity.User;
import com.majorproject.gradeusbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ClassGroupRepository groupRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentGroupMapRepository studentGroupMapRepository;

    @Autowired
    private ScoreRepository scoreRepository;


    public List<Class> getAllClasses() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        return classRepository.findAllClassesForStudent(user.getId());
    }

    public List<Topic> getTopicsInClass(Long classId) {
        return topicRepository.findByClassObj_ClassId(classId);
    }

    public List<User> getGroupMembersInClass(Long classId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        return userRepository.getGroupMembersInClass(user.getId(), classId);
    }

    public Optional<Topic> findTopicById(Long id) {
        return topicRepository.findById(id);
    }
}
