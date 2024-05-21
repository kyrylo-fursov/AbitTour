package nure.abittour.controller;

import nure.abittour.model.University;
import nure.abittour.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @GetMapping
    public List<University> getAll() {
        return universityService.getAll();
    }

    @GetMapping("/{id}")
    public University getById(@PathVariable Long id) {
        return universityService.getById(id);
    }

    @PostMapping
    public University create(@RequestBody University university) {
        return universityService.create(university);
    }

    @PutMapping
    public University update(@RequestBody University university) {
        return universityService.update(university);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        universityService.delete(id);
    }
}
