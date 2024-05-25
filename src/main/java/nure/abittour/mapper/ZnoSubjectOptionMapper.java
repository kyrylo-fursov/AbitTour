    package nure.abittour.mapper;

    import nure.abittour.dto.ZnoSubjectOptionDTO;
    import nure.abittour.model.ZnoSubjectOption;
    import org.mapstruct.Mapper;

    @Mapper(componentModel = "spring")
    public interface ZnoSubjectOptionMapper {
        ZnoSubjectOption toEntity(ZnoSubjectOptionDTO dto);
        ZnoSubjectOptionDTO toDto(ZnoSubjectOption entity);
    }