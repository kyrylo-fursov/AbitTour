package nure.abittour.service;

import nure.abittour.dto.SpecialityDTO;
import nure.abittour.mapper.SpecialityMapper;
import nure.abittour.mapper.SubjectCoefMapper;
import nure.abittour.model.Speciality;
import nure.abittour.model.SubjectCoef;
import nure.abittour.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    public boolean existsByCode(String code) {
        return specialityRepository.existsByCode(code);
    }

    public SpecialityDTO getSpecialityWithCoefs(Long id) {
        Speciality speciality = specialityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Speciality not found"));
        return SpecialityMapper.INSTANCE.toDto(speciality);
    }
    public List<SpecialityDTO> getAllSpecialities() {
        return specialityRepository.findAll().stream()
                .map(SpecialityMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public SpecialityDTO createSpeciality(SpecialityDTO specialityDTO) {
        Speciality speciality = SpecialityMapper.INSTANCE.toEntity(specialityDTO);

        speciality.getSubjectCoefs().forEach(subjectCoef -> subjectCoef.setSpeciality(speciality));

        Speciality savedSpeciality = specialityRepository.save(speciality);
        return SpecialityMapper.INSTANCE.toDto(savedSpeciality);
    }

    public SpecialityDTO updateSpeciality(Long id, SpecialityDTO specialityDTO) {
        Speciality speciality = specialityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Speciality not found"));

        speciality.setCode(specialityDTO.getCode());
        speciality.setName(specialityDTO.getName());
        speciality.setSpecialization(specialityDTO.getSpecialization());

        speciality.getSubjectCoefs().clear();
        specialityDTO.getSubjectCoefs().forEach(subjectCoefDTO -> {
            SubjectCoef subjectCoef = SubjectCoefMapper.INSTANCE.toEntity(subjectCoefDTO);
            subjectCoef.setSpeciality(speciality);
            speciality.getSubjectCoefs().add(subjectCoef);
        });

        Speciality updatedSpeciality = specialityRepository.save(speciality);
        return SpecialityMapper.INSTANCE.toDto(updatedSpeciality);
    }

    public void deleteSpeciality(Long id) {
        if (!specialityRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Speciality not found");
        }
        specialityRepository.deleteById(id);
    }
}