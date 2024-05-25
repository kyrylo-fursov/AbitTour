package nure.abittour.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nure.abittour.model.enums.Subject;
@ToString
@Getter
@Setter
@Data
public class ZnoSubjectOptionDTO {
    private Double coefficient;
    private Subject subject;
}
