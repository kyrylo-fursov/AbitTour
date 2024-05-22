package nure.abittour.mapper;

import nure.abittour.dto.CompetitiveOfferRequest;
import nure.abittour.model.CompetitiveOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ZnoSubjectOptionMapper.class})
public interface CompetitiveOfferMapper {
    CompetitiveOfferMapper INSTANCE = Mappers.getMapper(CompetitiveOfferMapper.class);


    CompetitiveOfferRequest competitiveOfferToDTO(CompetitiveOffer competitiveOffer);


    CompetitiveOffer dtoToCompetitiveOffer(CompetitiveOfferRequest dto);

    void updateCompetitiveOfferFromDTO(CompetitiveOfferRequest dto, @MappingTarget CompetitiveOffer competitiveOffer);
}
