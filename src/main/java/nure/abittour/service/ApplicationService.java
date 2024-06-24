package nure.abittour.service;

import nure.abittour.dto.ApplicationRequest;
import nure.abittour.dto.ApplicationResponse;
import nure.abittour.mapper.ApplicationMapper;
import nure.abittour.model.Application;
import nure.abittour.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationMapper applicationMapper;

    public List<ApplicationResponse> getAllApplications() {
        return applicationRepository.findAll().stream()
                .map(applicationMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public ApplicationResponse getApplicationById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        return applicationMapper.toResponseDto(application);
    }

    public List<ApplicationResponse> getApplicationsByCompetitiveOfferId(Long competitiveOfferId) {
        return applicationRepository.findByCompetitiveOfferIdOrderByTotalScoreDesc(competitiveOfferId).stream()
                .map(applicationMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public ApplicationResponse createApplication(ApplicationRequest applicationRequest) {
        Application application = applicationMapper.toEntity(applicationRequest);
        Application savedApplication = applicationRepository.save(application);
        return applicationMapper.toResponseDto(savedApplication);
    }

    public ApplicationResponse updateApplication(Long id, ApplicationRequest applicationRequest) {
        Application existingApplication = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        applicationMapper.updateEntityFromRequest(applicationRequest, existingApplication);

        Application updatedApplication = applicationRepository.save(existingApplication);
        return applicationMapper.toResponseDto(updatedApplication);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}
