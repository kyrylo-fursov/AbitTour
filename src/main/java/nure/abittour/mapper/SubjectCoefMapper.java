package nure.abittour.mapper;

import nure.abittour.dto.SubjectCoefDto;
import nure.abittour.model.SubjectCoef;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectCoefMapper {

    SubjectCoefDto toDto(SubjectCoef subjectCoef);

    SubjectCoef toEntity(SubjectCoefDto subjectCoefDto);
}
