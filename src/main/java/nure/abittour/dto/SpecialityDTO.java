package nure.abittour.dto;

import lombok.Data;

import java.util.List;

@Data
public class SpecialityDTO {
    private Long id;
    private String code;
    private String name;
    private String specialization;
    private List<SubjectCoefDTO> subjectCoefs;
}