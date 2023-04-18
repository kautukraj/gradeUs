package com.majorproject.gradeusbackend.controller;

import com.majorproject.gradeusbackend.entity.Class;
import com.majorproject.gradeusbackend.entity.ClassGroup;
import com.majorproject.gradeusbackend.entity.Score;
import com.majorproject.gradeusbackend.entity.Topic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    // The classes in which the student is enrolled in
//    @GetMapping("/classes")
//    public List<Class> getAllClasses() {
//        return teacherService.findAllClasses();
//    }
//
//    // The topics in a class
//    @GetMapping("/topics")
//    public List<Topic> getAllTopics(@RequestParam Long classId) {
//        teacherService.validateClassId(classId);
//        return teacherService.findAllTopics(classId);
//    }
//
//    // The groups in which the student is present
//    @GetMapping("/groups")
//    public List<ClassGroup> getAllGroups(@RequestParam Long classId) {
//        teacherService.validateClassId(classId);
//        return teacherService.findAllGroups(classId);
//    }
//
//    @PostMapping("/score")
//    public ResponseEntity<Score> addScore(@RequestBody Score score) {
//
////        teacherService.validateClassId(classId);
////
////        Topic addedTopic = teacherService.addTopic(newTopic, classId);
////        return new ResponseEntity<>(addedTopic, HttpStatus.CREATED);
//    }
}
