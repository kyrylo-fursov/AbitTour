package nure.abittour.mapper;

import nure.abittour.dto.UniversityDTO;
import nure.abittour.model.University;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UniversityMapper {
    UniversityDTO toDto(University university);
}
