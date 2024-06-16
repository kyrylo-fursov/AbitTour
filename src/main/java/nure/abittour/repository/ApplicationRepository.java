package nure.abittour.repository;

import nure.abittour.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByCompetitiveOfferId(Long competitiveOfferId);
}
