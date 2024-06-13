package nure.abittour.dto;

import lombok.Data;
import lombok.ToString;
import nure.abittour.model.ZnoSubjectOption;
import nure.abittour.model.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@ToString
@Data
public class CompetitiveOfferDto {
    private Long id;
    private String programName;
    private Long offerCode;
    private EnrolmentBase enrolmentBase;
    private EducationalLevel educationalLevel;
    private Long specialityId;
    private Long universityId;
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
