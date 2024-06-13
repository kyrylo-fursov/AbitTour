package nure.abittour.mapper;

import nure.abittour.dto.StudentDto;
import nure.abittour.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    StudentDto toDto(Student student);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Student toEntity(StudentDto studentDto);
}
