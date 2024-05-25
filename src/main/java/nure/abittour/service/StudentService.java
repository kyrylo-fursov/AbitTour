package nure.abittour.service;

import nure.abittour.dto.StudentDto;
import nure.abittour.mapper.StudentMapper;
import nure.abittour.model.Student;
import nure.abittour.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    private final StudentMapper studentMapper = StudentMapper.INSTANCE;

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        return studentMapper.toDto(student);
    }

    public StudentDto saveStudent(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
