package com.majorproject.gradeusbackend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "score")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scoreId;

    @Column(nullable = false)
    private Integer score_value;

    private String feedback;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private User student;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "scorer_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private User scorer;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "classGroupId")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "group_id", nullable = false)
    private ClassGroup group;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "topic_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "topicId")
    @JsonIdentityReference(alwaysAsId = true)
    private Topic topic;
}
