package nure.abittour.dto;

import lombok.Data;
import lombok.ToString;
import nure.abittour.model.enums.EducationalLevel;
import nure.abittour.model.enums.EnrolledCourse;
import nure.abittour.model.enums.EnrolmentBase;
import nure.abittour.model.enums.FormOfEducation;
import nure.abittour.model.enums.TypeOfOffer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@ToString
@Data
public class CompetitiveOfferResponse {
    private Long id;
    private String programName;
    private Long offerCode;
    private EnrolmentBase enrolmentBase;
    private EducationalLevel educationalLevel;
    private Long specialityId;
    private UniversityDTO university;
    private String faculty;
    private TypeOfOffer typeOfOffer;
    private FormOfEducation formOfEducation;
    private EnrolledCourse enrolledCourse;
    private LocalDate startOfStudies;
    private LocalDate endOfStudies;
    private LocalDate startOfApplication;
    private LocalDate endOfApplication;
    private Integer licenceAmount;
    private Integer maxVolumeOfTheStateOrder;
    private Integer priceForYear;
    private Integer totalPrice;
    private BigDecimal regionalCoefficient;
    private BigDecimal domainCoefficient;
    private Set<ZnoSubjectOptionDTO> znoSubjectOptions;
}