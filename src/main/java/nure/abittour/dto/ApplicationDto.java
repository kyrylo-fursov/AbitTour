package nure.abittour.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApplicationDto {
    private Long id;
    private Long studentId;
    private Long competitiveOfferId;
    private BigDecimal totalScore;
    private Integer priority;
}
