package nure.abittour.mapper;

import nure.abittour.dto.RegionDTO;
import nure.abittour.model.Region;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegionMapper {
    RegionMapper INSTANCE = Mappers.getMapper(RegionMapper.class);

    RegionDTO regionToDTO(Region region);

    Region dtoToRegion(RegionDTO dto);
}
