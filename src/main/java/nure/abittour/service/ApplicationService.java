package nure.abittour.service;

import nure.abittour.dto.ApplicationDto;
import nure.abittour.model.Application;
import nure.abittour.repository.ApplicationRepository;
import nure.abittour.mapper.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationMapper applicationMapper;

    public ApplicationDto createApplication(ApplicationDto applicationDto) {
        Application application = applicationMapper.toEntity(applicationDto);
        application = applicationRepository.save(application);
        return applicationMapper.toDto(application);
    }

    public ApplicationDto getApplicationById(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
        return applicationMapper.toDto(application);
    }

    public List<ApplicationDto> getAllApplications() {
        return applicationRepository.findAll().stream()
                .map(applicationMapper::toDto)
                .collect(Collectors.toList());
    }

    public ApplicationDto updateApplication(Long id, ApplicationDto applicationDto) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
        applicationMapper.updateEntity(applicationDto, application);
        application = applicationRepository.save(application);
        return applicationMapper.toDto(application);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    public List<ApplicationDto> getApplicationsByCompetitiveOfferId(Long competitiveOfferId) {
        return applicationRepository.findByCompetitiveOfferId(competitiveOfferId).stream()
                .map(applicationMapper::toDto)
                .collect(Collectors.toList());
    }
}
