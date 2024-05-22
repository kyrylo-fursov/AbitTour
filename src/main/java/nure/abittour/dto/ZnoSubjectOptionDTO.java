package nure.abittour.dto;

import lombok.Getter;
import lombok.Setter;
import nure.abittour.model.enums.Subject;

@Getter
@Setter
public class ZnoSubjectOptionDTO {
    private Double coefficient;
    private Subject subject;
}
