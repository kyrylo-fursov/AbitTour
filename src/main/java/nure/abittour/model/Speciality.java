package nure.abittour.model;

import jakarta.persistence.*;
import nure.abittour.model.enums.EducationalLevel;
import nure.abittour.model.enums.EnrolmentBase;
import nure.abittour.model.enums.FormOfEducation;

import java.util.List;

@Entity
public class Speciality extends BaseEntity {
    @Column(nullable = false)
    String competitionIdentifier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    EducationalLevel degreeOfHigherEducation;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @Column(nullable = false)
    List<EnrolmentBase> enrolmentBase;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    FormOfEducation formOfEducation;

    @Column(nullable = false)
    String code;

    @Column(nullable = false)
    String name;
}
