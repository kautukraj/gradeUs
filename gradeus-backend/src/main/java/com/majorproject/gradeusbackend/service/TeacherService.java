package com.majorproject.gradeusbackend.service;

import com.majorproject.gradeusbackend.entity.*;
import com.majorproject.gradeusbackend.entity.Class;
import com.majorproject.gradeusbackend.exceptions.ResourceNotFoundException;
import com.majorproject.gradeusbackend.repository.*;
import com.majorproject.gradeusbackend.utils.StudentGroupMapId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

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

    public Class addClass(Class newClass, Long teacherId) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        newClass.setTeacher(teacher);
        return classRepository.save(newClass);
    }

    public ClassGroup addGroup(ClassGroup newGroup, Long classId) {
        Class classObj = classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"));
        newGroup.setClassObj(classObj);
        System.out.println("newGroup.getClassObj().getTeacher() = " + newGroup.getClassObj());
        groupRepository.save(newGroup);
        return newGroup;
    }

    public Topic addTopic(Topic newTopic, Long classId) {
        Class classObj = classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"));
        newTopic.setClassObj(classObj);
        return topicRepository.save(newTopic);
    }

//    public void addStudentsToGroup(List<Long> studentIds, Long groupId) {
//        ClassGroup group = groupRepository.findById(groupId)
//                .orElseThrow(() -> new ResourceNotFoundException("ClassGroup not found"));
//        List<User> students = userRepository.findAllById(studentIds);
//        group.getStudents().addAll(students);
//        groupRepository.save(group);
//    }

    public void addStudentsToGroup(List<Long> studentIds, Long groupId) {

        System.out.println("studentIds = " + studentIds);
        for(Long id : studentIds) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            System.out.println("user = " + user);

            ClassGroup group = groupRepository.findById(groupId)
                    .orElseThrow(() -> new ResourceNotFoundException("Group not found"));
            System.out.println("group = " + group);

            StudentGroupMap studentGroupMap = new StudentGroupMap(new StudentGroupMapId(id, groupId), user, group);
            studentGroupMapRepository.save(studentGroupMap);
        }
    }

    public List<User> getStudentsInGroup(Long groupId) {
        return userRepository.findStudentsInGroup(groupId);
    }
}
