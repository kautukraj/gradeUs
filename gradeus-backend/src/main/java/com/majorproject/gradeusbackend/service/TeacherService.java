package com.majorproject.gradeusbackend.service;

import com.majorproject.gradeusbackend.entity.*;
import com.majorproject.gradeusbackend.entity.Class;
import com.majorproject.gradeusbackend.exceptions.InvalidAccessException;
import com.majorproject.gradeusbackend.exceptions.ResourceNotFoundException;
import com.majorproject.gradeusbackend.repository.*;
import com.majorproject.gradeusbackend.utils.StudentGroupMapId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void validateTeacherId(Long teacherId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        if(user.getId() != teacherId) {
            throw new InvalidAccessException("The given teacher id cannot be used to proceed.");
        }
    }

    public void validateClassId(Long classId) {
        Long teacherId = classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"))
                .getTeacher()
                .getId();

        validateTeacherId(teacherId);
    }

    public void validateGroupId(Long groupId) {
        Long classId = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found"))
                .getClassObj()
                .getClassId();

        validateClassId(classId);
    }

    public void validateTopicId(Long topicId) {
        Long classId = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found"))
                .getClassObj()
                .getClassId();

        validateClassId(classId);
    }

    public List<Class> findAllClasses() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        return classRepository.findByTeacher_Id(user.getId());
    }

    public Optional<Class> findClassById(Long id) {
        return classRepository.findById(id);
    }

    public void deleteClassById(Long id) {
        classRepository.deleteById(id);
    }



    public List<ClassGroup> findAllGroups(Long classId) {
        return groupRepository.findByClassObj_ClassId(classId);
    }

    public Optional<ClassGroup> findGroupById(Long id) {
        return groupRepository.findById(id);
    }

    public void deleteGroupById(Long id) {
        groupRepository.deleteById(id);
    }



    public List<Topic> findAllTopics(Long classId) {
        return topicRepository.findByClassObj_ClassId(classId);
    }

    public Optional<Topic> findTopicById(Long id) {
        return topicRepository.findById(id);
    }

    public void deleteTopicById(Long id) {
        topicRepository.deleteById(id);
    }


}
