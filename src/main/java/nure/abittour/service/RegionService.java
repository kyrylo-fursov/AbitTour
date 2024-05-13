package nure.abittour.service;

import nure.abittour.model.Region;
import nure.abittour.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    public Region getById(Long id) {
        return regionRepository.findById(id).orElse(null);
    }

    public Region create(Region region) {
        return regionRepository.save(region);
    }

    public Region update(Region region) {
        return regionRepository.save(region);
    }

    public void delete(Long id) {
        regionRepository.deleteById(id);
    }
}
