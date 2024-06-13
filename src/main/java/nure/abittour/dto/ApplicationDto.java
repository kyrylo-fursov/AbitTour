package nure.abittour.dto;

import lombok.Data;

@Data
public class ApplicationDto {
    private Long id;
    private Long studentId;
    private Long competitiveOfferId;
    private Double totalScore;
    private Integer priority;
}
