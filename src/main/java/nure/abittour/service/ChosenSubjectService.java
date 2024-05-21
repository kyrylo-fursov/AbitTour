package nure.abittour.service;

import nure.abittour.model.ChosenSubject;
import nure.abittour.repository.ChosenSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChosenSubjectService {

    @Autowired
    private ChosenSubjectRepository chosenSubjectRepository;

    public List<ChosenSubject> getAll() {
        return chosenSubjectRepository.findAll();
    }

    public ChosenSubject getById(Long id) {
        return chosenSubjectRepository.findById(id).orElse(null);
    }

    public ChosenSubject create(ChosenSubject chosenSubject) {
        return chosenSubjectRepository.save(chosenSubject);
    }

    public ChosenSubject update(ChosenSubject chosenSubject) {
        return chosenSubjectRepository.save(chosenSubject);
    }

    public void delete(Long id) {
        chosenSubjectRepository.deleteById(id);
    }
}
