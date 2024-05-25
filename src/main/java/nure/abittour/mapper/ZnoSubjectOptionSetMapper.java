package nure.abittour.mapper;

import nure.abittour.dto.ZnoSubjectOptionDTO;
import nure.abittour.model.ZnoSubjectOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(uses = {ZnoSubjectOptionMapper.class})
public interface ZnoSubjectOptionSetMapper {
    ZnoSubjectOptionSetMapper INSTANCE = Mappers.getMapper(ZnoSubjectOptionSetMapper.class);

    Set<ZnoSubjectOptionDTO> toDto(Set<ZnoSubjectOption> znoSubjectOption);

    Set<ZnoSubjectOption> toEntity(Set<ZnoSubjectOptionDTO> znoSubjectOptionDto);
}
