package nure.abittour.service;

import nure.abittour.entity.University;
import nure.abittour.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    public University getUniversityById(Long id) {
        return universityRepository.findById(id).orElse(null);
    }

    public University saveUniversity(University university) {
        return universityRepository.save(university);
    }

    public void deleteUniversity(Long id) {
        universityRepository.deleteById(id);
    }
}