package nure.abittour.repository;

import nure.abittour.model.CompetitiveOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompetitiveOfferRepository extends JpaRepository<CompetitiveOffer, Long>, JpaSpecificationExecutor<CompetitiveOffer> {
}
