package nure.abittour.mapper;

import nure.abittour.dto.CompetitiveOfferDTO;
import nure.abittour.model.CompetitiveOffer;
import nure.abittour.model.ZnoSubjectOption;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CompetitiveOfferMapper {

    @Mappings({
            @Mapping(source = "speciality.id", target = "specialityId"),
            @Mapping(source = "university.id", target = "universityId"),
            @Mapping(source = "znoSubjectOptions", target = "znoSubjectOptionIds", qualifiedByName = "mapZnoSubjectOptionsToIds")
    })
    CompetitiveOfferDTO toDTO(CompetitiveOffer competitiveOffer);

    @Mappings({
            @Mapping(source = "specialityId", target = "speciality.id"),
            @Mapping(source = "universityId", target = "university.id"),
            @Mapping(source = "znoSubjectOptionIds", target = "znoSubjectOptions", qualifiedByName = "mapIdsToZnoSubjectOptions")
    })
    CompetitiveOffer toEntity(CompetitiveOfferDTO dto);

    @Named("mapZnoSubjectOptionsToIds")
    static Set<Long> mapZnoSubjectOptionsToIds(Set<ZnoSubjectOption> znoSubjectOptions) {
        return znoSubjectOptions.stream()
                .map(ZnoSubjectOption::getId)
                .collect(Collectors.toSet());
    }

    @Named("mapIdsToZnoSubjectOptions")
    static Set<ZnoSubjectOption> mapIdsToZnoSubjectOptions(Set<Long> ids) {
        return ids.stream()
                .map(id -> {
                    ZnoSubjectOption option = new ZnoSubjectOption();
                    option.setId(id);
                    return option;
                })
                .collect(Collectors.toSet());
    }
}
