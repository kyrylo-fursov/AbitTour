    package nure.abittour.mapper;

    import nure.abittour.dto.ZnoSubjectOptionDTO;
    import nure.abittour.model.ZnoSubjectOption;
    import org.mapstruct.Mapper;
    import org.mapstruct.Mapping;
    import org.mapstruct.factory.Mappers;

    @Mapper
    public interface ZnoSubjectOptionMapper {
        ZnoSubjectOptionMapper INSTANCE = Mappers.getMapper(ZnoSubjectOptionMapper.class);

        ZnoSubjectOptionDTO toDto(ZnoSubjectOption znoSubjectOption);

        ZnoSubjectOption toEntity(ZnoSubjectOptionDTO znoSubjectOptionDto);
    }
