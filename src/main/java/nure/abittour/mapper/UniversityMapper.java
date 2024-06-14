package nure.abittour.mapper;

import nure.abittour.dto.UniversityDTO;
import nure.abittour.model.University;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = RegionMapper.class)
public interface UniversityMapper {
    UniversityMapper INSTANCE = Mappers.getMapper(UniversityMapper.class);

    UniversityDTO universityToDTO(University university);

    University dtoToUniversity(UniversityDTO dto);
}
