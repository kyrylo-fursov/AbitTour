package nure.abittour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nure.abittour.model.enums.EducationalLevel;
import nure.abittour.model.enums.EnrolledCourse;
import nure.abittour.model.enums.EnrolmentBase;
import nure.abittour.model.enums.FormOfEducation;
import nure.abittour.model.enums.TypeOfOffer;

import java.math.BigDecimal;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CompetitiveOffer extends BaseEntity {
    @Column(nullable = false)
    String programName;

    @Column(nullable = false)
    Long offerCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    EnrolmentBase enrolmentBase;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    EducationalLevel educationalLevel;

//    @ManyToOne
//    @JoinColumn(name = "speciality_id")
//    private Speciality speciality;

//    @ManyToOne
//    @JoinColumn(name = "university_id")
//    private University university;

    @Column(nullable = false)
    String faculty;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    TypeOfOffer typeOfOffer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    FormOfEducation formOfEducation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    EnrolledCourse enrolledCourse;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    LocalDate startOfStudies;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    LocalDate endOfStudies;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    LocalDate startOfApplication;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    LocalDate endOfApplication;

    @Column(nullable = false)
    Integer licenceAmount;

    @Column(nullable = false)
    Integer maxVolumeOfTheStateOrder;

    @Column(nullable = false)
    Integer priceForYear;

    @Column(nullable = false)
    Integer totalPrice;

    @Column(nullable = false)
    BigDecimal regionalCoefficient;

    @Column(nullable = false)
    BigDecimal domainCoefficient;

//    @OneToMany(mappedBy = "competitiveOffer")
//    private Set<SubjectOption> subjectOptions;
}