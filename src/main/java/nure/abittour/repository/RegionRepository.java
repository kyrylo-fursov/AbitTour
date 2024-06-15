package nure.abittour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nure.abittour.model.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Region findByName(String name);
}
