package nure.abittour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CompetitiveOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "offer_name")
    private String offerName;

    @Column(name = "program_name")
    private String programName;

    @Column(name = "faculty")
    private String faculty;

//    @ManyToOne
//    @JoinColumn(name = "form_of_education_id")
//    private FormOfEducation formOfEducation;
//
//    @ManyToOne
//    @JoinColumn(name = "speciality_id")
//    private Speciality speciality;
//

    @Enumerated(EnumType.STRING)
    @Column(name = "enrolled_course", nullable = false)
    private EnrolledCourse enrolledCourse;

//
//    @ManyToOne
//    @JoinColumn(name = "enrolment_base_id")
//    private EnrolmentBase enrolmentBase;
//
//    @ManyToOne
//    @JoinColumn(name = "university_id")
//    private University university;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_of_studies")
    private Date startOfStudies;

    @Temporal(TemporalType.DATE)
    private Date endOfStudies;

    private Integer licenceAmount;

//    @OneToMany(mappedBy = "competitiveOffer")
//    private Set<SubjectOption> subjectOptions;

    @Column(name = "regional_coefficient")
    private Double regionalCoefficient;

    private Integer price;
}
