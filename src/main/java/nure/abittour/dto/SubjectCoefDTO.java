package nure.abittour.dto;

import lombok.Data;
import nure.abittour.model.enums.Subject;

import java.math.BigDecimal;

@Data
public class SubjectCoefDTO {
    private Long id;
    private String subject;
    private BigDecimal coefficient;
}
