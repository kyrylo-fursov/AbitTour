package nure.abittour.repository;

import nure.abittour.model.SubjectCoef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectCoefRepository extends JpaRepository<SubjectCoef, Long> {
}
