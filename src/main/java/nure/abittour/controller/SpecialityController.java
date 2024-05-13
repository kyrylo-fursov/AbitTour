package nure.abittour.controller;

import nure.abittour.model.Speciality;
import nure.abittour.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialities")
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @GetMapping
    public List<Speciality> getAll() {
        return specialityService.getAll();
    }

    @GetMapping("/{id}")
    public Speciality getById(@PathVariable Long id) {
        return specialityService.getById(id);
    }

    @PostMapping
    public Speciality create(@RequestBody Speciality speciality) {
        return specialityService.create(speciality);
    }

    @PutMapping
    public Speciality update(@RequestBody Speciality speciality) {
        return specialityService.update(speciality);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        specialityService.delete(id);
    }
}
