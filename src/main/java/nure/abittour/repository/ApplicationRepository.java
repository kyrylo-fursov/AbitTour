package nure.abittour.repository;

import nure.abittour.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByCompetitiveOfferId(Long competitiveOfferId);
}
