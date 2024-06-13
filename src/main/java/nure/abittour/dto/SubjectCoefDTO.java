package nure.abittour.dto;

import lombok.Data;
import nure.abittour.model.enums.Subject;

@Data
public class SubjectCoefDTO {
    private Long id;
    private String subject;
    private Double coefficient;
}
