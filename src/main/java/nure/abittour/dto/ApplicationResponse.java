package nure.abittour.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
@ToString
@Data
public class ApplicationResponse {
    private Long id;
    private StudentDto student;
    private CompetitiveOfferResponse competitiveOffer;
    private BigDecimal totalScore;
    private Integer priority;
}
