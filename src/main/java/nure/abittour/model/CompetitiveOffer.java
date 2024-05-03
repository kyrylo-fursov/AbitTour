package nure.abittour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
//    @ManyToOne
//    @JoinColumn(name = "year_of_enrolment_id")
//    private YearOfEnrolment yearOfEnrolment;
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
