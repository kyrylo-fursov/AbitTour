package nure.abittour.mapper;

import nure.abittour.dto.ApplicationRequest;
import nure.abittour.dto.ApplicationResponse;
import nure.abittour.model.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "competitiveOfferId", target = "competitiveOffer.id")
    Application toEntity(ApplicationRequest applicationRequest);

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "competitiveOffer.id", target = "competitiveOfferId")
    ApplicationRequest toRequestDto(Application application);

    @Mapping(source = "student.id", target = "student.id")
    @Mapping(source = "competitiveOffer.id", target = "competitiveOffer.id")
    ApplicationResponse toResponseDto(Application application);

    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "competitiveOfferId", target = "competitiveOffer.id")
    void updateEntityFromRequest(ApplicationRequest dto, @MappingTarget Application entity);
}
