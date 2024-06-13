package nure.abittour.service;

import nure.abittour.model.SubjectCoef;
import nure.abittour.repository.SubjectCoefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectCoefService {

    @Autowired
    private SubjectCoefRepository subjectCoefRepository;

    public List<SubjectCoef> findAll() {
        return subjectCoefRepository.findAll();
    }

    public Optional<SubjectCoef> findById(Long id) {
        return subjectCoefRepository.findById(id);
    }

    public SubjectCoef save(SubjectCoef subjectCoef) {
        return subjectCoefRepository.save(subjectCoef);
    }

    public void deleteById(Long id) {
        subjectCoefRepository.deleteById(id);
    }
}
