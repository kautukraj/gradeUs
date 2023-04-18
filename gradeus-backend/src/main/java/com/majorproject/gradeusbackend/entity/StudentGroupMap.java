package com.majorproject.gradeusbackend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.majorproject.gradeusbackend.utils.StudentGroupMapId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "student_group_map")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StudentGroupMap {

    @EmbeddedId
    private StudentGroupMapId studentGroupMapId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private User student;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "groupId")
    @JsonIdentityReference(alwaysAsId = true)
    private ClassGroup group;
}

