package nure.abittour.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UniversityDTO {
    private Long id;
    private String code;
    private String name;
    private RegionDTO region;
}
