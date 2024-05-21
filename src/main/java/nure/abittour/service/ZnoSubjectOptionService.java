package nure.abittour.service;

import nure.abittour.model.ZnoSubjectOption;
import nure.abittour.repository.ZnoSubjectOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZnoSubjectOptionService {

    @Autowired
    private ZnoSubjectOptionRepository znoSubjectOptionRepository;

    public List<ZnoSubjectOption> getAll() {
        return znoSubjectOptionRepository.findAll();
    }

    public ZnoSubjectOption getById(Long id) {
        return znoSubjectOptionRepository.findById(id).orElse(null);
    }

    public ZnoSubjectOption create(ZnoSubjectOption znoSubjectOption) {
        return znoSubjectOptionRepository.save(znoSubjectOption);
    }

    public ZnoSubjectOption update(ZnoSubjectOption znoSubjectOption) {
        return znoSubjectOptionRepository.save(znoSubjectOption);
    }

    public void delete(Long id) {
        znoSubjectOptionRepository.deleteById(id);
    }
}
