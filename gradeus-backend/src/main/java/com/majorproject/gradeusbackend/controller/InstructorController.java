package com.majorproject.gradeusbackend.controller;

import com.majorproject.gradeusbackend.entity.ClassGroup;
import com.majorproject.gradeusbackend.entity.Class;
import com.majorproject.gradeusbackend.entity.Topic;
import com.majorproject.gradeusbackend.entity.User;
import com.majorproject.gradeusbackend.model.IdList;
import com.majorproject.gradeusbackend.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/class")
    public ResponseEntity<Class> addClass(@RequestBody Class newClass, @RequestParam Long teacherId) {
        teacherService.validateTeacherId(teacherId);

        Class addedClass = teacherService.addClass(newClass, teacherId);
        return new ResponseEntity<>(addedClass, HttpStatus.CREATED);
    }

    @GetMapping("/classes")
    public List<Class> getAllClasses() {
        return teacherService.findAllClasses();
    }

    @GetMapping("/class/{id}")
    public ResponseEntity<Class> getClassById(@PathVariable Long id) {
        teacherService.validateClassId(id);

        Optional<Class> optionalEntity = teacherService.findClassById(id);
        if (optionalEntity.isPresent()) {
            return new ResponseEntity<>(optionalEntity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/class/{id}")
    public ResponseEntity<String> deleteClass(@PathVariable Long id) {
        teacherService.validateClassId(id);

        Optional<Class> optionalEntity = teacherService.findClassById(id);
        if (optionalEntity.isPresent()) {
            teacherService.deleteClassById(id);
            return new ResponseEntity<>("Class with ID " + id + " deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Class with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }







    @PostMapping("/group")
    public ResponseEntity<ClassGroup> addGroup(@RequestBody ClassGroup newGroup, @RequestParam Long classId) {
        teacherService.validateClassId(classId);

        ClassGroup addedGroup = teacherService.addGroup(newGroup, classId);
        return new ResponseEntity<>(addedGroup, HttpStatus.CREATED);
    }

    @GetMapping("/groups")
    public List<ClassGroup> getAllGroups(@RequestParam Long classId) {
        teacherService.validateClassId(classId);

        return teacherService.findAllGroups(classId);
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<ClassGroup> getGroupById(@PathVariable Long id) {
        teacherService.validateGroupId(id);

        Optional<ClassGroup> optionalEntity = teacherService.findGroupById(id);
        if (optionalEntity.isPresent()) {
            return new ResponseEntity<>(optionalEntity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long id) {
        teacherService.validateGroupId(id);

        Optional<ClassGroup> optionalEntity = teacherService.findGroupById(id);
        if (optionalEntity.isPresent()) {
            teacherService.deleteGroupById(id);
            return new ResponseEntity<>("Group with ID " + id + " deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Group with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }





    @PostMapping("/topic")
    public ResponseEntity<Topic> addTopic(@RequestBody Topic newTopic, @RequestParam Long classId) {
        teacherService.validateClassId(classId);

        Topic addedTopic = teacherService.addTopic(newTopic, classId);
        return new ResponseEntity<>(addedTopic, HttpStatus.CREATED);
    }

    @GetMapping("/topics")
    public List<Topic> getAllTopics(@RequestParam Long classId) {
        teacherService.validateClassId(classId);
        return teacherService.findAllTopics(classId);
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        teacherService.validateTopicId(id);

        Optional<Topic> optionalEntity = teacherService.findTopicById(id);
        if (optionalEntity.isPresent()) {
            return new ResponseEntity<>(optionalEntity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/topic/{id}")
    public ResponseEntity<String> deleteTopic(@PathVariable Long id) {
        teacherService.validateTopicId(id);

        Optional<Topic> optionalEntity = teacherService.findTopicById(id);
        if (optionalEntity.isPresent()) {
            teacherService.deleteTopicById(id);
            return new ResponseEntity<>("Topic with ID " + id + " deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Topic with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }





    @PostMapping("/group/students")
    public ResponseEntity<Void> addStudentsToGroup(@RequestBody IdList studentIds, @RequestParam Long groupId) {
        teacherService.validateGroupId(groupId);

        teacherService.addStudentsToGroup(studentIds.getIds(), groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/group/students")
    public ResponseEntity<List<User>> getStudentsInGroup(@RequestParam Long groupId) {
        teacherService.validateGroupId(groupId);

        return new ResponseEntity<List<User>>(teacherService.getStudentsInGroup(groupId), HttpStatus.OK);
    }
}
