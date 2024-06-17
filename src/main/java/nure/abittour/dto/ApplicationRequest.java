package nure.abittour.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
@ToString
@Data
public class ApplicationRequest {
    private Long id;
    private Long studentId;
    private Long competitiveOfferId;
    private BigDecimal totalScore;
    private Integer priority;
}
