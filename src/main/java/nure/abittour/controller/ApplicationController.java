package nure.abittour.controller;

import nure.abittour.model.Application;
import nure.abittour.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public List<Application> getAll() {
        return applicationService.getAll();
    }

    @GetMapping("/{id}")
    public Application getById(@PathVariable Long id) {
        return applicationService.getById(id);
    }

    @PostMapping
    public Application create(@RequestBody Application application) {
        return applicationService.create(application);
    }

    @PutMapping
    public Application update(@RequestBody Application application) {
        return applicationService.update(application);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        applicationService.delete(id);
    }
}
