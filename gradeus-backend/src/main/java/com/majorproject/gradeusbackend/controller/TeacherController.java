package com.majorproject.gradeusbackend.controller;

import com.majorproject.gradeusbackend.entity.ClassGroup;
import com.majorproject.gradeusbackend.entity.Class;
import com.majorproject.gradeusbackend.entity.Topic;
import com.majorproject.gradeusbackend.entity.User;
import com.majorproject.gradeusbackend.model.IdList;
import com.majorproject.gradeusbackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/class")
    public ResponseEntity<Class> addClass(@RequestBody Class newClass, @RequestParam Long teacherId) {
        // TODO - Add validation using jwt

        Class addedClass = teacherService.addClass(newClass, teacherId);
        return new ResponseEntity<>(addedClass, HttpStatus.CREATED);
    }

    @PostMapping("/group")
    public ResponseEntity<ClassGroup> addGroup(@RequestBody ClassGroup newGroup, @RequestParam Long classId) {
        // TODO - Add validation using jwt

        ClassGroup addedGroup = teacherService.addGroup(newGroup, classId);
        return new ResponseEntity<>(addedGroup, HttpStatus.CREATED);
//        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PostMapping("/topic")
    public ResponseEntity<Topic> addTopic(@RequestBody Topic newTopic, @RequestParam Long classId) {
        // TODO - Add validation using jwt

        Topic addedTopic = teacherService.addTopic(newTopic, classId);
        return new ResponseEntity<>(addedTopic, HttpStatus.CREATED);
    }

    @PostMapping("/group/students")
    public ResponseEntity<Void> addStudentsToGroup(@RequestBody IdList studentIds, @RequestParam Long groupId) {
        // TODO - Add validation using jwt

        teacherService.addStudentsToGroup(studentIds.getIds(), groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/group/students")
    public ResponseEntity<List<User>> getStudentsInGroup(@RequestParam Long groupId) {
        // TODO - Add validation using jwt

        return new ResponseEntity<List<User>>(teacherService.getStudentsInGroup(groupId), HttpStatus.OK);
    }
}
