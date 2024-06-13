package nure.abittour.controller;

import nure.abittour.dto.ApplicationDto;
import nure.abittour.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationDto> createApplication(@RequestBody ApplicationDto applicationDto) {
        ApplicationDto createdApplication = applicationService.createApplication(applicationDto);
        return ResponseEntity.ok(createdApplication);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDto> getApplicationById(@PathVariable Long id) {
        ApplicationDto applicationDto = applicationService.getApplicationById(id);
        return ResponseEntity.ok(applicationDto);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDto>> getAllApplications() {
        List<ApplicationDto> applications = applicationService.getAllApplications();
        return ResponseEntity.ok(applications);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationDto> updateApplication(@PathVariable Long id, @RequestBody ApplicationDto applicationDto) {
        ApplicationDto updatedApplication = applicationService.updateApplication(id, applicationDto);
        return ResponseEntity.ok(updatedApplication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/offer/{competitiveOfferId}")
    public ResponseEntity<List<ApplicationDto>> getApplicationsByCompetitiveOfferId(@PathVariable Long competitiveOfferId) {
        List<ApplicationDto> applications = applicationService.getApplicationsByCompetitiveOfferId(competitiveOfferId);
        return ResponseEntity.ok(applications);
    }
}
