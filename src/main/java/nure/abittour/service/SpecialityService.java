package nure.abittour.service;

import nure.abittour.model.Speciality;
import nure.abittour.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    public List<Speciality> getAll() {
        return specialityRepository.findAll();
    }

    public Speciality getById(Long id) {
        return specialityRepository.findById(id).orElse(null);
    }

    public Speciality create(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    public Speciality update(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    public void delete(Long id) {
        specialityRepository.deleteById(id);
    }
}
