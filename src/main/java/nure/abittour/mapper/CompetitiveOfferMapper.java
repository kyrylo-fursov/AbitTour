package nure.abittour.mapper;

import nure.abittour.dto.CompetitiveOfferRequest;
import nure.abittour.dto.CompetitiveOfferResponse;
import nure.abittour.model.CompetitiveOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ZnoSubjectOptionMapper.class})
public interface CompetitiveOfferMapper {

    @Mapping(source = "specialityId", target = "speciality.id")
    @Mapping(source = "universityId", target = "university.id")
    CompetitiveOffer toEntity(CompetitiveOfferRequest competitiveOfferRequest);

    @Mapping(source = "speciality.id", target = "specialityId")
    @Mapping(source = "university.id", target = "universityId")
    CompetitiveOfferRequest toRequestDto(CompetitiveOffer competitiveOffer);

    @Mapping(source = "speciality.id", target = "speciality.id")
    @Mapping(source = "university.id", target = "university.id")
    CompetitiveOfferResponse toResponseDto(CompetitiveOffer competitiveOffer);

    @Mapping(source = "specialityId", target = "speciality.id")
    @Mapping(source = "universityId", target = "university.id")
    void updateEntityFromRequest(CompetitiveOfferRequest dto, @MappingTarget CompetitiveOffer entity);
}