package nure.abittour.mapper;

import nure.abittour.dto.ApplicationDto;
import nure.abittour.model.Application;
import nure.abittour.model.Student;
import nure.abittour.model.CompetitiveOffer;
import nure.abittour.repository.StudentRepository;
import nure.abittour.repository.CompetitiveOfferRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {StudentMapper.class, CompetitiveOfferMapper.class})
public abstract class ApplicationMapper {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CompetitiveOfferRepository competitiveOfferRepository;

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "competitiveOffer.id", target = "competitiveOfferId")
    public abstract ApplicationDto toDto(Application application);

    @Mapping(target = "student", expression = "java(getStudent(applicationDto.getStudentId()))")
    @Mapping(target = "competitiveOffer", expression = "java(getCompetitiveOffer(applicationDto.getCompetitiveOfferId()))")
    public abstract Application toEntity(ApplicationDto applicationDto);

    @Mapping(target = "student", expression = "java(getStudent(applicationDto.getStudentId()))")
    @Mapping(target = "competitiveOffer", expression = "java(getCompetitiveOffer(applicationDto.getCompetitiveOfferId()))")
    public abstract void updateEntity(ApplicationDto applicationDto, @MappingTarget Application application);

    protected Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    protected CompetitiveOffer getCompetitiveOffer(Long id) {
        return competitiveOfferRepository.findById(id).orElseThrow(() -> new RuntimeException("Competitive Offer not found"));
    }
}
