package nure.abittour.mapper;

import nure.abittour.dto.SpecialityDTO;
import nure.abittour.model.Speciality;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpecialityMapper {
    SpecialityMapper INSTANCE = Mappers.getMapper(SpecialityMapper.class);

    SpecialityDTO specialityToDTO(Speciality speciality);

    Speciality dtoToSpeciality(SpecialityDTO dto);
}
