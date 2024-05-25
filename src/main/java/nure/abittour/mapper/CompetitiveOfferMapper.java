package nure.abittour.mapper;

import nure.abittour.dto.CompetitiveOfferDto;
import nure.abittour.model.CompetitiveOffer;
import nure.abittour.model.Speciality;
import nure.abittour.model.University;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ZnoSubjectOptionSetMapper.class})
public interface CompetitiveOfferMapper {
    CompetitiveOfferMapper INSTANCE = Mappers.getMapper(CompetitiveOfferMapper.class);

    @Mapping(source = "specialityId", target = "speciality.id")
    @Mapping(source = "universityId", target = "university.id")
    CompetitiveOffer toEntity(CompetitiveOfferDto competitiveOfferDto);

    @Mapping(source = "speciality.id", target = "specialityId")
    @Mapping(source = "university.id", target = "universityId")
    CompetitiveOfferDto toDto(CompetitiveOffer competitiveOffer);
}
