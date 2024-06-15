package nure.abittour.dto;

import lombok.Data;
import nure.abittour.model.enums.EducationalLevel;
import nure.abittour.model.enums.EnrolledCourse;
import nure.abittour.model.enums.EnrolmentBase;
import nure.abittour.model.enums.FormOfEducation;
import nure.abittour.model.enums.TypeOfOffer;

@Data
public class CompetitiveOfferFilterDTO {
    private String programName;
    private EnrolmentBase enrolmentBase;
    private EducationalLevel educationalLevel;
    private Long specialityId;
    private Long universityId;
    private TypeOfOffer typeOfOffer;
    private FormOfEducation formOfEducation;
    private EnrolledCourse enrolledCourse;
}
