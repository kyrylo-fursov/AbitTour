package nure.abittour.dto;

import lombok.Data;
import nure.abittour.model.enums.Subject;

@Data
public class SubjectCoefDto {
    private Long id;
    private Long specialityId;
    private Subject subject;
    private Double coefficient;
}
