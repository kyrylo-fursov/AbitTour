package nure.abittour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nure.abittour.model.Speciality;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
    Speciality findByCode(String code);
    Speciality findAllByCode(String code);
}
