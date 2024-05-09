package nure.abittour.repository;

import nure.abittour.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
}
