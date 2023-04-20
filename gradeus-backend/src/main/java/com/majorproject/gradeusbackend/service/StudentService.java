package com.majorproject.gradeusbackend.service;

import com.majorproject.gradeusbackend.entity.Class;
import com.majorproject.gradeusbackend.entity.Score;
import com.majorproject.gradeusbackend.entity.Topic;
import com.majorproject.gradeusbackend.entity.User;
import com.majorproject.gradeusbackend.exceptions.InvalidAccessException;
import com.majorproject.gradeusbackend.model.ScoreModel;
import com.majorproject.gradeusbackend.model.ScoreResponse;
import com.majorproject.gradeusbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

    public ScoreResponse getScore(Long studentId, Long topicId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        Long scorerId = user.getId();

        Score fetchedScore = this.scoreRepository.findByStudent_IdAndScorer_IdAndTopic_TopicId(studentId, scorerId, topicId);

        if(fetchedScore != null) {
            return new ScoreResponse(fetchedScore.getScoreValue(), true);
        }
        else {
            return new ScoreResponse(0L, false);
        }
    }

    public Score addScore(ScoreModel score) {
        if(getScore(score.getStudentId(), score.getTopicId()).getPresent()) {
            throw new InvalidAccessException("Score already present");
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

//        System.out.println("user = " + user);
//        System.out.println("(this.userRepository.findById(score.getScorerId()).get() = " + this.userRepository.findById(score.getScorerId()).get());
//        System.out.println("this.groupRepository.findById(score.getGroupId()).get() = " + this.groupRepository.findById(score.getGroupId()).get());

        Score sc = Score.builder()
                .scorer(user)
                .student(this.userRepository.findById(score.getStudentId()).get())
                .scoreValue(score.getScoreValue())
                .topic(this.topicRepository.findById(score.getTopicId()).get())
                .build();

        return this.scoreRepository.save(sc);
    }
}
