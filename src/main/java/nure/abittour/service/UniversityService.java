package nure.abittour.service;

import nure.abittour.dto.UniversityDTO;
import nure.abittour.dto.UniversitySearchRequest;
import nure.abittour.filter.UniversitySpecification;
import nure.abittour.mapper.UniversityMapper;
import nure.abittour.model.University;
import nure.abittour.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;


    @Autowired
    private UniversityMapper universityMapper;


    public List<University> getAll() {
        return universityRepository.findAll();
    }

    public University getById(Long id) {
        return universityRepository.findById(id).orElse(null);
    }

    public University create(University university) {
        return universityRepository.save(university);
    }

    public University update(University university) {
        return universityRepository.save(university);
    }

    public List<UniversityDTO> searchUniversities(UniversitySearchRequest searchRequest) {
        UniversitySpecification spec = new UniversitySpecification(searchRequest);
        return universityRepository.findAll(spec).stream()
                .map(universityMapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        universityRepository.deleteById(id);
    }
}
