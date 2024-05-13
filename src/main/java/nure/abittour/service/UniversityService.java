package nure.abittour.service;

import nure.abittour.model.University;
import nure.abittour.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

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

    public void delete(Long id) {
        universityRepository.deleteById(id);
    }
}
