package nure.abittour.controller;


import nure.abittour.dto.ApplicationRequest;
import nure.abittour.dto.ApplicationResponse;
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

    @GetMapping
    public List<ApplicationResponse> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ApplicationResponse getApplicationById(@PathVariable("id") Long id) {
        return applicationService.getApplicationById(id);
    }
    @GetMapping("/offer/{competitiveOfferId}")
    public List<ApplicationResponse> getApplicationsByCompetitiveOfferId(@PathVariable Long competitiveOfferId) {
        return applicationService.getApplicationsByCompetitiveOfferId(competitiveOfferId);
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(@RequestBody ApplicationRequest applicationRequest) {
        ApplicationResponse createdApplication = applicationService.createApplication(applicationRequest);
        return ResponseEntity.ok(createdApplication);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResponse> updateApplication(@PathVariable Long id, @RequestBody ApplicationRequest applicationRequest) {
        ApplicationResponse updatedApplication = applicationService.updateApplication(id, applicationRequest);
        return ResponseEntity.ok(updatedApplication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
}
