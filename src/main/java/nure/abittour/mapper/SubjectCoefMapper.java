package nure.abittour.mapper;

import nure.abittour.dto.SubjectCoefDTO;
import nure.abittour.model.SubjectCoef;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectCoefMapper {
    SubjectCoefMapper INSTANCE = Mappers.getMapper(SubjectCoefMapper.class);

    SubjectCoefDTO toDto(SubjectCoef subjectCoef);

    SubjectCoef toEntity(SubjectCoefDTO subjectCoefDTO);
}