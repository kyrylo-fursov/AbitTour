package nure.abittour.mapper;

import nure.abittour.dto.CompetitiveOfferDto;
import nure.abittour.model.CompetitiveOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ZnoSubjectOptionMapper.class})
public interface CompetitiveOfferMapper {

    @Mapping(source = "specialityId", target = "speciality.id")
    @Mapping(source = "universityId", target = "university.id")
    @Mapping(target = "znoSubjectOptions", ignore = true)
    CompetitiveOffer toEntity(CompetitiveOfferDto competitiveOfferDto);

    @Mapping(source = "speciality.id", target = "specialityId")
    @Mapping(source = "university.id", target = "universityId")
    CompetitiveOfferDto toDto(CompetitiveOffer competitiveOffer);
}